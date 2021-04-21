package model;

import java.sql.*;

public class Cart {

	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
	 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", ""); 
		} 
		
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	 } 
	
	
	//Insert
	public String insertCartDetails(String PID, String Ptype, String Pname, String Price) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
	 // create a prepared statement
			String query = " insert into cart(`cartid`,`pid`,`type`,`pname`,`price`)"+" values (?, ?, ?, ?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
	 // binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, PID); 
			preparedStmt.setString(3, Ptype); 
			preparedStmt.setString(4, Pname);  
			preparedStmt.setDouble(5, Double.parseDouble(Price)); 
			
	// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Added to cart successfully"; 
		} 
		
		catch (Exception e) 
		{ 
		 	output = "Error while adding the products to cart."; 
		 	System.err.println(e.getMessage()); 
		} 
	 return output; 
	} 
	
	
	//read
	
	public String readCartDetails() 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr>"
					+"<th>CartID</th>"
					+"<th>PID</th>"
					+"<th>Research product type</th>" 
					+"<th>Research product name</th>"
					+"<th>Price</th>"
					+ "</tr>"; 
	 
			String query = "select * from cart"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String cartid = Integer.toString(rs.getInt("cartid"));
				String pid = rs.getString("pid");
				String type = rs.getString("type"); 
				String pname = rs.getString("pname"); 
				String price = Double.toString(rs.getDouble("price")); 
				
				
				// Add into the html table
				output += "<tr><td>" + cartid + "</td>"; 
				output += "<td>" + pid + "</td>";
				output += "<td>" + type + "</td>"; 
				output += "<td>" + pname + "</td>"; 
				output += "<td>" + price + "</td>"; 
				
				
			} 
			
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		
		catch (Exception e) 
		{ 
			output = "Error while reading cart information."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	//update
	public String updateCartDetails(String CartID, String PID, String Ptype, String Pname, String Price)
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
		 
			 // create a prepared statement
			 String query = "UPDATE  cart SET pid=?,type=?,pname=?,price=? WHERE cartid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 // binding values
			 preparedStmt.setString(1, PID);
			 preparedStmt.setString(2, Ptype); 
			 preparedStmt.setString(3, Pname); 
			 preparedStmt.setDouble(4, Double.parseDouble(Price));
			 preparedStmt.setInt(5, Integer.parseInt(CartID));
			  
		 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 
		 catch (Exception e) 
		 { 
			 output = "Error while updating cart information."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	//delete
	public String deleteCartDetails(String cartid) 
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 
		 // create a prepared statement
		 String query = "delete from cart where cartid=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setString(1, cartid); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the cart details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
}
