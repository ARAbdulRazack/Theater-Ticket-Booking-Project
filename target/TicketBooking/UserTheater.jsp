<%@ page import="java.util.List" %>
<%@ page import="util.Theater" %>

<%
    int selectedMovieId = (int) request.getAttribute("selectedMovieId");
    List<Theater> theaters = (List<Theater>) request.getAttribute("theaters");
    String movieName = (String) request.getAttribute("movieName");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Theaters for Selected Movie</title>
</head>
<body>
   <h2>Theaters for Movie: <%= movieName %></h2>
<form action="/TicketBooking/UserMoviesDatesServlet" method="post">
    <!-- Add a hidden input field to store the selected movie ID -->
    <input type="hidden" name="selectedMovieId" value="<%= selectedMovieId %>">
    
    <% if (theaters != null && !theaters.isEmpty()) { %>
        <% for (Theater theater : theaters) { %>
            <button type="submit" name="selectedTheaterId" value="<%= theater.getId() %>">
                <%= theater.getName() %>
            </button>
        <% } %>
    <% } else { %>
        <p>No theaters available for the selected movie</p>
    <% } %>
</form>
</body>
</html>
