package com;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Account;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/accounts")

public class AccountService {
	
	Account accountobj= new Account();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAccountDetails(){
			return accountobj.readAccountDetails();
	}
	
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAccountDetails(@FormParam("cname") String cname,@FormParam("email") String email,@FormParam("phoneNo") String phoneNo,@FormParam("password") String password)
	{
		System.out.println("insert service method");
		String output = accountobj.insertAccountDetails(cname, email, phoneNo, password);
		return output;
	}
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAccountDetails(String accountData)
	{
		//Convert the input string to a JSON object
		JsonObject jsonobj = new JsonParser().parse(accountData).getAsJsonObject();
	
		//Read the values from the JSON object
		String cid = jsonobj.get("cid").getAsString();
		String cname = jsonobj.get("cname").getAsString();
		String email = jsonobj.get("email").getAsString();
		String phoneNo = jsonobj.get("phoneNo").getAsString();
		String password = jsonobj.get("password").getAsString();
		
		
		String output = accountobj.updateAccountDetails(cid, cname, email, phoneNo, password);
		return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAccountDetails(String accountData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(accountData, "", Parser.xmlParser());
	
	//Read the value from the element <uid>
	String cid = doc.select("cid").text();
	String output = accountobj.deleteAccountDetails(cid);
	return output;
	}

}
