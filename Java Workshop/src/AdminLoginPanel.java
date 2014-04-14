import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 */


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * @author user1
 *
 */
public class AdminLoginPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUserId;
	private JTextField txtPassword;
	private JButton btnLogin;
	private JButton btnCancel;
	private JOptionPane jOptionPane1;
	private JLabel lblPassword;
	private JTextField txtUserId;

	
	public AdminLoginPanel() 
	{
		super();
				
		// Initialize the window.
		initGUI();

		
	}
	private void initGUI() {
		try {
			{
				this.setPreferredSize(new java.awt.Dimension(606, 330));
				this.setLayout(null);
				{
					lblUserId = new JLabel();
					this.add(lblUserId);
					lblUserId.setText("User Name:");
					lblUserId.setBounds(175, 95, 71, 15);
				}
				{
					txtUserId = new JTextField();
					this.add(txtUserId);
					txtUserId.setText("Administrator");
					txtUserId.setBounds(300, 92, 107, 22);
					txtUserId.setEnabled(false);
					txtUserId.setEditable(false);
				}
				{
					lblPassword = new JLabel();
					this.add(lblPassword);
					lblPassword.setText("Password:");
					lblPassword.setBounds(175, 144, 62, 15);
				}
				{
					txtPassword = new JTextField();
					this.add(txtPassword);
					txtPassword.setText("jTextField1");
					txtPassword.setBounds(300, 141, 71, 22);
				}
				{
					btnLogin = new JButton();
					this.add(btnLogin);
					btnLogin.setText("Login");
					btnLogin.setBounds(237, 205, 46, 22);
				}
				{
					btnCancel = new JButton();
					this.add(btnCancel);
					btnCancel.setText("Exit");
					btnCancel.setBounds(317, 205, 54, 22);
				}
				{
					jOptionPane1 = new JOptionPane();
					this.add(jOptionPane1);
					jOptionPane1.setBounds(22, 18, 562, 277);
					jOptionPane1.setMessage("");
					jOptionPane1.setLayout(null);
					jOptionPane1.setRequestFocusEnabled(false);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
