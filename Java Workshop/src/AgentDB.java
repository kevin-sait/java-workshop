/*
 * JAVA - Threaded Project
 * Team - 5
 * Author:Suparna Roychoudhury
 * Date Created:15th Mar 2014
 * About the file: Implementation of AgentDB Class for all DB activities related to Agent
 * */


import java.sql.*;
import java.util.Vector;


public class AgentDB {
	
	//declaring all local variables
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
		
	public void connect()//method to establish DB connection
	{
        try {
			
        	Class.forName("oracle.jdbc.driver.OracleDriver");
	        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ictoosd","ictoosd");
            conn.setAutoCommit(false);//auto-commit is set to false here
	        stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//the method retrieves all agent ids from DB to populate the combo box
	public Vector<String> getAgentIds() {
		
		Vector<String> agents = new Vector<String>();
		try {
			rs = stmt.executeQuery("SELECT agentid,agtFirstName,agtLastName FROM agents ORDER BY agentid");
			while (rs.next())
			{
				agents.add(rs.getString("agentid")+" - "+rs.getString("agtFirstName")+" "+rs.getString("agtLastName"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return agents;//the method returns a vector of agents
	}
		
	//the method retrieves all active agent ids from DB to populate the combo box
	public Vector<String> getActiveAgentIds() {
		
		Vector<String> agents = new Vector<String>();
		try {
			rs = stmt.executeQuery("SELECT agentid,agtFirstName,agtLastName FROM agents WHERE agtposition <> 'Inactive'");
			while (rs.next())
			{
				agents.add(rs.getString("agentid")+" - "+rs.getString("agtFirstName")+" "+rs.getString("agtLastName"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return agents;//the method returns a vector of agents
	}
		
	//the method retrieves details of an Agent from DB
	public Agent getAgent(int agentId) {
		
		Agent newAgent = new Agent();
		newAgent.setAgentId(agentId);
		try {
			rs = stmt.executeQuery("SELECT agtfirstname,agtmiddleinitial,agtlastname,agtbusphone,"
					+"agtemail,agtposition,agencyid FROM agents WHERE agentid="+agentId);
			while (rs.next())
			{
				
				newAgent.setAgentFirstName(rs.getString(1));
				newAgent.setAgentMI(rs.getString(2));
				newAgent.setAgentLastName(rs.getString(3));
				newAgent.setAgentPhone(rs.getString(4));
				newAgent.setAgentEmail(rs.getString(5));
				newAgent.setAgentPosition(rs.getString(6));
				newAgent.setAgencyId(rs.getString(7));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return newAgent;//the method returns an instance of agent
	}
		
	//method to update agent record in DB
	public void updateAgent(Agent agent)
	{
		int numRows;
		//update sql statement constructed
        String sql = "UPDATE agents SET "
        	+ "agtfirstname='" + agent.getAgentFirstName()
        	+"', agtmiddleinitial='" + agent.getAgentMI()
        	+"', agtlastname='" + agent.getAgentLastName()
        	+"', agtbusphone='" + agent.getAgentPhone()
        	+"', agtemail='" + agent.getAgentEmail()
        	+"', agtposition='" + agent.getAgentPosition()
        	+"', agencyid='"+ agent.getAgencyId()
        	+"' WHERE agentid=" + agent.getAgentId();
        	
        System.out.println(sql);
        try 
        {
        	PreparedStatement preparedStatement = conn.prepareStatement(sql);
        	
        	// execute update SQL statement
        	numRows =  preparedStatement.executeUpdate();
        	if (numRows == 0)
        	{
        		System.out.println("update failed");
        	}
        	else
        	{
        		System.out.println("updated " + numRows + " row(s)");
        		conn.commit();
        	}
        } catch (SQLException e) {
			e.printStackTrace();
		}         	
        
	}
		
		
	//method to insert agent record in DB
	public void insertAgent(Agent agent)
	{
		int numRows;
		int agentId = 0;
		
		try {
			rs = stmt.executeQuery("SELECT MAX(agentid) maxid FROM agents");//select max agentid from DB
			while (rs.next())
			{
				agentId = rs.getInt("maxid");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		agentId = agentId + 1;//increment agent id by 1
		
		//System.out.println("Agent id is:"+agentId);
		
		//insert sql statement constructed
        String sql = "INSERT INTO agents VALUES( "
        	+ agentId
        	+ ", '" + agent.getAgentFirstName()
        	+"', '" + agent.getAgentMI()
        	+"', '" + agent.getAgentLastName()
        	+"', '" + agent.getAgentPhone()
        	+"', '" + agent.getAgentEmail()
        	+"', '" + agent.getAgentPosition()
        	+"', '"+ agent.getAgencyId()
        	+"')";
        	
        //System.out.println(sql);
        try 
        {
        	PreparedStatement preparedStatement = conn.prepareStatement(sql);
        	
        	// execute update SQL statement
        	numRows =  preparedStatement.executeUpdate();
        	if (numRows == 0)
        	{
        		System.out.println("insert failed");
        	}
        	else
        	{
        		System.out.println("insert " + numRows + " row(s)");
        		conn.commit();
        	}
        } catch (SQLException e) {
			e.printStackTrace();
		}         	
        
	}
				
		
	//method to deactivate agent record in DB
	public void deactivateAgent(int oldAgentId, int newAgentId)
	{
		int numRows;
		
		//update sql statement constructed
        String sql = "UPDATE agents SET agtposition='Inactive' WHERE AgentId=" + oldAgentId;
        	
        //System.out.println(sql);
        try 
        {
        	PreparedStatement preparedStatement = conn.prepareStatement(sql);
        	
        	// execute update SQL statement
        	numRows =  preparedStatement.executeUpdate();
        	if (numRows == 0)
        	{
        		System.out.println("update failed");
        	}
        	else
        	{
        		System.out.println("updated " + numRows + " row(s)");
        		conn.commit();
        	}
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
        
        sql = "UPDATE customers SET agentid="+newAgentId+" WHERE agentid=" + oldAgentId;
    	
        try 
        {
        	PreparedStatement preparedStatement = conn.prepareStatement(sql);        	
        	// execute update SQL statement
        	numRows =  preparedStatement.executeUpdate();
        	if (numRows == 0)
        	{
        		System.out.println("customer update failed");
        	}
        	else
        	{
        		System.out.println("customer updated " + numRows + " row(s)");
        		conn.commit();
        	}
        } catch (SQLException e) {
			e.printStackTrace();
		}         
        
	}
				
	//the method retrieves all agency ids from DB to populate the combo box
	public Vector<String> getAgencyIds() {
		
		Vector<String> agencies = new Vector<String>();
		try {
			rs = stmt.executeQuery("select agencyid from agencies");
			while (rs.next())
			{
				agencies.add(rs.getString("agencyid"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return agencies;//the method returns a vector of agency ids
	}
				
	
	//the method retrieves max agent id from DB
		public String getMaxAgentId() {
			
			String maxAgentId="";
			
			try {
				rs = stmt.executeQuery("SELECT MAX(agentid) FROM agents");
				while (rs.next())
				{
					
					maxAgentId = rs.getString(1);
					
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return maxAgentId;//the method returns an instance of agent
		}

}
