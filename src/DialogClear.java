import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class DialogClear extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private String yesOrNo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogClear dialog = new DialogClear();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogClear() {
		setTitle("Clear");
		setResizable(false);
		setBounds(100, 100, 487, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}
	public DialogClear(JFrame frame, boolean modal){
		super(frame, modal);
		setTitle("Clear");
		setResizable(false);
		setBounds(100, 100, 487, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setVisible(true);
	}
		{
			JButton btnYes = new JButton("Yes");
			btnYes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					yesOrNo = "yes";
					setVisible(false);
				}
			});
			btnYes.setBounds(114, 113, 89, 23);
			contentPanel.add(btnYes);
		}
		{
			JButton btnNo = new JButton("No");
			btnNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					yesOrNo = "no";
					setVisible(false);
				}
			});
			btnNo.setBounds(274, 113, 89, 23);
			contentPanel.add(btnNo);
		}
		{
			JLabel lblYouAreAbout = new JLabel("You are about to delete all of your saved passwords. Do you want to continue?");
			lblYouAreAbout.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblYouAreAbout.setHorizontalAlignment(SwingConstants.CENTER);
			lblYouAreAbout.setBounds(10, 11, 461, 91);
			contentPanel.add(lblYouAreAbout);
		}
	public boolean desicion(){
		dispose();
		if(yesOrNo.equals("yes"))
			return true;
		else return false;
	}
	
}
