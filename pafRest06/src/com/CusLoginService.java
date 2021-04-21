package com;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import model.CusLogin;


@Path("/logins")
public class CusLoginService {
	
CusLogin loginobj= new  CusLogin();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String Login(@FormParam("cname") String cname,@FormParam("password") String password)
	{
		
		boolean output = loginobj.Login(cname, password);
		if(output) {
			return "Login success";
		}
		else {
			return "Login Failed";
		}
		
	}

}
