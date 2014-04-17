/*
 * JAVA - Threaded Project
 * Team - 5
 * Author:Suparna Roychoudhury
 * Date Created:15th Mar 2014
 * About the file: Implementation of AgentAddModify Class which adds/updates agent data
 * */

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.regex.*;


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
public class AgentAddModify extends javax.swing.JDialog {
	
	private JComboBox cmbAgencyId;
	private JLabel lblAgentAddModify;
	private JPanel jPanelMiddle;

	private JLabel jLabel1;

	private JButton btnSave;
	private JButton btnCancel;
	private JTextField txtPhone;
	private JPanel jPanelBottom;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JLabel lblMI;
	private JTextField txtMI;
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblPosition;
	private JTextField txtPosition;
	private JLabel lblAgencyId;
	private boolean addClicked;
	
	AgentDB agentDB = new AgentDB();
	Agent agent;
	AgentPanel parent;
		
	public AgentAddModify(AgentPanel panel, boolean bool, Agent agt) 
	{
		super();
		agentDB.connect();//call to DB connect method before GUI is loaded
		agent = agt;//set local agent to the agent passed from the parent
		addClicked = bool;
		parent = panel;
		this.setModal(true);//make dialog modal
		
		// Initialize the window.
		initGUI();

		// Update the heading text.
		lblAgentAddModify.setText("Add Agent");
		if (!addClicked)
		{
			lblAgentAddModify.setText("Modify Agent");
			populateAgent();//display agent data if modify button is clicked
		}
	}
	
