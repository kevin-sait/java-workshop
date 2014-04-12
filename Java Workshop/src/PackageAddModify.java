import com.cloudgarden.layout.AnchorLayout;
/****************************************************
Application Name: Travel Expert Information System
                  (Threaded Project phase 3)
Class Name: PackageAddMod                                 
Module Name: PackageAddMod.java
Purpose of this module: 
 - display, add, modify travel package data
 - display available products / suppliers data,
   select product / supplier data which associate
   with the package.

Author : Ryuji Sasaki                            
Create Date: 29/03/2014
*****************************************************/
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;

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
 * PackageAddMod class
 */
public class PackageAddModify extends javax.swing.JFrame {
	
	// private valiables
	private JTextField txtPkgName;
	private ButtonGroup btnGrp1;
	private JScrollPane jScrollPane2;
	private JPanel jPanel1;
	private JLabel lblPackageAddModify;
	private JList lstProSup;
	private JLabel lblDesc;
	private JButton btnCancel;
	private JButton btnSave;
	private JScrollPane jScrollPane1;
	private JLabel lblProSup;
	private JList lstPrdSup;
	private JPanel jPnlButtons;
	private JTextField txtAgcyCms;
	private JTextField txtBasePrice;
	private JTextField txtEndDate;
	private JTextField txtStartDate;
	private JPanel jPnlTextFields;
	private JLabel lblAgcyCms;
	private JLabel lblBasePrice;
	private JLabel lblEndDate;
	private JLabel lblStartDate;
	private JPanel jPnlLabels;
	private JLabel lblPkgName;
	private JDateChooser dcStartDate;
	private JDateChooser dcEndDate;
	private int packageID;			// package id from package screen
	private String actionMode;		// action mode from clicked button in package screen
	private ResultSet rs;
	private ResultSet rsSel;
	private String[] Items;			// array for all available products /suppliers list box
	private String[] selectItems;   // array for products /suppliers associated to the package id
	private int[] selectIdx;		// array for selected index in products /suppliers list box
	private int[] lstVals;			// array for productsupplierID of all available products /suppliers
	private JTextArea taDescription;
	private PackagePanel parent;
	
	/**
	 * constructor
	 */
	public PackageAddModify(PackagePanel panel, int id) {
		
		parent = panel;
		packageID = id;
		
		// Update the action mode.
		actionMode = "mod";
		if (id < 0) {
			actionMode = "add";
		}
		
		// Initialize the window.
		initGUI();
		
		// Update the heading text.
		lblPackageAddModify.setText("Modify Package");
		if (id < 0) {
			lblPackageAddModify.setText("Add Package");
		}
	}
	
