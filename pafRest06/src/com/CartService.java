package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Cart;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/carts")

public class CartService {

Cart cartobj= new Cart();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readcarttDetails(){
			return cartobj.readCartDetails();
	}
	
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCartDetails(@FormParam("pid") String pid,@FormParam("type") String type,@FormParam("pname") String pname,@FormParam("price") String price)
	{
		
		String output = cartobj.insertCartDetails(pid, type, pname, price);
		return output;
	}
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCartDetails(String cartData)
	{
		//Convert the input string to a JSON object
		JsonObject jsonobj = new JsonParser().parse(cartData).getAsJsonObject();
	
		//Read the values from the JSON object
		String cartid = jsonobj.get("cartid").getAsString();
		String pid = jsonobj.get("pid").getAsString();
		String type = jsonobj.get("type").getAsString();
		String pname = jsonobj.get("pname").getAsString();
		String price = jsonobj.get("price").getAsString();
		
		
		
		String output = cartobj.updateCartDetails(cartid,pid, type, pname, price);
		return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCartDetails(String cartData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(cartData, "", Parser.xmlParser());
	
	//Read the value from the element <uid>
	String cartid = doc.select("cartid").text();
	String output = cartobj.deleteCartDetails(cartid);
	return output;
	}
}
