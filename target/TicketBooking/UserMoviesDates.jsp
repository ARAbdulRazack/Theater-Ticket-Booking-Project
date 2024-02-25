<%@ page import="java.util.List" %>
<%@ page import="util.Theater" %>

<%
    int selectedMovieId = (int) request.getAttribute("selectedMovieId");
    int selectedTheaterId = (int) request.getAttribute("selectedTheaterId");
    String movieName = (String) request.getAttribute("movieName");
    String theaterName = (String) request.getAttribute("theaterName");
    List<String> availableDates = (List<String>) request.getAttribute("availableDates");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Available Dates</title>
</head>
<body>
    <h2>Available Dates for <%= movieName %> at <%= theaterName %></h2>

    <% if (availableDates != null && !availableDates.isEmpty()) { %>
        <% for (String date : availableDates) { %>
            <form action="/TicketBooking/UserMoviesTimeServlet" method="post">
                <input type="hidden" name="selectedMovieId" value="<%= selectedMovieId %>">
                <input type="hidden" name="selectedTheaterId" value="<%= selectedTheaterId %>">
                <input type="hidden" name="selectedDate" value="<%= date %>">
                <button type="submit" name="selectedDateButton">
                    <%= date %>
                </button>
            </form>
        <% } %>
    <% } else { %>
        <p>No available dates</p>
    <% } %>
</body>
</html>
