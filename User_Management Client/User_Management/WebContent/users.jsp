<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "model.UserManagement" %>
      
      
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>User Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/users.js"></script>
</head>

<body>
		<div class="container"><div class="row"><div class="col-6">
		<h1>User Management</h1>
		<form id="formItem" name="formItem">
			userName:
			<input id="userName" name="userName" type="text"class="form-control form-control-sm">
			<br> 
			userType:
			<input id="userType" name="userType" type="text"class="form-control form-control-sm">
			<br> 
			Password:
			<input id="Password" name="Password" type="text"class="form-control form-control-sm">
			<br> 
			Email:
			<input id="Email" name="Email" type="text"class="form-control form-control-sm">
			<br>
			Phone:
			<input id="Phone" name="Phone" type="text"class="form-control form-control-sm">
			<br>
			Address:
			<input id="Address" name="Address" type="text"class="form-control form-control-sm">
			<br>
			<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
			<input type="hidden" id="userID"name="userID" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divItemsGrid">
		<%
			UserManagement userObj = new UserManagement();
			out.print(userObj.readUsers());
		%>
		</div>
		</div> </div> </div>
</body>
</html>