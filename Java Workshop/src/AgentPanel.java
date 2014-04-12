/*
 * JAVA - Threaded Project
 * Team - 5
 * Author:Suparna Roychoudhury
 * Date Created:15th Mar 2014
 * About the file: Implementation of AgentFrame Class which shows details of the selected agent
 * */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister.Pack;


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
public class AgentPanel extends javax.swing.JPanel {

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	private static final long serialVersionUID = 1L;
	private JPanel jPanelTop;
	private JPanel jPanelBottom;
	private JPanel jPanelMiddle;

	private JButton btnAdd;
	private JButton btnDeactivate;
	private JButton btnModify;
	
	private JTextField txtAgencyId;
	private JTextField txtPosition;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtLastName;
	private JTextField txtMI;
	private JTextField txtFirstName;

	private JLabel lblFirstName;
	private JLabel lblAgencyId;
	private JLabel lblPosition;
	private JLabel lblEmail;
	private JLabel lblPhone;
	private JLabel lblLastName;
	private JLabel lblMI;
	private JLabel lblAgents;
	
	private JComboBox cmbAgents;
	
	private  boolean addClicked = false;
	private  int agentId;
	private  int newAgentId;

	AgentDB agentDB = new AgentDB();
	Agent agent = new Agent();	
	
	public AgentPanel() {
		super();
		agentDB.connect();
		initGUI();
		populateAgent(chunkId());
	}
	
