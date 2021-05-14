package model;

import java.sql.*;

public class UserManagement {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gbdb?useTimezone=true&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return con;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>User Name</th>" + "<th>User Type</th>" + "<th>Password</th>"
					+ "<th>Email</th>" + "<th>Phone</th>" + "<th>Address</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set

			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("userID"));
				String userName = rs.getString("userName");
				String userType = rs.getString("userType");
				String Password = rs.getString("Password");
				String Email = rs.getString("Email");
				String Phone = Integer.toString(rs.getInt("Phone"));
				String Address = rs.getString("Address");
				// Add into the html table
				output += "<td>" + userName + "</td>";
				output += "<td>" + userType + "</td>";
				output += "<td>" + Password + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Phone + "</td>";
				output += "<td>" + Address + "</td>";
				// buttons

				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-itemid='" + userID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-itemid='" + userID + "'></td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading ";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertUser(String userName, String userType, String Password, String Email, String Phone,
			String Address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user (`userID`,`userName`,`userType`,`Password`,`Email`,`Phone`,`Address`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, userName);
			preparedStmt.setString(3, userType);
			preparedStmt.setString(4, Password);
			preparedStmt.setString(5, Email);
			preparedStmt.setInt(6, Integer.parseInt(Phone));
			preparedStmt.setString(7, Address);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
//			output = "Inserted successfully";
		} catch (Exception e) {
			
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUser(String userID, String userName, String userType, String Password, String Email,
			String Phone, String Address) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE user SET userName=?,userType=?,Password=?,Email=?,Phone=?,Address=? WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, userType);
			preparedStmt.setString(3, Password);
			preparedStmt.setString(4, Email);
			preparedStmt.setInt(5, Integer.parseInt(Phone));
			preparedStmt.setString(6, Address);
			preparedStmt.setInt(7, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
//			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUser(String userID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from user where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}";
	} catch (Exception e) {
			output = "Error while deleting";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
