<%@ page import="java.util.List" %>
<%@ page import="util.Movie" %>

<!-- Assuming 'movies' is an attribute in the request -->
<%
	String selectedUserId = request.getParameter("selectedUserId");    
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>
    <h2>Movies</h2>
    <form action="/TicketBooking/UserTheaterServlet" method="post">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <% if (movies != null && !movies.isEmpty()) { %>
            <% for (Movie movie : movies) { %>
                <button type="submit" name="selectedMovieId" value="<%= movie.getId() %>">
                    <%= movie.getName() %>
                </button>
                <br><br>
            <% } %>
        <% } else { %>
            <p>No movies available</p>
        <% } %>
    </form>
    <br><br>
    <a href='/TicketBooking/User.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
