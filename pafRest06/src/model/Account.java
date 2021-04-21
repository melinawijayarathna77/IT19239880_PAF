package model;
import java.sql.*;

public class Account {
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
			public String insertAccountDetails(String Name, String Email, String PhoneNo, String Password) 
			 { 
				String output = ""; 
				try
				{ 
					Connection con = connect(); 
					if (con == null) 
					{return "Error while connecting to the database for inserting."; } 
					
			 // create a prepared statement
					String query = " insert into account(`cid`,`cname`,`email`,`phoneNo`,`password`)"+" values (?, ?, ?, ?, ?)"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					
			 // binding values
					preparedStmt.setInt(1, 0); 
					preparedStmt.setString(2, Name); 
					preparedStmt.setString(3, Email); 
					preparedStmt.setInt(4, Integer.parseInt(PhoneNo)); 
					preparedStmt.setString(5, Password); 
					
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
			
			public String readAccountDetails() 
			 { 
				String output = ""; 
				try
				{ 
					Connection con = connect(); 
					if (con == null) 
					{return "Error while connecting to the database for reading."; } 
					
					// Prepare the html table to be displayed
					output = "<table border='1'><tr>"
							+"<th>ID</th>"
							+"<th>Name</th>" 
							+"<th>Email</th>"
							+"<th>Phone No</th>"
							+"<th>Password</th>"
							+ "</tr>"; 
			 
					String query = "select * from account"; 
					Statement stmt = con.createStatement(); 
					ResultSet rs = stmt.executeQuery(query); 
					
					// iterate through the rows in the result set
					while (rs.next()) 
					{ 
						String cid = Integer.toString(rs.getInt("cid")); 
						String cname = rs.getString("cname"); 
						String email = rs.getString("email"); 
						String phoneNo = Integer.toString(rs.getInt("phoneNo")); 
						String password = rs.getString("password"); 
						
						// Add into the html table
						output += "<tr><td>" + cid + "</td>"; 
						output += "<td>" + cname + "</td>"; 
						output += "<td>" + email + "</td>"; 
						output += "<td>" + phoneNo + "</td>"; 
						output += "<td>" + password + "</td>";
						
					} 
					
					con.close(); 
					// Complete the html table
					output += "</table>"; 
				} 
				
				catch (Exception e) 
				{ 
					output = "Error while reading account information."; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
			 } 
			
			
			//update
			public String updateAccountDetails(String ID, String Name, String Email, String PhoneNo, String Password)
			{ 
				 String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 {return "Error while connecting to the database for updating."; } 
				 
					 // create a prepared statement
					 String query = "UPDATE  account SET cname=?,email=?,phoneNo=?,password=? WHERE cid=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
					 // binding values
					 preparedStmt.setString(1, Name); 
					 preparedStmt.setString(2, Email); 
					 preparedStmt.setInt(3, Integer.parseInt(PhoneNo));
					 preparedStmt.setString(4, Password); 
					 preparedStmt.setInt(5, Integer.parseInt(ID)); 
				 
					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Updated successfully"; 
				 } 
				 
				 catch (Exception e) 
				 { 
					 output = "Error while updating account information."; 
					 System.err.println(e.getMessage()); 
				 } 
				 return output; 
			} 
			
			
			
			//delete
			public String deleteAccountDetails(String cid) 
			{ 
				 String output = ""; 
				 try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 {return "Error while connecting to the database for deleting."; } 
					 
				 // create a prepared statement
				 String query = "delete from account where cid=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(cid)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 output = "Deleted successfully"; 
				 } 
				 
				 catch (Exception e) 
				 { 
				 output = "Error while deleting the account details."; 
				 System.err.println(e.getMessage()); 
				 } 
				 return output; 
				 } 
}
