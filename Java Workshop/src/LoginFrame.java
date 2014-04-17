/*
 * JAVA - Threaded Project
 * Team - 5
 * Author:Suparna Roychoudhury
 * Date Created:15th Mar 2014
 * About the file: Implementation of LoginFrame Class for admin login
 * */


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;


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
public class LoginFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblUserId;
	private JButton btnLogin;
	private JButton btnCancel;
	private JPasswordField jPasswordField1;
	private JLabel jLabel1;
	private JLabel lblTitle;
	private JPanel jPanel1;
	private JOptionPane jOptionPane1;
	private JTextField txtUserId;
	private JLabel lblPassword;

	AgentDB agentDB = new AgentDB();
	Agent agent = new Agent();	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginFrame inst = new LoginFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public LoginFrame() {
		super();
		agentDB.connect();//connect to DB to fetch login information
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Travel Experts");
			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(774, 628));
			{
				lblUserId = new JLabel();
				getContentPane().add(lblUserId);
				lblUserId.setText("User Name:");
				lblUserId.setBounds(266, 203, 135, 15);
			}
			{
				lblPassword = new JLabel();
				getContentPane().add(lblPassword);
				lblPassword.setText("Password:");
				lblPassword.setBounds(266, 249, 105, 15);
			}
			{
				txtUserId = new JTextField();
				getContentPane().add(txtUserId);
				txtUserId.setBounds(405, 200, 114, 22);
			}
			{
				btnLogin = new JButton();
				getContentPane().add(btnLogin);
				btnLogin.setText("Login");
				btnLogin.setBounds(291, 310, 100, 30);
				btnLogin.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						btnLoginMouseClicked(evt);
					}
				});
			}
			{
				btnCancel = new JButton();
				getContentPane().add(btnCancel);
				btnCancel.setText("Cancel");
				btnCancel.setBounds(417, 310, 107, 30);
				btnCancel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						System.out.println("btnCancel.mouseClicked, event="+evt);
						//TODO add your code for btnCancel.mouseClicked
						dispose();
					}
				});
			}
			{
				jPasswordField1 = new JPasswordField();
				getContentPane().add(jPasswordField1);
				jPasswordField1.setBounds(405, 246, 114, 22);
			}
			{
				jOptionPane1 = new JOptionPane();
				getContentPane().add(jOptionPane1);
				//jOptionPane1.setLayout(jOptionPane1Layout);
				jOptionPane1.setBounds(27, 252, 27, 7);
				jOptionPane1.setMessage("");
			}
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1);
				jPanel1.setBounds(107, 124, 561, 358);
				jPanel1.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
			}
			{
				BufferedImage image = ImageIO.read(getClass().getResource("images/index.jpeg"));
				ImageIcon icon = new ImageIcon(image);
		        // JLabel label = new JLabel(icon);
				jLabel1 = new JLabel(icon);
				getContentPane().add(jLabel1);
				jLabel1.setBounds(303, 7, 179, 74);
				{
					lblTitle = new JLabel();			
					getContentPane().add(lblTitle);
					lblTitle.setLayout(null);
					lblTitle.setText("Admin Dashboard");
					lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
					lblTitle.setFont(new java.awt.Font("Dialog",1,20));
					lblTitle.setBounds(0, 70, 770, 68);
				}
			}
			pack();
			this.setSize(774, 628);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private void btnLoginMouseClicked(MouseEvent evt) {

		String user = txtUserId.getText().trim();
		String password = jPasswordField1.getText().trim();
		if(user.equals("") || password.equals("")) //in case username or password is blank
		{
			jOptionPane1.showMessageDialog(null, "Please enter User Name and Password");
			txtUserId.requestFocus();
		}
		else
		{
			if (agentDB.verifyLogin(user, password))//call DB to verify login
			{
				JavaWorkshop jw = new JavaWorkshop();
				jw.setVisible(true);	
				dispose();
			}
			else //invalid login
			{
				jOptionPane1.showMessageDialog(null, "Invalid User Name or Password");
				txtUserId.requestFocus();
			}
		}
	}

}