	private void initGUI() {
		try {
			jPanelTop = new JPanel();
			this.setLayout(null);
			this.add(jPanelTop, "Center");
			jPanelTop.setBounds(107, 18, 530, 50);
			jPanelTop.setLayout(null);
			{
				lblAgents = new JLabel();
				jPanelTop.add(lblAgents);
				lblAgents.setText("View Details for Agent:");
				lblAgents.setBounds(7, 21, 183, 17);
				lblAgents.setHorizontalAlignment(SwingConstants.RIGHT);
			}
			{
				//populate combobox with available agents
				cmbAgents = new JComboBox();
				jPanelTop.add(cmbAgents);
				cmbAgents.setModel(new DefaultComboBoxModel(agentDB.getAgentIds()));
				cmbAgents.setBounds(201, 16, 246, 27);
				cmbAgents.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						populateAgent(chunkId());//display agent details
					}
				});
			}
			{
				jPanelMiddle = new JPanel();
				this.add(jPanelMiddle, "North");
				jPanelMiddle.setBounds(113, 73, 524, 282);
				jPanelMiddle.setLayout(null);
				{
					lblFirstName = new JLabel();
					jPanelMiddle.add(lblFirstName);
					lblFirstName.setText("First Name:");
					lblFirstName.setBounds(59, 14, 124, 15);
					lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					lblMI = new JLabel();
					jPanelMiddle.add(lblMI);
					lblMI.setText("Middle Initial:");
					lblMI.setBounds(59, 51, 124, 15);
					lblMI.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					lblLastName = new JLabel();
					jPanelMiddle.add(lblLastName);
					lblLastName.setText("Last Name:");
					lblLastName.setBounds(59, 89, 124, 15);
					lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					lblPhone = new JLabel();
					jPanelMiddle.add(lblPhone);
					lblPhone.setText("Business Phone:");
					lblPhone.setBounds(59, 127, 124, 15);
					lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					lblEmail = new JLabel();
					jPanelMiddle.add(lblEmail);
					lblEmail.setText("Email:");
					lblEmail.setBounds(59, 165, 124, 15);
					lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					lblPosition = new JLabel();
					jPanelMiddle.add(lblPosition);
					lblPosition.setText("Position:");
					lblPosition.setBounds(59, 203, 124, 15);
					lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					lblAgencyId = new JLabel();
					jPanelMiddle.add(lblAgencyId);
					lblAgencyId.setText("Agency Id:");
					lblAgencyId.setBounds(59, 241, 124, 15);
					lblAgencyId.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					txtFirstName = new JTextField();
					jPanelMiddle.add(txtFirstName);
					txtFirstName.setBounds(195, 11, 245, 22);
					txtFirstName.setEditable(false);
				}
				{
					txtMI = new JTextField();
					jPanelMiddle.add(txtMI);
					txtMI.setBounds(195, 48, 245, 22);
					txtMI.setEditable(false);
				}
				{
					txtLastName = new JTextField();
					jPanelMiddle.add(txtLastName);
					txtLastName.setBounds(195, 86, 245, 22);
					txtLastName.setEditable(false);
				}
				{
					txtPhone = new JTextField();
					jPanelMiddle.add(txtPhone);
					txtPhone.setBounds(195, 124, 245, 22);
					txtPhone.setEditable(false);
				}
				{
					txtEmail = new JTextField();
					jPanelMiddle.add(txtEmail);
					txtEmail.setBounds(195, 162, 245, 22);
					txtEmail.setEditable(false);
				}
				{
					txtPosition = new JTextField();
					jPanelMiddle.add(txtPosition);
					txtPosition.setBounds(195, 200, 245, 22);
					txtPosition.setEditable(false);
				}
				{
					txtAgencyId = new JTextField();
					jPanelMiddle.add(txtAgencyId);
					txtAgencyId.setBounds(195, 238, 245, 22);
					txtAgencyId.setEditable(false);
				}
			}
			
			{
				jPanelBottom = new JPanel();
				this.add(jPanelBottom, "West");
				jPanelBottom.setLayout(null);
				jPanelBottom.setBounds(84, 338, 576, 67);
				{
					btnAdd = new JButton();
					jPanelBottom.add(btnAdd, "Center");
					btnAdd.setText("Add Agent");
					btnAdd.setBounds(25, 23, 155, 33);
					btnAdd.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btnAddMouseClicked(evt);
						}
					});
				}
				{
					btnModify = new JButton();
					jPanelBottom.add(btnModify, "North");
					btnModify.setText("Modify Agent");
					btnModify.setBounds(196, 23, 155, 33);
					btnModify.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btnModifyMouseClicked(evt);
						}
					});
				}
				{
					btnDeactivate = new JButton();
					jPanelBottom.add(btnDeactivate, "West");
					btnDeactivate.setText("Deactivate Agent");
					btnDeactivate.setBounds(367, 23, 165, 33);
					btnDeactivate.addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent evt) {
							btnDeactivateMouseClicked(evt);
						}
					});
				}
			}
			this.setSize(650, 404);
			this.setPreferredSize(new java.awt.Dimension(672, 422));
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void populateAgent(int id) 
	{
		//update the combo data
		agent = agentDB.getAgent(id);
		cmbAgents.setModel(new DefaultComboBoxModel(agentDB.getAgentIds()));
		
		//select the input item
		for (int i = 0; i < cmbAgents.getItemCount(); i++) {
			int nItem = Integer.parseInt(cmbAgents.getItemAt(i).toString().substring(0,cmbAgents.getItemAt(i).toString().indexOf(" ")));
			if (nItem == id) {
				cmbAgents.setSelectedIndex(i);
				break;
			}
		}

		//set the text fields with agent details
		txtFirstName.setText(agent.getAgentFirstName());
		txtMI.setText(agent.getAgentMI());
		txtLastName.setText(agent.getAgentLastName());
		txtPhone.setText(agent.getAgentPhone());
		txtEmail.setText(agent.getAgentEmail());
		txtPosition.setText(agent.getAgentPosition());
		txtAgencyId.setText(agent.getAgencyId());
		
		//check if the deactivate button should be enabled
		if(agent.getAgentPosition().equals("Inactive"))
		{
			btnDeactivate.setEnabled(false);		
		}
		else
		{
			btnDeactivate.setEnabled(true);	
		}
	}
	
	private void btnAddMouseClicked(MouseEvent evt) {
		
		addClicked = true;
		//open instance of AddModifyAgent dialog and set visibility true
		AgentAddModify addModifyAgent = new AgentAddModify(this,addClicked,agent);
		addModifyAgent.setVisible(true);
	}
	
	private void btnModifyMouseClicked(MouseEvent evt) {
		
		addClicked = false;
		agent.setAgentId(chunkId());//set the agent id to the selected one
		//open instance of AddModifyAgent dialog and set visibility true
		AgentAddModify addModifyAgent = new AgentAddModify(this,addClicked,agent);
		addModifyAgent.setVisible(true);
	}
	
	private void btnDeactivateMouseClicked(MouseEvent evt) {
			
		//open instance of AssignCustomer dialog and set visibility true
		agent.setAgentId(chunkId());//set the agent id to the selected one
		AgentDeactivate assignCustomer = new AgentDeactivate(this,chunkId());
		assignCustomer.setVisible(true);
		
	}

	//method to chunk the agent id from the string in the combo box
	private int chunkId()
	{
		int id = Integer.parseInt(cmbAgents.getSelectedItem().toString().substring(0,cmbAgents.getSelectedItem().toString().indexOf(" ")));
		return id;
	}
	
}
