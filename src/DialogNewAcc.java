import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DialogNewAcc extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldAccount;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JLabel lblErrorAccount;
	private JLabel lblErrorUsername;
	private JLabel lblErrorPassword;
	private Password pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogNewAcc dialog = new DialogNewAcc();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogNewAcc() {
		setTitle("New account ");
		setResizable(false);
		setBounds(100, 100, 549, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}

	public DialogNewAcc(JFrame frame, boolean modal) {
		super(frame, modal);
		setTitle("New account ");
		setResizable(false);
		setBounds(100, 100, 549, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}

	{
		JLabel lblAccountName = new JLabel("Account name");
		lblAccountName.setBounds(28, 26, 86, 14);
		contentPanel.add(lblAccountName);
	}
	{
		textFieldAccount = new JTextField();
		textFieldAccount.setColumns(10);
		textFieldAccount.setBounds(28, 51, 163, 20);
		contentPanel.add(textFieldAccount);
	}
	{
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(28, 82, 86, 14);
		contentPanel.add(lblUsername);
	}
	{
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(28, 105, 163, 20);
		contentPanel.add(textFieldUsername);
	}
	{
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(28, 142, 86, 14);
		contentPanel.add(lblPassword);
	}
	{
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(28, 167, 163, 20);
		contentPanel.add(textFieldPassword);
	}
	{
		lblErrorAccount = new JLabel("Error! You must enter account name.");
		lblErrorAccount.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErrorAccount.setForeground(Color.RED);
		lblErrorAccount.setBounds(201, 54, 332, 14);
		contentPanel.add(lblErrorAccount);
		lblErrorAccount.setVisible(false);
	}
	{
		lblErrorUsername = new JLabel("Error! You must enter username.");
		lblErrorUsername.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErrorUsername.setForeground(Color.RED);
		lblErrorUsername.setBounds(201, 108, 332, 14);
		contentPanel.add(lblErrorUsername);
		lblErrorUsername.setVisible(false);
	}
	{
		lblErrorPassword = new JLabel("Error! You must enter password.");
		lblErrorPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErrorPassword.setForeground(Color.RED);
		lblErrorPassword.setBounds(201, 170, 332, 14);
		contentPanel.add(lblErrorPassword);
		lblErrorPassword.setVisible(false);
		{
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// ovo ima obicaj da izbaguje situaciju kada kliknem OK a da
					// nista nisam uneo, mada ne stalno, nekada radi i kako
					// treba. ali kad ne radi, zamrzne sve i not responding
					lblErrorAccount.setVisible(false);
					lblErrorPassword.setVisible(false);
					lblErrorUsername.setVisible(false);
					boolean okay = false;
					while (!okay) {
						pw = new Password();
						try {
							pw.setAccount(textFieldAccount.getText());
						} catch (Exception e1) {
							lblErrorAccount.setVisible(true);
						}
						try {
							pw.setPassword(textFieldPassword.getText());
						} catch (Exception e1) {
							lblErrorPassword.setVisible(true);
						}
						try {
							pw.setUsername(textFieldUsername.getText());
						} catch (Exception e1) {
							lblErrorUsername.setVisible(true);
						}
						if (pw.getAccount() != null && pw.getPassword() != null && pw.getUsername() != null) {
							okay = true;
						}
					}
					if(okay)
						setVisible(false);
				}
			});
			btnOk.setBounds(201, 242, 89, 23);
			contentPanel.add(btnOk);
		}
		{
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					setVisible(false);
				}
			});
			btnCancel.setBounds(300, 242, 89, 23);
			contentPanel.add(btnCancel);
		}
		lblErrorPassword.setVisible(false);
	}

	public Password showDialog() {
		setVisible(true);
		dispose();
		return pw;
	}

}
