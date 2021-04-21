package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;

import model.Payment;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/payments")

public class PaymentService {

Payment paymentobj= new Payment();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPaymentDetails(){
			return paymentobj.readPaymentDetails();
	}
	
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPaymentDetails(@FormParam("paytype") String paytype,@FormParam("amount") String amount,@FormParam("paydate") String paydate)
	{
		System.out.println("insert service method");
		String output = paymentobj.insertPaymentDetails(paytype, amount, paydate);
		return output;
	}
	
	
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePaymentDetails(String paymentData)
	{
		//Convert the input string to a JSON object
		JsonObject jsonobj = new JsonParser().parse(paymentData).getAsJsonObject();
	
		//Read the values from the JSON object
		String payid = jsonobj.get("payid").getAsString();
		String paytype = jsonobj.get("paytype").getAsString();
		String amount = jsonobj.get("amount").getAsString();
		String paydate = jsonobj.get("paydate").getAsString();
		
		
		String output = paymentobj.updatePaymentDetails(payid, paytype, amount, paydate);
		return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePaymentDetails(String paymentData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
	
	//Read the value from the element <uid>
	String payid = doc.select("payid").text();
	String output = paymentobj.deletePaymentDetails(payid);
	return output;
	}
}
