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

    <h2>Owner Home Page</h2>

	<form action="./ProfileServlet3" method="get">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Profile">
    <br><br>
    </form>	
    
    <form action="./RegistrationAdminServlet" method="get">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Registration form for Admin">
    <br><br>
    </form>	
    
 <!--     
    <a href='/TicketBooking/AddTheater.jsp?selectedUserId=<%= selectedUserId %>'>
    <input type="submit" value="Add Theater">
	</a>
	<br><br>
-->
	
	<form action="./ShowTheaterServlet" method="get">
	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    <input type="submit" value="Remove Theater">
    <br><br>
    </form>
	
	<a href='/TicketBooking/MovieManagement.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Movie Management">
	</a>
	<br><br>
	
    <a href='/TicketBooking/Login.jsp'><input type="submit" value="Logout"></a>
	<br><br>
	 
</body>
</html>