	private void initGUI() 
	{
		try {
			{
				lblAgentAddModify = new JLabel();
				getContentPane().add(lblAgentAddModify);
				lblAgentAddModify.setText("Add Agent");
				lblAgentAddModify.setFont(new java.awt.Font("Dialog",1,20));
				lblAgentAddModify.setBounds(0, 5, 557, 50);
				lblAgentAddModify.setHorizontalAlignment(SwingConstants.CENTER);
			}
			{
				jPanelMiddle = new JPanel();
				getContentPane().add(jPanelMiddle);
				jPanelMiddle.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
				jPanelMiddle.setBounds(29, 61, 504, 298);
				jPanelMiddle.setLayout(null);
				{
					jLabel1 = new JLabel();
					jPanelMiddle.add(jLabel1);
					jLabel1.setText("* Required field");
					jLabel1.setBounds(336, 257, 144, 19);
					jLabel1.setForeground(new java.awt.Color(165,42,42));
					jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					lblFirstName = new JLabel();
					jPanelMiddle.add(lblFirstName);
					lblFirstName.setText("* First Name:");
					lblFirstName.setBounds(29, 23, 150, 21);
					lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					txtFirstName = new JTextField();
					jPanelMiddle.add(txtFirstName);
					txtFirstName.setBounds(191, 24, 278, 22);
				}
				{
					lblMI = new JLabel();
					jPanelMiddle.add(lblMI);
					lblMI.setText(" Middle Initial:");
					lblMI.setBounds(29, 58, 150, 21);
					lblMI.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					txtMI = new JTextField();
					jPanelMiddle.add(txtMI);
					txtMI.setBounds(191, 62, 278, 22);
				}
				{
					lblLastName = new JLabel();
					jPanelMiddle.add(lblLastName);
					lblLastName.setText("* Last Name:");
					lblLastName.setBounds(29, 99, 150, 21);
					lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					txtLastName = new JTextField();
					jPanelMiddle.add(txtLastName);
					txtLastName.setBounds(191, 100, 278, 22);
				}
				{
					lblPhone = new JLabel();
					jPanelMiddle.add(lblPhone);
					lblPhone.setText("* Phone (xxx-xxx-xxxx):");
					lblPhone.setBounds(2, 136, 177, 21);
					lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					txtPhone = new JTextField();
					jPanelMiddle.add(txtPhone);
					txtPhone.setBounds(191, 138, 278, 22);
				}
				{
					lblEmail = new JLabel();
					jPanelMiddle.add(lblEmail);
					lblEmail.setText("* Email:");
					lblEmail.setBounds(29, 175, 150, 21);
					lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					txtEmail = new JTextField();
					jPanelMiddle.add(txtEmail);
					txtEmail.setBounds(191, 176, 278, 22);
				}
				{
					lblPosition = new JLabel();
					jPanelMiddle.add(lblPosition);
					lblPosition.setText("* Position:");
					lblPosition.setBounds(29, 210, 150, 21);
					lblPosition.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					txtPosition = new JTextField();
					jPanelMiddle.add(txtPosition);
					txtPosition.setBounds(191, 214, 278, 22);
				}
				{
					lblAgencyId = new JLabel();
					jPanelMiddle.add(lblAgencyId);
					lblAgencyId.setText("* Agency Id:");
					lblAgencyId.setBounds(29, 255, 150, 21);
					lblAgencyId.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					getContentPane().setLayout(null);
					this.setTitle("Travel Experts");
					//populate combobox with all agency ids available
					ComboBoxModel cmbAgencyIdModel = 
					new DefaultComboBoxModel(
							agentDB.getAgencyIds());
					cmbAgencyId = new JComboBox();
					jPanelMiddle.add(cmbAgencyId);
					cmbAgencyId.setModel(cmbAgencyIdModel);
					cmbAgencyId.setBounds(191, 255, 106, 22);
					
				}
				{
					jPanelBottom = new JPanel();
					FlowLayout jPanelBottomLayout = new FlowLayout();
					jPanelBottomLayout.setHgap(15);
					getContentPane().add(jPanelBottom);
					jPanelBottom.setBounds(0, 375, 557, 41);
					jPanelBottom.setLayout(jPanelBottomLayout);
					{
						btnSave = new JButton();
						jPanelBottom.add(btnSave);
						btnSave.setText("Save");
						btnSave.setPreferredSize(new java.awt.Dimension(79, 33));
						btnSave.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								btnSaveMouseClicked(evt);
							}
						});
					}
					{
						btnCancel = new JButton();
						jPanelBottom.add(btnCancel);
						btnCancel.setText("Cancel");
						btnCancel.setPreferredSize(new java.awt.Dimension(99, 33));
						btnCancel.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent evt) {
								dispose();
							}
						});
					}
				}
			}
			this.setSize(567, 463);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void btnSaveMouseClicked(MouseEvent evt) 
	{
		System.out.println("btnSave.mouseClicked, event="+evt);
		//add/modify only after all validation passes
		if(validateFields())
		{
			Agent newAgent = new Agent();
			newAgent.setAgentId(agent.getAgentId());
			newAgent.setAgentFirstName(txtFirstName.getText());
			newAgent.setAgentMI(txtMI.getText());
			newAgent.setAgentLastName(txtLastName.getText());
			newAgent.setAgentPhone(txtPhone.getText());
			newAgent.setAgentEmail(txtEmail.getText());		
			newAgent.setAgentPosition(txtPosition.getText());
			newAgent.setAgencyId(cmbAgencyId.getSelectedItem().toString());
			if (addClicked)
			{
				agentDB.insertAgent(newAgent);//call DB class to insert new agent
				// Update the parent.
				parent.populateAgent(Integer.parseInt(agentDB.getMaxAgentId()));
			}
			else
			{
				agentDB.updateAgent(newAgent);//call DB class to update existing agent
				// Update the parent.
				parent.populateAgent(newAgent.getAgentId());
			}
					
			//dispose current dialog
			dispose();
		}

	}
	
	//populate all text fields from agent class
	private void populateAgent() 
	{
		txtFirstName.setText(agent.getAgentFirstName());
		txtMI.setText(agent.getAgentMI());
		txtLastName.setText(agent.getAgentLastName());
		txtPhone.setText(agent.getAgentPhone());
		txtEmail.setText(agent.getAgentEmail());
		txtPosition.setText(agent.getAgentPosition());
		cmbAgencyId.setSelectedItem(agent.getAgencyId());

	}
	
	//method to validate all fields-checks for all required field to be
	//populated and regex for phone and email
	private boolean validateFields()
	{
		if (txtFirstName.getText().trim().isEmpty() )
		{
			JOptionPane.showMessageDialog(this, "Enter first name");
			txtFirstName.requestFocus();
			return false;
		}
		else if(txtLastName.getText().trim().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Enter last name");
			txtLastName.requestFocus();
			return false;
			
		}
		else if(txtPhone.getText().trim().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Enter phone number");
			txtPhone.requestFocus();
			return false;
		}
		else 
		{
			Pattern patternPhone = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
		    Matcher matcherPhone = patternPhone.matcher(txtPhone.getText().trim());
			 
			Pattern patternPhone2 = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}");
		    Matcher matcherPhone2 = patternPhone2.matcher(txtPhone.getText().trim());
			 
		    if ((!matcherPhone.matches()) && (!matcherPhone2.matches())) 
		    {
		    	  JOptionPane.showMessageDialog(this, "Enter valid phone number");
		    	  txtPhone.requestFocus();
		    	  return false;
		    }					
			else if (txtEmail.getText().trim().isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Enter email id");
				txtEmail.requestFocus();
				return false;
			}
			else
			{
				Pattern patternEmail = java.util.regex.Pattern.compile(".+@travelexperts.com");
			    Matcher matcherEmail = patternEmail.matcher(txtEmail.getText().trim());
			 
			      if(!matcherEmail.matches())
			      {
			    	  JOptionPane.showMessageDialog(this, "Enter valid email id");
			    	  txtEmail.requestFocus();
			    	  return false;
			      }
			      else if(txtPosition.getText().trim().isEmpty())
			      {
			    	  JOptionPane.showMessageDialog(this, "Enter position");
						txtPosition.requestFocus();
						return false;
			      }
			      else
			      {
			    	  return true;
			      }
			}
		      
		}		
		
	}		
	
}
