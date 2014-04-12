//Threaded Project Workshop-3, Team-5; 
//Version 1.0
//Created Date:March-15-2014
//Author:Porkodi 
//This is java, creating code for Travel Exports, using Oracle database

import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;


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
public class PackagePanel extends javax.swing.JPanel {

	private JComboBox cbPackage;
	private JLabel lblTitle;
	private JLabel lblprodSupplst;
	private JTextField txtPkgBasePri;
	private JLabel lblBasePri;
	private JTextField txtPkgDesc;
	private JLabel lblPkgDes;
	private JLabel lblPkgEdate;
	private JTextField txtPkgSdate;
	private JLabel lblPkgSdate;
	private JButton btnDel;
	private JButton btnMpkg;
	private JButton btnApkg;
	private JTextField txtPkgEdate; //package end date
	private JList lstProdSupp;
	private JTextField txtPkgAgyCom;
	private JLabel lblPAgyCom;
	private JTextField txtPkgNm;
	private JLabel lblPkgNm;

	//variable to connect database(Declared)
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public PackagePanel() {
		super();
		System.out.println("connecting database=20"); //--show  debug msg
		connect();
		System.out.println("calling initGUI");
		initGUI();
		populatePackage(getPackageId());
	}
	
	private void initGUI()
	{
		try
		{
			this.setLayout(null);
			//auto generate for getpackageIds combo box
			{
				ComboBoxModel cbProdIDModel = new DefaultComboBoxModel(getPackageIDs());
				cbPackage = new JComboBox();
				this.add(cbPackage);
				cbPackage.setModel(cbProdIDModel);
				cbPackage.setBounds(347, 23, 215, 29);
			}
			{
				lblTitle = new JLabel();
				this.add(lblTitle);
				this.add(getLblPkgNm());
				this.add(getTxtPkgNm());
				this.add(getLblprodSupplst());

				this.add(getBtnApkg());
				this.add(getBtnMpkg());
				this.add(getBtnDel());
				this.add(getLblPkgSdate());
				this.add(getTxtPkgSdate());
				this.add(getJLabel1());
				this.add(getLblPkgDes());
				this.add(getTxtPkgDesc());
				this.add(getLblBasePri());
				this.add(getTxtPkgBasePri());
				this.add(getLblPAgyCom());
				this.add(getTxtPkgAgyCom());
				this.add(getJList1());
				this.add(getJTextField1());
				lblTitle.setText("View Details for Package:");
				lblTitle.setBounds(143, 24, 192, 25);
				lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);

				cbPackage.addActionListener(new ActionListener() 
				{
					//once you add action listener it will appear
					@Override
					public void actionPerformed(ActionEvent e)
					{
						populatePackage(getPackageId()); 
					}
				});
			}
			this.setSize(688, 492);
			this.setPreferredSize(new java.awt.Dimension(703, 421));
		}
		catch (Exception e) 
		{
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public void connect() //This method connect to the database
	{
		System.out.println("Display connect-30"); // display debug msg
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:XE","ictoosd","ictoosd");
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
		}
		catch(ClassNotFoundException e)
		{
		e.printStackTrace();	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//get data from getpackageId
	private Vector<String> getPackageIDs()
	{
		System.out.println("Display-40"); //display error msg
		
		Vector<String> Dispackages = new Vector<String>();
		try
		{
			rs = stmt.executeQuery("Select PackageId, PkgName from Packages");
			while (rs.next())
			{
				Dispackages.add(rs.getString("PackageID") + " - "  + rs.getString("PkgName"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return Dispackages;
	}
	
	//Update the window with details for the input package.
	public void populatePackage(int id)
	{
		System.out.println("Getting data from database-50"); //display error msg
		
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		
		try
		{
			// Update the combo box.
			ComboBoxModel cbProdIDModel = new DefaultComboBoxModel(getPackageIDs());
			cbPackage.setModel(cbProdIDModel);
			
			// Select the input package.
			for (int i = 0; i < cbPackage.getItemCount(); i++) {
				int nItem = Integer.parseInt(cbPackage.getItemAt(i).toString().substring(0,cbPackage.getItemAt(i).toString().indexOf(" ")));
				if (nItem == id) {
					cbPackage.setSelectedIndex(i);
					break;
				}
			}
			
			// Get the package details.
			rs = stmt.executeQuery("SELECT * FROM packages where PackageID =" + id); 
			if (rs.next())
			{
				txtPkgNm.setText(rs.getString(2));
				
				String Sdate = df.format(rs.getDate(3));
				txtPkgSdate.setText(Sdate);
				
				String Edate = df.format(rs.getDate(4));
				txtPkgEdate.setText(Edate);
				
				txtPkgDesc.setText(rs.getString(5));
				
				double baseprice = Double.parseDouble(rs.getString(6));  
				txtPkgBasePri.setText(defaultFormat.format(baseprice ));
				
				double Agencycomm = Double.parseDouble(rs.getString(7));
				txtPkgAgyCom.setText(defaultFormat.format(Agencycomm));
				
				// Update the product supplier list.
				updateProdSuppList();	
			}
			else
			{
				System.out.println("No rows returned");
			}
			
			rs.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	private int getPackageId() {
		int id = Integer.parseInt(cbPackage.getSelectedItem().toString().substring(0,cbPackage.getSelectedItem().toString().indexOf(" ")));
		return id;
	}
	
// getting data from the oracle/sql database
	private Vector<String> getProducts()
	{
		System.out.println("Display-80"); //debug msg
		
		Vector<String> DisplyProducts = new Vector<String>();
		try
		{
			System.out.println("Inside get products 100 " + cbPackage.getSelectedItem()); // display debug msg
			
			
			rs = stmt.executeQuery("SELECT p.packageId, pps.productsupplierId,"
		               + " ps.productId, pr.prodname"
			           + " FROM packages p, packages_products_suppliers pps,"
		               + " products_suppliers ps, products pr"
		               + " where  p.PackageID = pps.packageId"
		               + " and pps.productsupplierId = ps.productsupplierId"
		               + " and ps.productId = pr.productId"
		               + " and p.PackageId = "+ getPackageId());
			while (rs.next())
			{
				System.out.println("Display-80A, assigning products"); //display error msg
				DisplyProducts.add(rs.getString("prodname"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return DisplyProducts;
	}

 //get package name
	private JLabel getLblPkgNm()
	{
		if(lblPkgNm == null) 
		{
			lblPkgNm = new JLabel();
			lblPkgNm.setText("Package Name:");
			lblPkgNm.setBounds(28, 93, 120, 15);
			lblPkgNm.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPkgNm;
	}
	
	private JTextField getTxtPkgNm()
	{
		if(txtPkgNm == null)
		{
			txtPkgNm = new JTextField();
			txtPkgNm.setBounds(156, 89, 164, 22);
			txtPkgNm.setEditable(false);
		}
		return txtPkgNm;
	}
     //This is for product- supplier title label
	private JLabel getLblprodSupplst()
	{
		if(lblprodSupplst == null)
		{
			lblprodSupplst = new JLabel();
			lblprodSupplst.setText("Products in this package (with suppliers):");
			lblprodSupplst.setBounds(346, 85, 320, 27);
			lblprodSupplst.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return lblprodSupplst;
	}
	
	
	//This Button for AddPackage form 

	private JButton getBtnApkg() 
	{
		if(btnApkg == null)
		{
			btnApkg = new JButton();
			btnApkg.setText("Add Package");
			btnApkg.setBounds(155, 358, 155, 35);

			btnApkg.addMouseListener(new MouseAdapter()
			{
				public void mouseClicked(MouseEvent evt)
				{
					btnAddMouseClicked();
				}
			});
		}
		return btnApkg;  //add package button
	}
	
	// This is for Modify button form 
	private JButton getBtnMpkg()
	{
		if(btnMpkg == null) 
		{
			btnMpkg = new JButton();
			btnMpkg.setText("Modify Package");
			btnMpkg.setBounds(333, 358, 155, 35);
			
			btnMpkg.addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent evt)
				{
					btnModifyMouseClicked();
				}
			});
		}
		return btnMpkg;
	}
	
	private void btnAddMouseClicked()
	{
		PackageAddModify addModifyPackage = new PackageAddModify(this, -1);
		addModifyPackage.setVisible(true);
	}
	
	private void btnModifyMouseClicked()
	{
		PackageAddModify addModifyPackage = new PackageAddModify(this, getPackageId());
		addModifyPackage.setVisible(true);
	}

	//This is Delete button 
	private JButton getBtnDel() 
	{
		if(btnDel == null)
		{
			btnDel = new JButton();
			btnDel.setText("Delete Package");
			btnDel.setBounds(511, 358, 155, 35);
			
			btnDel.addMouseListener(new MouseAdapter()
			{
					public void mouseClicked(MouseEvent evt)
					{
						deletePackages();
						ComboBoxModel cbProdIDModel = new DefaultComboBoxModel(getPackageIDs());
						cbPackage.setModel(cbProdIDModel);
						//clearfields();
						//initGUI();
						//Packages inst = new Packages();
					}
			});
		
		}
		return btnDel;
	}
	//this is for delete packages	
	public void deletePackages()
	{
		String DeltSQL = " delete from  Packages where packageId =" 
				       +  getPackageId();
		int numRows; // to find how many rows deleted from table
		try 
		{
			numRows = stmt.executeUpdate(DeltSQL); //returns the number of rows deleted
			if (numRows == 0)
			{
				System.out.println(" No Rows Deleted");
			}
			else
			{
				stmt.executeQuery("Commit");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	//this is clear when delete  the fields
	public void clearfields()
	{		
		txtPkgNm.setText(null);
		txtPkgDesc.setText(null);
		txtPkgBasePri.setText(null);
		txtPkgAgyCom.setText(null);
		lstProdSupp = null;
		
		ComboBoxModel cbProdIDModel = new DefaultComboBoxModel(getPackageIDs());
		cbPackage.setModel(cbProdIDModel);
		
	}
	private void btnExitMouseClicked(MouseEvent evt)
	{
		System.exit(0);
	}
	
		
	//getting supplier data  form data base
	private Vector<String> getSuppliers()
	{
		System.out.println("Display-110"); //display error msg
		
		Vector<String> DisplySupp = new Vector<String>();
		try
		{
					
			rs = stmt.executeQuery("select pr.prodname || ' / '  || s.supName  Prod_supp_Name"
		               + " FROM packages p, packages_products_suppliers pps,"
		               + " products_suppliers ps, suppliers s,products pr"
		               + " where p.PackageID           = pps.packageId"
		               + " and   pps.productsupplierId = ps.productsupplierId"
		               + " and   s.supplierId          = ps.supplierId"
		               + " and   pr.productId          = ps.productId"
		               + " and   p.packageId           = "+ getPackageId());
			while (rs.next())
			{
				System.out.println("Display-120, assigning suppliers"); //display debug msg
				DisplySupp.add(rs.getString("Prod_supp_Name"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return DisplySupp;
	}
	// this field for Package start date
	private JLabel getLblPkgSdate()
	{
		if(lblPkgSdate == null)
		{
			lblPkgSdate = new JLabel();
			lblPkgSdate.setText("Start Date:");
			lblPkgSdate.setBounds(28, 130, 120, 15);
			lblPkgSdate.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPkgSdate;
	}
	
	private JTextField getTxtPkgSdate()  
	{
		if(txtPkgSdate == null) 
		{
			txtPkgSdate = new JTextField();
			txtPkgSdate.setBounds(156, 126, 164, 22);
			txtPkgSdate.setEditable(false);
		}
		return txtPkgSdate;
	}
	
	//this is for end date
	private JLabel getJLabel1() 
	{
		if(lblPkgEdate == null)
		{
			lblPkgEdate = new JLabel();
			lblPkgEdate.setText("End Date:");
			lblPkgEdate.setBounds(27, 167, 120, 15);
			lblPkgEdate.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPkgEdate;
	}
// this is for package description
	private JLabel getLblPkgDes()
	{
		if(lblPkgDes == null) 
		{
			lblPkgDes = new JLabel();
			lblPkgDes.setText("Description:");
			lblPkgDes.setBounds(27, 285, 120, 15);
			lblPkgDes.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPkgDes;
	}
	
	private JTextField getTxtPkgDesc() 
	{
		if(txtPkgDesc == null)
		{
			txtPkgDesc = new JTextField();
			txtPkgDesc.setBounds(156, 284, 510, 53);
			txtPkgDesc.setHorizontalAlignment(SwingConstants.CENTER);
			txtPkgDesc.setEditable(false);
		}
		return txtPkgDesc;
	}
	
	//this is for package base price
	private JLabel getLblBasePri()
	{
		if(lblBasePri == null)
		{
			lblBasePri = new JLabel();
			lblBasePri.setText("Base Price:");
			lblBasePri.setBounds(27, 204, 120, 15);
			lblBasePri.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblBasePri;
	}
	
	private JTextField getTxtPkgBasePri() 
	{
		if(txtPkgBasePri == null) 
		{
			txtPkgBasePri = new JTextField();
			txtPkgBasePri.setBounds(156, 200, 164, 22);
			txtPkgBasePri.setEditable(false);
		}
		return txtPkgBasePri;
	}
	//this is for Agency commission
	private JLabel getLblPAgyCom()
	{
		if(lblPAgyCom == null)
		{
			lblPAgyCom = new JLabel();
			lblPAgyCom.setText("Commission:");
			lblPAgyCom.setBounds(27, 241, 120, 15);
			lblPAgyCom.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		return lblPAgyCom;
	}
	
	private JTextField getTxtPkgAgyCom()
	{
		if(txtPkgAgyCom == null) 
		{
			txtPkgAgyCom = new JTextField();
			txtPkgAgyCom.setBounds(156, 238, 162, 22);
			txtPkgAgyCom.setEditable(false);
		}
		return txtPkgAgyCom;
	}
	
	//this is for display a products and suppliers list
	private JList getJList1()
	{
		if(lstProdSupp == null)
		{
			ListModel jList1Model = new DefaultComboBoxModel(new String[] { " " });
			lstProdSupp = new JList();
			lstProdSupp.setEnabled(false);
			lstProdSupp.setModel(jList1Model);
			lstProdSupp.setBounds(345, 117, 321, 146);
			lstProdSupp.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
		}
		return lstProdSupp;
	}
	
	//update the product and supplier list when package is selected
	private void updateProdSuppList()
	{
			
			ListModel jList1Model = new DefaultComboBoxModel(getSuppliers());
			if (jList1Model == null)
			System.out.println("Jlist Model = null");
			if (lstProdSupp == null)
				System.out.println("lstProdSupp = null");
			lstProdSupp.setModel(jList1Model);
			lstProdSupp.setBounds(345, 114, 321, 146);
			lstProdSupp.setBorder(new LineBorder(new java.awt.Color(0,0,0), 1, false));
	}
		
	//this is for package end date text field
	private JTextField getJTextField1() 
	{
		if(txtPkgEdate == null)
		{
			txtPkgEdate = new JTextField();
			txtPkgEdate.setBounds(156, 163, 164, 22);
			txtPkgEdate.setEditable(false);
		}
		return txtPkgEdate;
	}

}
