<%@ page import="java.util.List" %>
<%@ page import="util.Theater" %>

<!-- Assuming 'movies' is an attribute in the request -->
<%
	String selectedUserId = request.getParameter("selectedUserId");

    List<Theater> theaters = (List<Theater>) request.getAttribute("theaters");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>
    <h2>Theater</h2>
    <form action="/TicketBooking/UserMovieServlet2" method="post">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <% if (theaters != null && !theaters.isEmpty()) { %>
            <% for (Theater theater : theaters) { %>
                <button type="submit" name="selectedTheaterId" value="<%= theater.getId() %>">
                    <%= theater.getName() %>
                </button>
                <br><br>
            <% } %>
        <% } else { %>
            <p>No theater available</p>
        <% } %>
    </form>
     <br><br>
    <a href='/TicketBooking/User.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
