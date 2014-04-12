/*
 * JAVA - Threaded Project
 * Team - 5
 * Author:Suparna Roychoudhury
 * Date Created:15th Mar 2014
 * About the file: Implementation of AssignCustomer Class which assigns new agent to customer
 * */

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
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
public class AgentDeactivate extends javax.swing.JDialog {
	
	private JComboBox cmbAgents;
	private JLabel lblAgent;
	private JButton jButton1;
	private JLabel jLabel1;
	private JButton btnCancel;
	private JButton btnSave;
	AgentPanel parent;
	AgentDB agentDB = new AgentDB();
	int oldAgentId;
	
	public AgentDeactivate(AgentPanel panel, int id) {
		
		super();
		agentDB.connect();//call to DB connect method before GUI is loaded
		this.setModal(true);//set dialog to modal
		parent = panel;//save the parent
		oldAgentId = id;//set local agentid to agentid passed from parent
		initGUI();
	}
	
	
	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				this.setTitle("Travel Experts");
				{
					//populate combobox with all active agents
					ComboBoxModel cmbAgentsModel = 
							new DefaultComboBoxModel(agentDB.getActiveAgentIds());
					cmbAgents = new JComboBox();
					getContentPane().add(cmbAgents);
					cmbAgents.setModel(cmbAgentsModel);
					cmbAgents.setBounds(40, 114, 343, 22);
				}
				{
					lblAgent = new JLabel();
					getContentPane().add(lblAgent);
					lblAgent.setText("<html><center>Please select a new agent for the customers of the agent who is being deactivated:</center></html>");
					lblAgent.setBounds(40, 64, 343, 45);
				}
				{
					btnSave = new JButton();
					getContentPane().add(btnSave);
					btnSave.setText("Save");
					btnSave.setBounds(121, 157, 86, 33);
					btnSave.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btnSaveMouseClicked(evt);
						}
					});
				}
				{
					btnCancel = new JButton();
					getContentPane().add(btnCancel);
					btnCancel.setText("Cancel");
					btnCancel.setBounds(222, 157, 83, 33);
					btnCancel.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btnCancelMouseClicked(evt);
						}
					});
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(jLabel1);
					jLabel1.setText("Deactivate Agent");
					jLabel1.setBounds(0, 7, 418, 48);
					jLabel1.setFont(new java.awt.Font("Dialog",1,20));
					jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
				}
			}
			this.setSize(428, 241);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnSaveMouseClicked(MouseEvent evt) {
		System.out.println("btnSave.mouseClicked, event="+evt);
		//DB call to deactivateAgent method with old and new agent id
		agentDB.deactivateAgent(oldAgentId, chunkId());
		JOptionPane.showMessageDialog(this, "All customers assigned to "+ cmbAgents.getSelectedItem().toString());
		// Update the parent
		parent.populateAgent(oldAgentId);
		//dispose current dialog
		dispose();
	}
	
	//method to chunk the agent id from the string in the combo box
	private int chunkId()
	{
		int id = Integer.parseInt(cmbAgents.getSelectedItem().toString().substring(0,cmbAgents.getSelectedItem().toString().indexOf(" ")));
		return id;
	}
	
	private void btnCancelMouseClicked(MouseEvent evt) {
		System.out.println("btnCancel.mouseClicked, event="+evt);
		dispose();
	}
	
	private void jButton1MouseClicked(MouseEvent evt) {
		System.out.println("jButton1.mouseClicked, event="+evt);
		//TODO add your code for jButton1.mouseClicked
	}

}//end of class
