package model;

import java.sql.*;

public class CusLogin {
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

	public boolean Login(String cname,String password) {
		String output = "";
		Connection con=null;
	   try{           
		    con = connect();
			if (con == null) {
				output= "Error while connecting to the database for updating.";
			}
			
	       PreparedStatement pst = con.prepareStatement("Select * from account where cname=? and password=?");
	       pst.setString(1, cname); 
	       pst.setString(2, password);
	       ResultSet rs = pst.executeQuery();
	      boolean apple=rs.next();
	      con.close();
	       if(apple) {
	    	   
	    	   System.out.println(cname);
	       
	           return true;  
	       }
	       else {
	    	   return false;  
	      
	       }
	      
	   }
	   catch(Exception e){
	       e.printStackTrace();
	       return false;
	   } 
	  
	}
	
	
}
