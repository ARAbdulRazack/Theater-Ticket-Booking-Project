<%@ page import="java.util.List" %>
<%@ page import="util.Movie" %>
<%@ page import="util.Theater" %>

<%
	String selectedUserId = request.getParameter("selectedUserId");
    int selectedTheaterId = (int) request.getAttribute("selectedTheaterId");
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
    Theater theaterName = (Theater) request.getAttribute("theater");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>
   <h2>Movies in Theater</h2>
<form action="/TicketBooking/UserMoviesDatesServlet" method="post">
    <!-- Add a hidden input field to store the selected theater ID -->
    <input type="hidden" name="selectedTheaterId" value="<%= selectedTheaterId %>">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
    
    <% if (movies != null && !movies.isEmpty()) { %>
        <% for (Movie movie : movies) { %>
            <button type="submit" name="selectedMovieId" value="<%= movie.getId() %>">
                <%= movie.getName() %>
            </button><br><br>
        <% } %>
    <% } else { %>
        <p>No movies available at this theater</p>
    <% } %>
</form>
<br><br>
    <a href='/TicketBooking/User.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