	/**
	 * Initialize GUI
	 */
	private void initGUI() {
		try {
			getContentPane().setLayout(null);
			this.setTitle("Travel Experts");			
			{
				
				// Panel Labels
				jPnlLabels = new JPanel();
				GridLayout jPnlLabelsLayout = new GridLayout(5, 1);
				jPnlLabelsLayout.setColumns(1);
				jPnlLabelsLayout.setHgap(5);
				jPnlLabelsLayout.setVgap(15);
				jPnlLabelsLayout.setRows(5);
				jPnlLabels.setLayout(jPnlLabelsLayout);
				getContentPane().add(jPnlLabels);
				jPnlLabels.setBounds(63, 114, 145, 206);
				{
					// Package Name
					lblPkgName = new JLabel();
					jPnlLabels.add(lblPkgName);
					lblPkgName.setText("Package Name:");
					lblPkgName.setBounds(23, 5, 87, 15);
					lblPkgName.setHorizontalTextPosition(SwingConstants.LEADING);
					lblPkgName.setHorizontalAlignment(SwingConstants.RIGHT);
					lblPkgName.setPreferredSize(new java.awt.Dimension(201, 42));
					
				}
				{
					// Start Date
					lblStartDate = new JLabel();
					jPnlLabels.add(lblStartDate);
					lblStartDate.setText("Start Date:");
					lblStartDate.setBounds(23, 82, 71, 15);
					lblStartDate.setPreferredSize(new java.awt.Dimension(69, 15));
					lblStartDate.setHorizontalAlignment(SwingConstants.RIGHT);
					
				}
				{
					// End Date
					lblEndDate = new JLabel();
					jPnlLabels.add(lblEndDate);
					lblEndDate.setText("End Date:");
					lblEndDate.setBounds(23, 118, 63, 15);
					lblEndDate.setPreferredSize(new java.awt.Dimension(67, 15));
					lblEndDate.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					// Base Price
					lblBasePrice = new JLabel();
					jPnlLabels.add(lblBasePrice);
					lblBasePrice.setText("Base Price:");
					lblBasePrice.setBounds(23, 157, 71, 15);
					lblBasePrice.setPreferredSize(new java.awt.Dimension(69, 15));
					lblBasePrice.setHorizontalAlignment(SwingConstants.RIGHT);
				}
				{
					// Agent Commission
					lblAgcyCms = new JLabel();
					jPnlLabels.add(lblAgcyCms);
					lblAgcyCms.setText("Agency Commission:");
					lblAgcyCms.setBounds(23, 188, 87, 15);
					lblAgcyCms.setHorizontalAlignment(SwingConstants.RIGHT);
					lblAgcyCms.setPreferredSize(new java.awt.Dimension(124, 100));
				}
			
			   // Panel TextFields
				jPnlTextFields = new JPanel();
			   GridLayout jPnlTextFieldsLayout = new GridLayout(5, 1);
				jPnlTextFieldsLayout.setColumns(1);
				jPnlTextFieldsLayout.setHgap(5);
				jPnlTextFieldsLayout.setVgap(15);
				jPnlTextFieldsLayout.setRows(5);
				getContentPane().add(jPnlTextFields);
				jPnlTextFields.setBounds(220, 116, 211, 206);
				jPnlTextFields.setLayout(jPnlTextFieldsLayout);
				{
					// Package Name
					txtPkgName = new JTextField();
					jPnlTextFields.add(txtPkgName);
					txtPkgName.setBounds(12, 1, 170, 20);
					txtPkgName.setPreferredSize(new java.awt.Dimension(149, 22));
					txtPkgName.setSize(211, 30);
					
				}
				{	
					// Start Date
					dcStartDate = new JDateChooser();
					jPnlTextFields.add(dcStartDate);
					dcStartDate.setBounds(12, 79, 170, 20);					
				}
				{	
					// End Date
					dcEndDate = new JDateChooser();
					jPnlTextFields.add(dcEndDate);
					dcStartDate.setBounds(12, 116, 170, 20);
					dcStartDate.setRequestFocusEnabled(false);

				}
				{	// Base Price
					txtBasePrice = new JTextField();
					jPnlTextFields.add(txtBasePrice);
					txtBasePrice.setBounds(12, 152, 170, 20);
					txtBasePrice.setPreferredSize(new java.awt.Dimension(145, 22));
					txtBasePrice.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent evt) {
							txtBasePriceFocusLost(evt);
						}
					});

				}
				{	// Agency Commission
					txtAgcyCms = new JTextField();
					jPnlTextFields.add(txtAgcyCms);
					txtAgcyCms.setBounds(12, 186, 170, 20);
					txtAgcyCms.setPreferredSize(new java.awt.Dimension(147, 22));
					txtAgcyCms.setSize(211, 30);
					txtAgcyCms.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent evt) {
							txtAgcyCmsFocusLost(evt);
						}
					});

				}
			
				// Panel Buttons
				jPnlButtons = new JPanel();
				jPnlButtons.setLayout(null);
				getContentPane().add(jPnlButtons);
				getContentPane().add(getLblDesc());
				getContentPane().add(getJScrollPane1());
				getContentPane().add(getLblProSup());

				jPnlButtons.setBounds(476, 491, 344, 39);
				{	
					// Cancel button
					btnCancel = new JButton();
					jPnlButtons.add(btnCancel);
					btnCancel.setText("Cancel");
					btnCancel.setBounds(245, 0, 99, 33);

					// mouse listener
					btnCancel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						System.out.println("btnCancel.mouseClicked, event="+evt);
						// end this application
						dispose();
					}
				 });
				}
				{	// Save button
					btnSave = new JButton();
					jPnlButtons.add(btnSave);
					btnSave.setText("Save");
					btnSave.setBounds(128, 0, 101, 33);
					btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						btnSaveActionPerformed(evt);
					}
				 });				
				}
				
				// Call DisplayPackageData Function
				DisplayPackageData();
				
				// Call DisplayProductsSuppliersList function
				DisplayProductsSuppliersList();
				
				// create product/supplier JList
				getContentPane().add(getJScrollPane2());
			}
		
			pack();
			this.setSize(859, 581);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	// label Description
	private JLabel getLblDesc() {
		if(lblDesc == null) {
			lblDesc = new JLabel();
			lblDesc.setText("Description:");
			lblDesc.setBounds(71, 341, 137, 30);
			lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDesc.setVerticalAlignment(SwingConstants.TOP);
		}
		return lblDesc;
	}
	// scrollpane for description text area
	private JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(221, 344, 569, 94);
			jScrollPane1.setViewportView(getTaDescription());
		}
		return jScrollPane1;
	}

	// label of Products / Suppliers list
	private JLabel getLblProSup() {
		if(lblProSup == null) {
			lblProSup = new JLabel();
			lblProSup.setText("Available products (with suppliers)");
			lblProSup.setBounds(475, 120, 315, 15);
		}
		return lblProSup;
	}
	
	/**
    * Display Package Data
    */
	private void DisplayPackageData() {
	
		try {
			// if the action mode is add, exit here
			if(actionMode.equals("add")){
				return;
			}
			
			// creates DB connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			// ***Select package data
			// Select SQL
			String sql = "select ";
			sql += "pkgname,pkgstartdate,pkgenddate, ";
			sql += "pkgbaseprice,pkgagencycommission,pkgdesc ";
			sql += "from packages ";
			sql += "where ";
			sql += "packageid = " + packageID;
			
			// executes SQL
			stmt.execute(sql);
				
			// get the resultset
			ResultSet rs = stmt.executeQuery(sql);
			
			// display package data
			if (rs.next()){
				// package name
				txtPkgName.setText(rs.getString("pkgname").toString());
				
				// Start date
				String dateValue = rs.getString("pkgstartdate").toString();
				Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
				dcStartDate.setDate(startDate);
				
				// End date
				dateValue = rs.getString("pkgenddate").toString(); 
				Date endtDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateValue);
				dcEndDate.setDate(endtDate);
				
				// base price
				DecimalFormat df = new DecimalFormat("0.00");
				txtBasePrice.setText("$" + df.format(Double.parseDouble(rs.getString("pkgbaseprice").toString())));
				
				// agency commission
				txtAgcyCms.setText("$" + df.format(Double.parseDouble(rs.getString("pkgagencycommission").toString())));
				
				// description
				taDescription.setText(rs.getString("pkgdesc").toString());
				
			}	
				conn.close();				
				
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	
	}
	
	/**
	* Display Product/Supplier List
	*/
	private void DisplayProductsSuppliersList() {
		
		try {
			// creates DB connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
			// *** get  the all product/supplier data for Jlist				
			String sql = "SELECT DISTINCT ";
			sql += "ps.ProductSupplierId, "; 
			sql += "ps.ProductId,";
			sql += "ps.SupplierId,";
			sql += "p.ProdName,";
			sql += "s.SupName ";
			sql += "FROM ";
			sql += "packages_products_suppliers pps,";
			sql += "products_suppliers ps,"; 
			sql += "products p, "; 
			sql += "suppliers s ";
			sql += "WHERE "; 
			sql += "pps.ProductSupplierId = ps.ProductSupplierId ";
			sql += "AND ";
			sql += "ps.ProductID = p.ProductID ";
			sql += "AND ";
			sql += "ps.SupplierID = s.SupplierID "; 
			sql += " ORDER BY "; 
			sql += "p.ProdName,s.SupName";
			
			// executes SQL
			rs = stmt.executeQuery(sql);			
			
			// get result count
			int cnt = 0;
			while (rs.next()){					
				cnt++;
			}
			
			System.out.println("allCnt=" + (cnt));
			
			// move resultset to beforefirst
		    rs.beforeFirst();
			
			
			Items = new String[cnt]; // create array of list item text (used to compare method for selected items)
			lstVals = new int[cnt];  // create array of list item value (used in save method)
			
			int i = 0;
			// create defaultlistmodel
			DefaultListModel model = new DefaultListModel();
			
			// loop through product/supplier data
			while (rs.next()){
								
				System.out.println(rs.getString("ProductId").toString());
				System.out.println(rs.getString("SupplierId").toString());
				System.out.println(rs.getString("ProdName").toString());
				System.out.println(rs.getString("SupName").toString());	

				// set data to defaultlistmodel
				model.addElement(rs.getString("ProdName").toString() + " / " + rs.getString("SupName").toString());
				
				// set data to array of list item text
				Items[i] = rs.getString("ProdName").toString() + " / " + rs.getString("SupName").toString();
				
				// set data to array of list item value
				lstVals[i] = Integer.parseInt(rs.getString("ProductSupplierId"));					
				i++;
				
			}
			
						
			// *** get  product/supplier data which is associated to the package ID
			sql = "SELECT DISTINCT ";
			sql += "ps.ProductSupplierId, "; 
			sql += "ps.ProductId,";
			sql += "ps.SupplierId,";
			sql += "p.ProdName,";
			sql += "s.SupName ";
			sql += "FROM ";
			sql += "packages_products_suppliers pps,";
			sql += "products_suppliers ps,"; 
			sql += "products p, "; 
			sql += "suppliers s ";
			sql += "WHERE "; 
			sql += "pps.ProductSupplierId = ps.ProductSupplierId ";
			sql += "AND ";
			sql += "ps.ProductID = p.ProductID ";
			sql += "AND ";
			sql += "ps.SupplierID = s.SupplierID "; 
			sql += "AND ";
			sql += "pps.PackageId = "+ packageID;
			sql += " ORDER BY "; 
			sql += "p.ProdName,s.SupName";
			
			// executes SQL
			rsSel = stmt.executeQuery(sql);				
		
			// get result count
			int selectCnt = 0;			
			while (rsSel.next()){
				selectCnt++;
			}
			
			System.out.println("selectCnt=" + selectCnt);
			// move resultset to beforefirst
			rsSel.beforeFirst();
			
			System.out.println("rsSel.beforeFirst()");
			
			// create array of selected list item 
			selectItems = new String[selectCnt];
			i = 0;
			
			// loop through the selected product/supplier data associated to Package ID
			while (rsSel.next()){
			
				// set data to array of selected item                   
				selectItems[i] = rsSel.getString("ProdName").toString() + " / " + rsSel.getString("SupName").toString();
				i++;
			}
		
			
			System.out.println("-----start compare");
			
			// create array of selected index
			selectIdx = new int[selectCnt];
			int j =0;
			
			// loop through the all product/supplier data
			for(int idx = 0; idx < cnt; idx++){
//				private JTextArea taDescription;
//				private JTextArea jTextArea1;

				System.out.println("Items[" + idx + "] = " + Items[idx]);
				
				// loop through the product/supplier data associated to the package ID
				for(int sel = 0; sel < selectCnt;sel ++) {
					System.out.println("selectItems[" + sel + "] = " + selectItems[sel]);
					
					// if item matches, add to the array of selected index
					if(Items[idx].toString().equals(selectItems[sel].toString())){
						System.out.println("---selectEDIdx = " + idx);
						selectIdx[j] = idx;
						
						j++;
					}
				}
				
			}					
			conn.close();
				
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	
		}
	
	
	/**
    * Save button click
    */
	private void btnSaveActionPerformed(ActionEvent evt) {
		
		// validate the input data
		if(validateFields()) {
			
			// if action mode is 'add'
			if(actionMode.equals("add")){
				// call insert function 
				InsertPackageProductsSuppliers();
				
				// if action mode is 'mod'
			} else if (actionMode.equals("mod")) {
				// call update function
				UpdatePackageProductsSuppliers();
			}
			
			// Update the parent.
			parent.populatePackage(packageID);
			
			//dispose current dialog
			dispose();
		}
		
	}
	
	/**
	* Validate input data
	*/
	private boolean validateFields()
	{
		//***mandatory check
		// package name
		if (txtPkgName.getText().trim().isEmpty() )
		{
			JOptionPane.showMessageDialog(this, "Enter package name");
			txtPkgName.requestFocus();
			return false;
		}
		
		// Base Price
		if (txtBasePrice.getText().trim().replace("$", "").isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Enter base price");
			txtBasePrice.requestFocus();
			return false;
		}
		
		// Agency commission
		if (txtAgcyCms.getText().trim().replace("$", "").isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Enter agency commission");
			txtAgcyCms.requestFocus();
			return false;
		}
		
		// Description
		if (taDescription.getText().trim().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Enter description");
			taDescription.requestFocus();
			return false;
		}
		
		//*** Max length check
		// package name [VARCHAR2(100)]
		if (txtPkgName.getText().trim().length() > 100 )
		{
			JOptionPane.showMessageDialog(this, "Enter package name within 100 characters.");
			txtPkgName.requestFocus();
			return false;
		}
		// Base Price [NUMBER(19,4)]
		if (txtBasePrice.getText().trim().replace("$", "").length() > 20 )
		{
			JOptionPane.showMessageDialog(this, "Enter base price within 20 digits.");
			txtBasePrice.requestFocus();
			return false;
		}
		
		// Agency commission [NUMBER(19,4)]
		if (txtAgcyCms.getText().trim().replace("$", "").length() > 20 )
		{
			JOptionPane.showMessageDialog(this, "Enter agency commission within 20 digits.");
			txtAgcyCms.requestFocus();
			return false;
		}
		
		// Description [VARCHAR2(100)]
		if (taDescription.getText().trim().length() > 100 )
		{
			JOptionPane.showMessageDialog(this, "Enter description within 100 characters.");
			taDescription.requestFocus();
			return false;
		}
		
		// *** format check
		// Start Date
		if (!isValidStartDate())
		{
			JOptionPane.showMessageDialog(this, "Enter valid start date");
			txtBasePrice.requestFocus();
			return false;
		}
		
		// End Date
		if (!isValidEndDate())
		{
			JOptionPane.showMessageDialog(this, "Enter valid end date");
			txtBasePrice.requestFocus();
			return false;
		}		
		
		// Base Price [NUMBER(19,4)]
		if (!isNum(txtBasePrice.getText().trim().replace("$", "")))
		{
			JOptionPane.showMessageDialog(this, "Base price needs to be numeric.");
			txtBasePrice.requestFocus();
			return false;
		}
		
		// Agency commission [NUMBER(19,4)]
		if (!isNum(txtAgcyCms.getText().trim().replace("$", "")))
		{
			JOptionPane.showMessageDialog(this, "Agency commission needs to be numeric.");
			txtAgcyCms.requestFocus();
			return false;
		}
		
		//*** Relation check
		//Agency commission can not be greater than base price
		if(Double.parseDouble(txtAgcyCms.getText().trim().replace("$", "")) >
		   Double.parseDouble(txtBasePrice.getText().trim().replace("$", ""))) {
			JOptionPane.showMessageDialog(this, "Agency commission can not be greater than base price.");
			txtAgcyCms.requestFocus();
			return false;
		}
		
		// End date must be later than start date
		if(!checkDateOrder()) {
			JOptionPane.showMessageDialog(this, "End date must be later than start date.");
			txtAgcyCms.requestFocus();
			return false;
		}
		
		
		return true;
	}
	
	/**
	* Numeric check
	*/
	public boolean isNum(String strNum) {
	    boolean ret = true;
	    try {

	        Double.parseDouble(strNum);

	    }catch (NumberFormatException e) {
	        ret = false;
	    }
	    
	    return ret;
	}
	
	/**
	* Validate start date format
	*/
	public boolean isValidStartDate() {
		
	    try {
	    	
	    	// create date format of selected start date
			Calendar startDate = dcStartDate.getCalendar();
			String startDateStr = startDate.get(Calendar.YEAR) + "-" 
			                  + (startDate.get(Calendar.MONTH) + 1) + "-" 
					          + startDate.get(Calendar.DAY_OF_MONTH); 
	    	
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	// if the date can be parsed, return true
	    	// if it can not, exception error occur, and return false
	        df.parse(startDateStr);	        
	        
	        return true;
	        
	    }catch (ParseException e) {
	        return false;
	
		}catch (NullPointerException e) {
	        return false;
	  
		}catch (Exception e) {
	        return false;
	    }
	}
	
	/**
	* Validate end date format
	*/
	public boolean isValidEndDate() {
		
	    try {
	    	
	    	// create date format of selected end date
			Calendar endDate = dcEndDate.getCalendar();
			String endDateStr = endDate.get(Calendar.YEAR) + "-" 
			                  + (endDate.get(Calendar.MONTH) + 1) + "-" 
					          + endDate.get(Calendar.DAY_OF_MONTH);   
	    	
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	// if the date can be parsed, return true
	    	// if it can not, exception error occur, and return false
	        df.parse(endDateStr);	        
	        
	        return true;
	        
	    }catch (ParseException e) {
	        return false;
	
		}catch (NullPointerException e) {
	        return false;
	  
		}catch (Exception e) {
	        return false;
	    }
	}
	
	/**
	* Validate the order of start date and end date
	*  - end date must be later than start date
	*/
	public boolean checkDateOrder() {
	    boolean ret = true;
	    try {
	    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	
	    	// create date format of selected  dates
			Calendar startDate = dcStartDate.getCalendar();
 			Calendar endDate = dcEndDate.getCalendar();
 			
 			if (startDate.before(endDate)){
 				return true;
 			} else {
 				return false;
 			}	        
	  
		}catch (Exception e) {
	        return false;
	    }
	}
	
	/**
	* Insert package, Product/Supplier to tables
	*/
	private void InsertPackageProductsSuppliers() {
		
		try {
			// creates DB connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");
			Statement stmt = conn.createStatement();
						
			// create date format of selected start date
			Calendar startDate = dcStartDate.getCalendar();
			String startDateStr = startDate.get(Calendar.YEAR) + "/" 
			                  + (startDate.get(Calendar.MONTH) + 1) + "/" 
					          + startDate.get(Calendar.DAY_OF_MONTH)
					          + " 00:00:00";
			
			// create date format of selected end date
			Calendar endtDate = dcEndDate.getCalendar();
			String endDateStr = endtDate.get(Calendar.YEAR) + "/" 
			                  + (endtDate.get(Calendar.MONTH) + 1) + "/" 
					          + endtDate.get(Calendar.DAY_OF_MONTH)
					          + " 00:00:00";
			
			System.out.println("dcStartDate=" + startDateStr);
			
			// Get the new package id.
			packageID = getMaxPackageId() + 1;
			
			// INSERT SQL
			String sql = "insert into packages ";
			sql += "(";
			sql += "packageid,";
			sql += "pkgagencycommission,";
			sql += "pkgbaseprice,";
			sql += "pkgdesc,";
			sql += "pkgenddate,";
			sql += "pkgname,";
			sql += "pkgstartdate";
			sql += ") values (";
			sql += packageID + ",";
			sql += txtAgcyCms.getText().replace("$", "") + ",";
			sql += txtBasePrice.getText().replace("$", "") + ",";
			sql += "'" + taDescription.getText() + "',";
			sql += "TO_DATE('" + endDateStr + "', 'yyyy/mm/dd hh24:mi:ss')" + ",";
			sql += "'" + txtPkgName.getText() + "',";
			sql += "TO_DATE('" + startDateStr + "', 'yyyy/mm/dd hh24:mi:ss')";
			sql += ")";
			
			System.out.println(sql);
			// executes SQL
			int rowsInserted = stmt.executeUpdate(sql);
			
			// no record updated
			if (rowsInserted == 0)
			{
				//JOptionPane.showMessageDialog(null, "Zero row inserted.");
			// recored updated
			} else {
				//JOptionPane.showMessageDialog(null, rowsInserted + " rows successfully inserted.");
			}
			
			// get selected list item index
			int[] selIdx = lstProSup.getSelectedIndices();		
			
			// loop through selected list items
			int rowsPpsInserted = 0;
			for ( int i = 0; i < selIdx.length; i++){			
			
					// Insert to Packages_Products_Suppliers
					// INSERT SQL
					sql = "insert into Packages_Products_Suppliers ";
					sql += "(";
					sql += "packageid,";
					sql += "productSupplierid";
					sql += ") values (";
					sql += packageID + ",";
					sql += lstVals[selIdx[i]]; // sets the productsupplierID of selected item
					sql += ")";
					
					System.out.println(sql);
					// executes SQL
					rowsPpsInserted += stmt.executeUpdate(sql);
			  }	
			  conn.close();	

		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	/**
	* Update package, Product/Supplier to tables
	*/
	private void UpdatePackageProductsSuppliers() {
		
		try {
			// creates DB connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");
			Statement stmt = conn.createStatement();
					
						
			// create date format of selected start date
			Calendar startDate = dcStartDate.getCalendar();
			String startDateStr = startDate.get(Calendar.YEAR) + "/" 
			                  + (startDate.get(Calendar.MONTH) + 1) + "/" 
					          + startDate.get(Calendar.DAY_OF_MONTH)
					          + " 00:00:00";
			
			// create date format of selected end date
			Calendar endtDate = dcEndDate.getCalendar();
			String endDateStr = endtDate.get(Calendar.YEAR) + "/" 
			                  + (endtDate.get(Calendar.MONTH) + 1) + "/" 
					          + endtDate.get(Calendar.DAY_OF_MONTH)
					          + " 00:00:00";
			
						
			// UPDATE SQL
			String sql = "update packages ";
			sql += "set ";
			sql += "pkgagencycommission = " + txtAgcyCms.getText().replace("$", "") + ",";
			sql += "pkgbaseprice = " + txtBasePrice.getText().replace("$", "") + ",";
			sql += "pkgdesc = " + "'" + taDescription.getText() + "',";
			sql += "pkgenddate = " + "TO_DATE('" + endDateStr + "', 'yyyy/mm/dd hh24:mi:ss')" + ",";
			sql += "pkgname = " + "'" + txtPkgName.getText() + "',";
			sql += "pkgstartdate = " + "TO_DATE('" + startDateStr + "', 'yyyy/mm/dd hh24:mi:ss')" ;
			sql += "where ";
			sql += "packageid = " + packageID;
			
			System.out.println(sql);
			// executes SQL
			int rowsUpdated = stmt.executeUpdate(sql);
			
			// no record updated
			if (rowsUpdated == 0)
			{
				//JOptionPane.showMessageDialog(null, "Zero row Updated.");
			// recored updated
			} else {
				//JOptionPane.showMessageDialog(null, rowsUpdated + " rows successfully Updated.");
			}
			
			//***Delete and Insert PackageProductsSuppliers	
						
			// *Delete SQL
			sql = "delete from packages_products_suppliers ";			
			sql += "where ";
			sql += "packageid = " + packageID;
			
			System.out.println(sql);
			
			// executes SQL
			int rowsDeleted = stmt.executeUpdate(sql);
			
			// no record Deleted 
			if (rowsDeleted == 0)
			{
				//JOptionPane.showMessageDialog(null, "Zero row Deleted.");
			// recored Deleted
			} else {
				//JOptionPane.showMessageDialog(null, rowsDeleted + " rows  Deleted.");
			}
			
			// **Insert
			// get selected list item index
			int[] selIdx = lstProSup.getSelectedIndices();		
			
			// loop through selected list items
			int rowsPpsInserted = 0;
			for ( int i = 0; i < selIdx.length; i++){			
			
					// Insert to Packages_Products_Suppliers
					// INSERT SQL
					sql = "insert into Packages_Products_Suppliers ";
					sql += "(";
					sql += "packageid,";
					sql += "productSupplierid";
					sql += ") values (";
					sql += packageID + ",";
					sql += lstVals[selIdx[i]]; // sets the productsupplierID of selected item
					sql += ")";
					
					System.out.println(sql);
					// executes SQL
					rowsPpsInserted += stmt.executeUpdate(sql);
			  }	
			  conn.close();	

		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	/**
	* get the max packageId for insert to packages table
	*/
	private int getMaxPackageId() {
		
		int maxPackageId = 0;
		
		try {
			// creates DB connection
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ictoosd", "ictoosd");
			Statement stmt = conn.createStatement();
					
			// get max row count
			String sql = "select count(*) cnt from packages";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
			
				maxPackageId = Integer.parseInt(rs.getString("cnt"));
				System.out.println("rowCnt=" +maxPackageId);
			}
			
			conn.close();
			
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
		return maxPackageId;
	}
	
	//label mode title

	// label mode

	// description text are
	private JTextArea getTaDescription() {
		if(taDescription == null) {
			taDescription = new JTextArea();
			taDescription.setText("");
			taDescription.setBounds(223, 344, 510, 90);
		}
		return taDescription;
	}
	
	/**
	* base price lost focus
	*/
	private void txtBasePriceFocusLost(FocusEvent evt) {
		
		if (isNum(txtBasePrice.getText().trim().replace("$", ""))){
			DecimalFormat df = new DecimalFormat("0.00");
			txtBasePrice.setText("$" + df.format(Double.parseDouble(txtBasePrice.getText().trim().replace("$", ""))));
		}
		
	}
	
	/**
	* Agency commission lost focus
	*/
	private void txtAgcyCmsFocusLost(FocusEvent evt) {
		if (isNum(txtAgcyCms.getText().trim().replace("$", ""))){
			DecimalFormat df = new DecimalFormat("0.00");
			txtAgcyCms.setText("$" + df.format(Double.parseDouble(txtAgcyCms.getText().trim().replace("$", ""))));
		}
	}
	
	private JLabel getLblPackageAddModify() {
		if(lblPackageAddModify == null) {
			lblPackageAddModify = new JLabel();
			lblPackageAddModify.setText("Add Package");
			lblPackageAddModify.setBounds(0, 12, 853, 68);
			lblPackageAddModify.setFont(new java.awt.Font("Dialog",1,20));
			lblPackageAddModify.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPackageAddModify;
	}
	
	private JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBounds(36, 82, 784, 387);
			jPanel1.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
			jPanel1.setLayout(null);
			jPanel1.setOpaque(false);
		}
		return jPanel1;
	}
	
	private JScrollPane getJScrollPane2() {
		if(jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(475, 146, 315, 176);
			{
				lstProSup = new JList(Items);
				jScrollPane2.setViewportView(lstProSup);
				lstProSup.setBounds(475, 146, 315, 176);
				lstProSup.clearSelection();
				// if the action mode is add
				if(actionMode.equals("mod")){
					lstProSup.setSelectedIndices(selectIdx);
				}					
				getContentPane().add(getLblPackageAddModify());
				getContentPane().add(getJPanel1());
			}
		}
		return jScrollPane2;
	}

}
