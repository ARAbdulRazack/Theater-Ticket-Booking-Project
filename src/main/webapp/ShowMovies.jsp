<%@ page import="java.util.List" %>
<%@ page import="util.Movie" %>

<%
	String selectedUserId = request.getParameter("selectedUserId");
    List<Movie> movies = (List<Movie>) request.getAttribute("movies");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
<body>
    <h2>All Movies List</h2>
    <table border="1">
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <tr>
            <th>Name</th>
            <th>Director</th>
            <th>Hero</th>
        </tr>
        <% for (Movie movie : movies) { %>
            <tr>
                <td><%= movie.getName() %></td>
                <td><%= movie.getDirectorName() %></td>
                <td><%= movie.getHeroName() %></td>
            </tr>
        <% } %>
    </table>
    
    <br><br>
    <a href='/TicketBooking/Owner.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
</body>
</html>
