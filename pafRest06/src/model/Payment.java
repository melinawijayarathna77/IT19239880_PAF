package model;
import java.sql.*;

public class Payment {
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
	public String insertPaymentDetails(String Paytype, String Amount, String Paydate) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
	 // create a prepared statement
			String query = " insert into payment(`payid`,`paytype`,`amount`,`paydate`)"+" values (?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
	 // binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, Paytype); 
			preparedStmt.setDouble(3, Double.parseDouble(Amount));
			preparedStmt.setDate(4, Paydate); 
			
	// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		
		catch (Exception e) 
		{ 
		 	output = "Error while inserting the Details."; 
		 	System.err.println(e.getMessage()); 
		} 
	 return output; 
	} 
	
	
	//read
	
	public String readPaymentDetails() 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr>"
					+"<th>PayID</th>"
					+"<th>Payment Type</th>" 
					+"<th>Amount</th>"
					+"<th>Payment Date</th>"
					+ "</tr>"; 
	 
			String query = "select * from payment"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String payid = Integer.toString(rs.getInt("payid")); 
				String paytype = rs.getString("paytype"); 
				String amount = rs.getString("amount"); 
				//String paydate = Integer.toString(rs.getInt("paydate")); 
			
				// Add into the html table
				output += "<tr><td>" + payid + "</td>"; 
				output += "<td>" + paytype + "</td>"; 
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + paydate + "</td>"; 
				
			} 
			
			con.close(); 
			// Complete the html table
			output += "</table>"; 
		} 
		
		catch (Exception e) 
		{ 
			output = "Error while reading payment information."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	 } 
	
	
	//update
	public String updatePaymentDetails(String PayID, String PayType, String Amount, String PayDate)
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
		 
			 // create a prepared statement
			 String query = "UPDATE  payment SET paytype=?,amount=?,paydate=? WHERE payid=?"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			 // binding values
			 preparedStmt.setString(1, PayType);  
			 preparedStmt.setDouble(2, Double.parseDouble(Amount));
			 preparedStmt.setDate(3, PayDate); 
		 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Updated successfully"; 
		 } 
		 
		 catch (Exception e) 
		 { 
			 output = "Error while updating payment information."; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	} 
	
	
	
	//delete
	public String deletePaymentDetails(String payid) 
	{ 
		 String output = ""; 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; } 
			 
		 // create a prepared statement
		 String query = "delete from payment where payid=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(payid)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 output = "Deleted successfully"; 
		 } 
		 
		 catch (Exception e) 
		 { 
		 output = "Error while deleting the payment details."; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 }
}
