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


    <h2>Admin Registration</h2>

    <form action="./RegistrationAdminServlet" method="post">
     <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <label for="userName">User Name:</label>
        <input type="text" id="userName" placeholder="Enter Name" name="userName" required>
        <br><br>

        <label for="phoneNumber">Phone Number:</label>
        <input type="text" id="phoneNumber" placeholder="Enter Phone number" name="phoneNumber" required>
        <br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" placeholder="Enter Password" name="password" required>
        <br><br>

        <label for="isAdmin">Is Admin:</label>
        <input type="checkbox" id="isAdmin" name="isAdmin" value="true">
        <br><br>

        <label for="isOwner">Is Owner:</label>
        <input type="checkbox" id="isOwner" name="isOwner" value="true">
        <br><br><br><br>
        
        <h2>Create a Theater</h2>

        <label for="theaterName">Theater Name:</label>
        <input type="text" id="theaterName" placeholder="Enter Theater Name" name="theaterName" required>
        <br><br>

        <input type="submit" value="Register">
    </form>
    <br><br>
    <a href='/TicketBooking/Owner.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
