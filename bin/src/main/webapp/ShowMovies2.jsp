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
    <h2>Remove Movies</h2>
    
    <form action="/TicketBooking/RemoveMovieServlet" method="post">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <% if (movies != null && !movies.isEmpty()) { %>
            <% for (Movie movie : movies) { %>
                <label>
                    <input type="checkbox" name="selectedMovieId" value="<%= movie.getId() %>">
                    <%= movie.getName() %>
                </label>
                <br><br>
            <% } %>
            <button type="submit">Remove Selected Movies</button>
        <% } else { %>
            <p>No movies available</p>
        <% } %>
    </form>
    
    <br><br>
    <a href='/TicketBooking/Owner.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
    
</body>
</html>
