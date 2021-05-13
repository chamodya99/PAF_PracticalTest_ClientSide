package com;
import java.sql.*;
public class UserService
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", "");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readUsers()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>User Name</th><th>Email Address</th>" + "<th>Phone Number</th>" +"<th>User Name</th>"+"<th>Password</th>" +"<th>User Type</th>" + "<th>Update Account</th><th>Remove Account</th></tr>";
 String query = "select * from userm";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
	 String userid = Integer.toString(rs.getInt("userid"));
	 String name = rs.getString("name");
	 String email = rs.getString("email");
	 String phone = rs.getString("phone");
	 String uname = rs.getString("uname");
	 String password = rs.getString("password");
	 String utype = rs.getString("utype");
 // Add into the html table
 output += "<tr><td><input userid='hidUserUpdate'name='hiduseridUpdate'type='hidden' value='" + userid+ "'>" + name + "</td>";
 
 output += "<td>" + email + "</td>";
 output += "<td>" + phone + "</td>";
 output += "<td>" + uname + "</td>";
 output += "<td>" + password + "</td>";
 output += "<td>" + utype + "</td>";
 // buttons
output += "<td><input name='btnUpdate'type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove'type='button' value='Remove'class='btnRemove btn btn-danger'data-userid='" + userid + "'>" + "</td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the users.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertUser(String Name, String Email, String Phone, String Uname, String Password, String Utype)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 // create a prepared statement
 String query = " insert into userm(`userid`,`name`,`email`,`phone`,`uname`,`password`,`utype`)" + " values (?, ?, ?, ?, ?,?,?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, Name);
 preparedStmt.setString(3, Email);
 preparedStmt.setString(4, Phone);
 preparedStmt.setString(5, Uname);
 preparedStmt.setString(6, Password);
 preparedStmt.setString(7, Utype);
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newUsers = readUsers();
 output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the users.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateUser(String ID, String Name, String Email, String Phone, String Uname, String Password, String Utype)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for updating.";
 }
 // create a prepared statement
 String query = "UPDATE userm SET name=?,email=?,phone=?,uname=?,password=?,utype=? WHERE userid=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, Name);
 preparedStmt.setString(2, Email);
 preparedStmt.setString(3, Phone);
 preparedStmt.setString(4, Uname);
 preparedStmt.setString(5, Password);
 preparedStmt.setString(6, Utype );
 preparedStmt.setInt(7, Integer.parseInt(ID));

 // execute the statement
 preparedStmt.execute();
 con.close();
 String newUsers = readUsers();
 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String deleteUser(String userid)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for deleting.";
 }
 // create a prepared statement
 String query = "delete from userm where userid=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(userid));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newUsers = readUsers();
 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while deleting the user.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}