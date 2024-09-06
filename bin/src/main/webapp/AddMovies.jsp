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
<meta charset="UTF-8">
<title>Ticket Booking</title>
</head>
<body>
		<h2>Add Moive</h2>
		
<form action="/TicketBooking/AdminAddMoviesServlet" method="post">
	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <label for="MovieName">Enter Movie name:</label>
    <input type="text" id="MovieName" name="MovieName" required>
    <br><br>
    
    <label for="DirectorName">Enter Director name:</label>
    <input type="text" id="DirectorName" name="DirectorName" >
    <br><br>
    
    <label for="HeroName">Enter Hero name:</label>
    <input type="text" id="HeroName" name="HeroName" >
    <br><br>
    <button type="submit" name="bookSeatsButton">Add Movies</button>
</form>

	<br><br>
    <a href='/TicketBooking/Owner.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
        
</body>
</html>