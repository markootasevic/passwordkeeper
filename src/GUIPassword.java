import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.util.LinkedList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JCheckBox;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUIPassword {

	private JFrame frmPasswordVault;
	private JComboBox<String> comboBox;
	private JButton btnAddNew;
	private JButton btnClear;
	private JButton btnSave;
	private JButton btnLoad;
	private JLabel lblYouHaveAlready;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNew;
	private JMenuItem mntmClear;
	private JMenuItem mntmLoad;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;
	private JMenu mnEdit;
	private JMenu mnHelp;

	private DialogChange changeDialog;
	private DialogNewAcc newAccount;
	private Password pass;
	private LinkedList<Password> passwordList;
	private DialogClear clear;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblShowUsername;
	private JLabel lblShowPassword;
	private JCheckBox chckbxShow;
	private JButton btnChange;
	private JMenuItem mntmChange;
	private JButton btnOk;
	private JMenuItem mntmNoHelp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIPassword window = new GUIPassword();
					window.frmPasswordVault.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIPassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPasswordVault = new JFrame();
		frmPasswordVault.setResizable(false);
		frmPasswordVault.setTitle("Password Vault");
		frmPasswordVault.setBounds(100, 100, 416, 269);
		frmPasswordVault.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPasswordVault.getContentPane().setLayout(null);
		frmPasswordVault.getContentPane().add(getComboBox());
		frmPasswordVault.getContentPane().add(getBtnAddNew());
		frmPasswordVault.getContentPane().add(getBtnClear());
		frmPasswordVault.getContentPane().add(getBtnSave());
		frmPasswordVault.getContentPane().add(getBtnLoad());
		frmPasswordVault.getContentPane().add(getLblYouHaveAlready());
		frmPasswordVault.getContentPane().add(getLblUsername());
		frmPasswordVault.getContentPane().add(getLblPassword());
		frmPasswordVault.getContentPane().add(getLblShowUsername());
		frmPasswordVault.getContentPane().add(getLblShowPassword());
		frmPasswordVault.getContentPane().add(getChckbxShow());
		frmPasswordVault.getContentPane().add(getBtnChange());
		frmPasswordVault.getContentPane().add(getBtnOk());
		frmPasswordVault.setJMenuBar(getMenuBar());
	}

	private JLabel getLblYouHaveAlready() {
		if (lblYouHaveAlready == null) {
			lblYouHaveAlready = new JLabel("You have already saved this entry.");
			lblYouHaveAlready.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblYouHaveAlready.setHorizontalAlignment(SwingConstants.CENTER);
			lblYouHaveAlready.setBounds(109, 11, 191, 14);
			lblYouHaveAlready.setVisible(false);
		}
		return lblYouHaveAlready;
	}

	private JComboBox<String> getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox<String>();
			comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					if (chckbxShow.isSelected()) {
						chckbxShow.doClick();
					}
				}
			});
			comboBox.setBounds(10, 40, 386, 20);
		}
		return comboBox;
	}

	private JButton getBtnAddNew() {
		if (btnAddNew == null) {
			btnAddNew = new JButton("Add new");
			btnAddNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblYouHaveAlready.setVisible(false);
					newAccount = new DialogNewAcc(frmPasswordVault, true); // ne
																			// razumem
																			// sta
																			// ovo
																			// radi,
																			// ali
																			// radi
																			// nekako
					pass = newAccount.showDialog();

					if (pass.getAccount() != null && pass.getPassword() != null
							&& pass.getUsername() != null) {
						if (passwordList == null) {
							passwordList = new LinkedList<Password>();
						}
						if (passwordList.contains(pass)) {
							lblYouHaveAlready.setVisible(true);
							btnOk.setVisible(true);
						} else {
							passwordList.add(pass);
							comboBox.addItem(pass.getAccount());
							comboBox.setSelectedItem(pass.getAccount());
						}
					}
				}
			});
			btnAddNew.setBounds(10, 187, 89, 23);
		}
		return btnAddNew;
	}

	private JButton getBtnClear() {
		if (btnClear == null) {
			btnClear = new JButton("Clear");
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear = new DialogClear(frmPasswordVault, true);
					boolean dec = clear.desicion();
					if (dec) {
						if (passwordList != null) {
							passwordList = null;
							comboBox.removeAllItems();
							if (chckbxShow.isSelected()) {
								chckbxShow.doClick();
							}
						}
					}
				}
			});
			btnClear.setBounds(109, 187, 89, 23);
		}
		return btnClear;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Save");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"passwords", "accounts");
					chooser.setFileFilter(filter);
					chooser.setCurrentDirectory(new File(Utility
							.getDir("resources")));
					int returnVal = chooser.showSaveDialog(mntmSave);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						serialize(Utility.getDir("resources") + "\\"
								+ chooser.getSelectedFile().getName());
						JOptionPane.showMessageDialog(frmPasswordVault,
								"Passwords saved.", "Save",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
			btnSave.setBounds(307, 187, 89, 23);
		}
		return btnSave;
	}

	private JButton getBtnLoad() {
		if (btnLoad == null) {
			btnLoad = new JButton("Load");
			btnLoad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"passwords", "accounts");
					chooser.setFileFilter(filter);
					chooser.setCurrentDirectory(new File(Utility
							.getDir("resources")));
					int returnVal = chooser.showOpenDialog(btnLoad);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						passwordList = deserialize(Utility.getDir("resources")
								+ "\\" + chooser.getSelectedFile().getName());
					}
					populateComboBox();
				}
			});
			btnLoad.setBounds(208, 187, 89, 23);
		}
		return btnLoad;
	}

	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnFile());
			menuBar.add(getMnEdit());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnFile() {
		if (mnFile == null) {
			mnFile = new JMenu("File");
			mnFile.add(getMntmNew());
			mnFile.add(getMntmClear());
			mnFile.add(getMntmLoad());
			mnFile.add(getMntmSave());
			mnFile.add(getMntmExit());
		}
		return mnFile;
	}

	private JMenuItem getMntmNew() {
		if (mntmNew == null) {
			mntmNew = new JMenuItem("New");
			mntmNew.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					lblYouHaveAlready.setVisible(false);
					newAccount = new DialogNewAcc(frmPasswordVault, true);
					pass = newAccount.showDialog();

					if (passwordList == null) {
						passwordList = new LinkedList<Password>();
					}
					if (passwordList.contains(pass)) {
						lblYouHaveAlready.setVisible(true);
					} else {
						passwordList.add(pass);
						comboBox.addItem(pass.getAccount());
					}
				}
			});
		}
		return mntmNew;
	}

	private JMenuItem getMntmClear() {
		if (mntmClear == null) {
			mntmClear = new JMenuItem("Clear");
			mntmClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear = new DialogClear(frmPasswordVault, true);
					boolean dec = clear.desicion();
					if (dec) {
						if (passwordList != null) {
							passwordList = null;
							comboBox.removeAllItems();
							if (chckbxShow.isSelected()) {
								chckbxShow.doClick();

							}
							// lblShowPassword.setVisible(false);
							// lblShowUsername.setVisible(false);
						}
					}
				}
			});
		}
		return mntmClear;
	}

	private JMenuItem getMntmLoad() {
		if (mntmLoad == null) {
			mntmLoad = new JMenuItem("Load");
			mntmLoad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"passwords", "accounts");
					chooser.setFileFilter(filter);
					chooser.setCurrentDirectory(new File(Utility
							.getDir("resources")));
					int returnVal = chooser.showOpenDialog(btnLoad);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						passwordList = deserialize(Utility.getDir("resources")
								+ "\\" + chooser.getSelectedFile().getName());
					}
					populateComboBox();
				}
			});
		}
		return mntmLoad;
	}

	private JMenuItem getMntmSave() {
		if (mntmSave == null) {
			mntmSave = new JMenuItem("Save");
			mntmSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(
							"passwords", "accounts");
					chooser.setFileFilter(filter);
					chooser.setCurrentDirectory(new File(Utility
							.getDir("resources")));
					int returnVal = chooser.showSaveDialog(mntmSave);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						serialize(Utility.getDir("resources") + "\\"
								+ chooser.getSelectedFile().getName());
						JOptionPane.showMessageDialog(frmPasswordVault,
								"Passwords saved.", "Save",
								JOptionPane.PLAIN_MESSAGE);
					}
				}
			});
		}
		return mntmSave;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return mntmExit;
	}

	private JMenu getMnEdit() {
		if (mnEdit == null) {
			mnEdit = new JMenu("Edit");
			mnEdit.add(getMntmChange());
		}
		return mnEdit;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.add(getMntmNoHelp());
		}
		return mnHelp;
	}

	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Username:");
			lblUsername.setBounds(10, 71, 89, 14);
			// lblUsername.setVisible(false);
		}
		return lblUsername;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setBounds(10, 96, 89, 14);
			// lblPassword.setVisible(false);
		}
		return lblPassword;
	}

	private JLabel getLblShowUsername() {
		if (lblShowUsername == null) {
			lblShowUsername = new JLabel("New label");
			lblShowUsername.setBounds(102, 71, 294, 14);
			lblShowUsername.setVisible(false);
		}
		return lblShowUsername;
	}

	private JLabel getLblShowPassword() {
		if (lblShowPassword == null) {
			lblShowPassword = new JLabel("New label");
			lblShowPassword.setBounds(102, 96, 294, 14);
			lblShowPassword.setVisible(false);
		}
		return lblShowPassword;
	}

	private JCheckBox getChckbxShow() {
		if (chckbxShow == null) {
			chckbxShow = new JCheckBox("Show ");
			chckbxShow.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (chckbxShow.isSelected()) {
						if (comboBox.getSelectedItem() == null) {
							lblShowPassword.setText(null);
							lblShowUsername.setText(null);
						} else {
							String accountName = (String) comboBox
									.getSelectedItem();
							for (int i = 0; i < passwordList.size(); i++) {
								if ((passwordList.get(i).getAccount())
										.equals(accountName)) {
									lblShowPassword.setText(passwordList.get(i)
											.getPassword());
									lblShowUsername.setText(passwordList.get(i)
											.getUsername());
									lblShowPassword.setVisible(true);
									lblShowUsername.setVisible(true);
									break;
								}
							}
						}
					} else {
						lblShowPassword.setVisible(false);
						lblShowUsername.setVisible(false);
					}

				}
			});
			chckbxShow.setBounds(10, 117, 97, 23);
		}
		return chckbxShow;
	}

	private JButton getBtnChange() {
		if (btnChange == null) {
			btnChange = new JButton("Change...");
			btnChange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (comboBox.getSelectedItem() == null) {
						return;
					} else {
						changeDialog = new DialogChange(frmPasswordVault, true);
						Password pword = changeDialog.getChange();
						String account = (String) comboBox.getSelectedItem();
						if (pword != null) {
							for (int i = 0; i < passwordList.size(); i++) {
								if (passwordList.get(i).getAccount()
										.equals(account)) {
									passwordList.set(i, pword);
									comboBox.setEditable(true);
									comboBox.removeItem(comboBox
											.getSelectedItem());
									comboBox.addItem(pword.getAccount());
									comboBox.setSelectedItem(pword.getAccount());
									comboBox.setEditable(false);
									if (chckbxShow.isSelected())
										chckbxShow.doClick();
									break;
								}
							}
						}

					}
				}
			});
			btnChange.setBounds(10, 153, 89, 23);
		}
		return btnChange;
	}

	private JMenuItem getMntmChange() {
		if (mntmChange == null) {
			mntmChange = new JMenuItem("Change");
			mntmChange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (comboBox.getSelectedItem() == null) {
						return;
					} else {
						changeDialog = new DialogChange(frmPasswordVault, true);
						Password pword = changeDialog.getChange();
						String account = (String) comboBox.getSelectedItem();
						if (pword != null) {
							for (int i = 0; i < passwordList.size(); i++) {
								if (passwordList.get(i).getAccount()
										.equals(account)) {
									passwordList.set(i, pword);
									comboBox.setEditable(true);
									comboBox.removeItem(comboBox
											.getSelectedItem());
									comboBox.addItem(pword.getAccount());
									comboBox.setSelectedItem(pword.getAccount());
									comboBox.setEditable(false);
									if (chckbxShow.isSelected())
										chckbxShow.doClick();
									break;
								}
							}
						}

					}
				}
			});
		}
		return mntmChange;
	}

	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					btnOk.setVisible(false);
					lblYouHaveAlready.setVisible(false);
					if (chckbxShow.isSelected())
						chckbxShow.doClick();
				}
			});
			btnOk.setBounds(296, 7, 63, 23);
			btnOk.setVisible(false);
		}
		return btnOk;
	}

	@SuppressWarnings("resource")
	private void serialize(String filename) {
		File f = new File(filename);
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream(f));
			out.writeObject(passwordList);
		} catch (Exception e) {
			// nista
		}
	}

	@SuppressWarnings("unchecked")
	// ne znam cemu ovo, ali ga je podvukao zuto, pa reko' da kliknem
	private LinkedList<Password> deserialize(String filename) {
		File f = new File(filename);
		LinkedList<Password> passwordList = new LinkedList<Password>();
		try {
			@SuppressWarnings("resource")
			// isto kao ovo gore
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			passwordList = (LinkedList<Password>) in.readObject();
		} catch (Exception e) {
			// e.printStackTrace(); -sta ce mi stek trejs?
		}
		return passwordList;
	}

	private void populateComboBox() {
		comboBox.removeAllItems();
		for (int i = 0; i < passwordList.size(); i++) {
			comboBox.addItem(passwordList.get(i).getAccount());
		}
	}

	private JMenuItem getMntmNoHelp() {
		if (mntmNoHelp == null) {
			mntmNoHelp = new JMenuItem("No help");
		}
		return mntmNoHelp;
	}

	// metoda ispod je bio pokusaj da nekako u DialogChange sprovedem izabrani
	// String da bih popunio text polja vec unetim podacima, sto bi bilo mnogo
	// kul za promenu tih podataka - ali mora da bude static, sto znaci da i
	// comboBox mora da bude static, sto nikako nije dobro
	public String selectedComboBoxItem() {
		if (comboBox.getSelectedItem() == null) {
			return "";
		} else
			return (String) comboBox.getSelectedItem();
	}
}
