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

    <h2>Theater Management Page</h2>	
      
<!--
	<a href='/TicketBooking/AddTheater.jsp?selectedUserId=<%= selectedUserId %>'>
    <input type="submit" value="Add Theater">
	</a>
	<br><br>
	
	<form action="./ShowTheaterServlet" method="get">
	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Remove Theater">
    <br><br>
    </form>
-->

	<form action="./AddMoviesInTheaterServlet" method="get">
	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Add Movie and Showtime in Theater">
    <br><br>
    </form>
    
	<form action="./RemoveMoviesInTheaterServlet" method="get">
	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Remove Movie and Showtime in Theater">
    <br><br>
    </form>
    
	<br><br>
    <a href='/TicketBooking/Admin.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
	 
</body>
</html>