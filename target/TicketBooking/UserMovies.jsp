<%@ page import="java.util.List" %>
<%@ page import="util.Movie" %>

<!-- Assuming 'movies' is an attribute in the request -->
<%
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Movies</title>
</head>
<body>
    <h2>Movies</h2>
    <form action="/TicketBooking/UserTheaterServlet" method="post">
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
</body>
</html>
