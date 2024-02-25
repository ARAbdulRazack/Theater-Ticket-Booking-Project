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

    <h2>Movie Management Page</h2>	
      
	<form action="./ShowMoviesServlet" method="get">
	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Show Movies">
    <br><br>
    </form>

   	<a href='/TicketBooking/AddMovies.jsp?selectedUserId=<%= selectedUserId %>'>
   		<input type="submit" value="Add Movies">
	</a>
	<br><br>
    
	<form action="./ShowMoviesServlet2" method="get">
	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Remove Movie">
    <br><br>
    </form>
    
    <br><br>
    <a href='/TicketBooking/Owner.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
	 
</body>
</html>