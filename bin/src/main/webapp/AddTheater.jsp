<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="util.User" %>

<!-- Assuming 'movies' is an attribute in the request -->
<%
	String selectedUserId = request.getParameter("selectedUserId");
%>

<!DOCTYPE html>
<html>
<head>
<title>Ticket Booking</title>
</head>
<body>
		<h2>Add Theater Name:</h2>
		
<form action="/TicketBooking/AdminAddTheaterServlet" method="post">
    <label for="TheaterName">Enter theater name:</label>
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="text" id="TheaterName" name="TheaterName" required>
    <br><br>
    <button type="submit" name="bookSeatsButton">Add Theater</button>
</form>
        
</body>
</html>