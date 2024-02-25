<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <h2>User Home Page</h2>	
    
    <form action="./ProfileServlet" method="get">
        <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <input type="submit" value="Profile">
        <br><br>
    </form>	
      
    <form action="./UserMoviesServlet" method="get">
        <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <input type="submit" value="Show Movies & Book Movie">
        <br><br>
    </form>

    <form action="./UserTheaterServlet2" method="get">
        <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <input type="submit" value="Show Theater & Book Movie">
        <br><br>
    </form>
    
    <form action="./UserBookingServlet" method="get">	
        <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <input type="submit" value="Booking History">
        <br><br>
    </form>
    
    <a href='/TicketBooking/Login.jsp'><input type="submit" value="Logout"></a>
    <br><br>
     
</body>
</html>
