<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="util.User" %>

<%
	String selectedUserId = request.getParameter("selectedUserId");    
%>

<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h2>Admin Profile</h2>

    <%-- Retrieve user details from the request attribute --%>
    <%
        User userDetails = (User) request.getAttribute("userDetails");
        if (userDetails != null) {
    %>
                <p>Name: <%= userDetails.getName() %><br></p>
                <p>Phone Number: <%= userDetails.getPhoneNumber() %><br></p>
    <%
        } else {
    %>
        <p>User details not available</p>
    <%
        }
    %>

    <br>
    <a href='/TicketBooking/Admin.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
    
</body>
</html>
