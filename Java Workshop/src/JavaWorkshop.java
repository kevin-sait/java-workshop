import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
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
public class JavaWorkshop extends javax.swing.JFrame {
	private JTabbedPane jTabbedPane;
	private AgentPanel tabAgents;
	private PackagePanel tabPackages;
	private JButton btnExit;
	private JLabel jLabel1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JavaWorkshop inst = new JavaWorkshop();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public JavaWorkshop() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setTitle("Travel Experts");
			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(774, 628));
			this.setResizable(false);
			{
				jTabbedPane = new JTabbedPane();
				getContentPane().add(jTabbedPane, "Center");
				jTabbedPane.setBounds(30, 68, 706, 448);
				jTabbedPane.setFont(new java.awt.Font("Dialog",0,14));
				{
					tabAgents = new AgentPanel();
					jTabbedPane.addTab("Agents", null, tabAgents, null);
					tabAgents.setPreferredSize(new java.awt.Dimension(739, 419));
					tabAgents.setLayout(null);
				}
				{
					tabPackages = new PackagePanel();
					jTabbedPane.addTab("Packages", null, tabPackages, null);
					tabPackages.setPreferredSize(new java.awt.Dimension(702, 419));
					tabPackages.setLayout(null);
				}
			}
			{
				jLabel1 = new JLabel();
				jLabel1.setLayout(null);
				getContentPane().add(jLabel1, "North");
				jLabel1.setText("Admin Dashboard");
				jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
				jLabel1.setFont(new java.awt.Font("Dialog",1,20));
				jLabel1.setBounds(0, 0, 770, 68);
			}
			{
				btnExit = new JButton();
				getContentPane().add(btnExit);
				btnExit.setText("Exit");
				btnExit.setBounds(652, 537, 84, 33);
				btnExit.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						System.out.println("btnExit.mouseClicked, event="+evt);
						dispose();
					}
				});
			}
			pack();
			this.setSize(774, 628);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
