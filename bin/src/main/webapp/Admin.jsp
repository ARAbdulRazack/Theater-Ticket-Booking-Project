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

    <h2>Admin Home Page</h2>
    
    <form action="./ProfileServlet2" method="get">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Profile">
    <br><br>
    </form>	

    <form action="UserMovieServlet3" method="post">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Show Theater & Book Movie">
    <br><br>
    </form>	
    
    <form action="./UserBookingServlet2" method="get">	
        <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <input type="submit" value="Booking History">
        <br><br>
    </form>
	
	<a href='/TicketBooking/TheaterManagement.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Theater Management">
	</a>
	<br><br>
	
    <a href='/TicketBooking/Login.jsp'><input type="submit" value="Logout"></a>
	<br><br>
	 
</body>
</html>