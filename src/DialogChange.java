import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DialogChange extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldAccountName;
	private JTextField textFieldUsername;
	private JTextField textField;
	private JLabel lblEnterAccountName;
	private JLabel lblEnterUsername;
	private JLabel lblEnterPassword;
	private Password pword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogChange dialog = new DialogChange();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogChange() {
		setResizable(false);
		setTitle("Change");
		setBounds(100, 100, 476, 149);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
	}

	public DialogChange(JFrame frame, boolean modal) {
		super(frame, modal);
		setResizable(false);
		setTitle("Change");
		setBounds(100, 100, 476, 149);
		getContentPane().setLayout(new BorderLayout(0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		// GUIPassword.selectedComboBoxItem();
		// takodje pokusaj da ispunim text polja unetim podacima koje treba
		// promeniti, ali ne moze da se poziva nad klasom kad nije staticka
		// metoda
	}

	{
		contentPanel.setLayout(null);
		JLabel lblAccountName = new JLabel("Account name:");
		lblAccountName.setBounds(10, 11, 105, 14);
		contentPanel.add(lblAccountName);
	}
	{
		textFieldAccountName = new JTextField();
		textFieldAccountName.setBounds(125, 8, 137, 20);
		contentPanel.add(textFieldAccountName);
		textFieldAccountName.setColumns(10);
	}
	{
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(10, 36, 105, 14);
		contentPanel.add(lblUsername);
	}
	{
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(125, 33, 137, 20);
		contentPanel.add(textFieldUsername);
		textFieldUsername.setColumns(10);
	}
	{
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 61, 105, 14);
		contentPanel.add(lblPassword);
	}
	{
		textField = new JTextField();
		textField.setBounds(125, 58, 137, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
	}
	{
		lblEnterAccountName = new JLabel("You must enter account name!");
		lblEnterAccountName.setBounds(272, 11, 188, 14);
		lblEnterAccountName.setForeground(Color.RED);
		contentPanel.add(lblEnterAccountName);
		lblEnterAccountName.setVisible(false);
	}
	{
		lblEnterUsername = new JLabel("You must enter username!");
		lblEnterUsername.setBounds(272, 36, 188, 14);
		lblEnterUsername.setForeground(Color.RED);
		contentPanel.add(lblEnterUsername);
		lblEnterUsername.setVisible(false);
	}
	{
		lblEnterPassword = new JLabel("You must enter password!");
		lblEnterPassword.setBounds(272, 61, 188, 14);
		lblEnterPassword.setForeground(Color.RED);
		contentPanel.add(lblEnterPassword);
		lblEnterPassword.setVisible(false);
	}
	{
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(95, 89, 89, 23);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblEnterAccountName.setVisible(false);
				lblEnterPassword.setVisible(false);
				lblEnterUsername.setVisible(false);
				boolean okay = false;
				while (!okay) {
					pword = new Password();
					try {
						pword.setAccount(textFieldAccountName.getText());
					} catch (Exception e1) {
						lblEnterAccountName.setVisible(true);
					}
					try {
						pword.setPassword(textField.getText());
					} catch (Exception e1) {
						lblEnterPassword.setVisible(true);
					}
					try {
						pword.setUsername(textFieldUsername.getText());
					} catch (Exception e1) {
						lblEnterUsername.setVisible(true);
					}
					if (pword.getAccount() != null
							&& pword.getPassword() != null
							&& pword.getUsername() != null)
						okay = true;
					else
						okay = false;
				}
				if (!lblEnterAccountName.isVisible()
						&& !lblEnterPassword.isVisible()
						&& !lblEnterUsername.isVisible())
					setVisible(false);
			}
		}

		);
		contentPanel.add(btnOk);
	}
	{
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(194, 89, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPanel.add(btnCancel);
	}

	public Password getChange() {
		setVisible(true);
		dispose();
		return pword;
	}
}
