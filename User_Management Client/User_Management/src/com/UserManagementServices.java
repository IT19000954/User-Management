package com;

import model.UserManagement;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")

public class UserManagementServices {

	UserManagement user = new UserManagement();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUsers() {
		return user.readUsers();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(@FormParam("userName") String userName,
			@FormParam("userType") String userType,
			@FormParam("Password") String Password,
			@FormParam("Email") String Email,
			@FormParam("Phone") String Phone,@FormParam("Address") String Address) {
		String output = user.insertUser(userName, userType, Password, Email, Phone, Address);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData) {
		// Convert the input string to a JSON object
		JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		// Read the values from the JSON object

		String userID = userObject.get("userID").getAsString();
		String userName = userObject.get("userName").getAsString();
		String userType = userObject.get("userType").getAsString();
		String Password = userObject.get("Password").getAsString();
		String Email = userObject.get("Email").getAsString();
		String Phone = userObject.get("Phone").getAsString();
		String Address = userObject.get("Address").getAsString();
		String output = user.updateUser(userID, userName, userType, Password, Email, Phone, Address);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		// Read the value from the element <itemID>
		String UserID = doc.select("userID").text();
		String output = user.deleteUser(UserID);
		return output;
	}

}
