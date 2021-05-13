<%@page import="com.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="views/bootstrap.min.css">
<script src="component/jquery-3.6.0.min.js"></script>
<script src="component/Users.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>User Profile Management</h1>
<form id="formUser" name="formUser">
 User First Name:
 <input id="username" name="username" type="text" class="form-control form-control-sm">
 <br> Email Address:
 <input id="email" name="email" type="text" class="form-control form-control-sm">
 <br> Phone Number:
 <input id="phone" name="phone" type="text" class="form-control form-control-sm">
 <br> User name:
 <input id="uname" name="uname" type="text" class="form-control form-control-sm">
 <br> Password:
 <input id="password" name="password" type="text" class="form-control form-control-sm">
 <br> User Type:
 <input id="utype" name="utype" type="text" class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
 <input type="hidden" id="hiduseridSave" name="hiduseridSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divUserGrid">
 <%
 UserService userObj = new UserService();
 out.print(userObj.readUsers());
 %>
</div>
</div> </div> </div>
</body>
</html>