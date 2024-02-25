<%@ page import="java.util.List" %>
<%@ page import="util.Movie" %>
<%@ page import="util.Theater" %>

<%
    String selectedUserId = request.getParameter("selectedUserId");
    List<Theater> theatersData = (List<Theater>) request.getAttribute("theatersData");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>

    <% for (Theater theater : theatersData) { %>
        <h2>Movies in your Theater: <%= theater.getName() %></h2>
        <form action="/TicketBooking/UserMoviesDatesServlet2" method="post">
            <!-- Add a hidden input field to store the selected theater ID -->
            <input type="hidden" name="selectedTheaterId" value="<%= theater.getId() %>">
            <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">

            
                <% List<Movie> movies = theater.getMovies(); %>
                <% if (movies != null && !movies.isEmpty()) { %>
                    <% for (Movie movie : movies) { %>
                       
                            <button type="submit" name="selectedMovieId" value="<%= movie.getId() %>">
                                <%= movie.getName() %>
                            </button><br><br>
                            <input type="hidden" name="movieIds" value="<%= movie.getId() %>">
                       
                    <% } %>
                <% } else { %>
                    No movies available at this theater
                <% } %>
        </form>
    <% } %>

    <br><br>
    <a href='/TicketBooking/Admin.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
</body>
</html>
