<%@ page import="java.util.List" %>
<%@ page import="util.Theater" %>

<%
	String selectedUserId = request.getParameter("selectedUserId");
    int selectedMovieId = (int) request.getAttribute("selectedMovieId");
    List<Theater> theaters = (List<Theater>) request.getAttribute("theaters");
    String movieName = (String) request.getAttribute("movieName");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>
   <h2>Theaters for Movie</h2>
<form action="/TicketBooking/UserMoviesDatesServlet" method="post">
    <!-- Add a hidden input field to store the selected movie ID -->
    <input type="hidden" name="selectedMovieId" value="<%= selectedMovieId %>">
     <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    
    <% if (theaters != null && !theaters.isEmpty()) { %>
        <% for (Theater theater : theaters) { %>
            <button type="submit" name="selectedTheaterId" value="<%= theater.getId() %>">
                <%= theater.getName() %>
            </button><br><br>
        <% } %>
    <% } else { %>
        <p>No theaters available for the selected movie</p>
    <% } %>
</form>
<br><br>
    <a href='/TicketBooking/User.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
