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

    <form action="/TicketBooking/AddMovieInTheaterServlet2" method="post">
        <% if (movies != null && !movies.isEmpty()) { %>
            <label for="selectedMovieId">Select a movie:</label>
            <select name="selectedMovieId">
                <% for (Movie movie : movies) { %>
                    <option value="<%= movie.getId() %>"><%= movie.getName() %></option>
                <% } %>
            </select>
            <br><br>

            <label for="showdate">Show Date (YYYY-MM-DD):</label>
            <input type="text" name="showdate" pattern="\d{4}-\d{2}-\d{2}" required>
            <br><br>

            <label for="showtime">Show Time (HH:MM AM|PM):</label>
            <input type="text" name="showtime" pattern="(0[0-9]|1[0-2]):[0-5][0-9] (AM|PM)" required>
            <br><br>

            <label for="cost">Cost:</label>
	        <input type="text" name="cost" pattern="\d+(\.\d{1,2})?" required>
	        <!-- The pattern allows whole numbers or decimals with up to two decimal places -->
	        <br><br>
	
	        <label for="total_seats">Total Seats :</label>
	        <input type="number" name="total_seats" min="1" required>
	        <!-- The min attribute enforces a minimum value of 1 -->
	        <br><br>

            <!-- Add a hidden field for selectedUserId -->
            <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
            
            <button type="submit" name="submit" value="submit">Submit</button>
        <% } else { %>
            <p>No movies available</p>
        <% } %>
    </form>
    
    <br><br>
    <a href='/TicketBooking/Admin.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
</body>
</html>
