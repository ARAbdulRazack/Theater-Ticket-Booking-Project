<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
	String selectedUserId = request.getParameter("selectedUserId");

    int selectedMovieId = (int) request.getAttribute("selectedMovieId");
    String selectedMovieName = (String) request.getAttribute("selectedMovieName");
    
    int selectedTheaterId = (int) request.getAttribute("selectedTheaterId");
    String selectedTheaterName = (String) request.getAttribute("selectedTheaterName");
    
    String selectedDate = (String) request.getAttribute("selectedDate");
    
    List<Map<String, Object>> availableTimes = (List<Map<String, Object>>) request.getAttribute("availableTimes");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>
    <h2>Show Times for Movie: <%= selectedMovieName %> , Theater: <%= selectedTheaterName %>, Date: <%= selectedDate %></h2>

    <% if (availableTimes != null && !availableTimes.isEmpty()) { %>
        <form action="/TicketBooking/UserMovieSeats" method="post">
            <% for (Map<String, Object> timeInfo : availableTimes) { %>
            	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
                <input type="hidden" name="selectedMovieId" value="<%= selectedMovieId %>">
                <input type="hidden" name="selectedTheaterId" value="<%= selectedTheaterId %>">
                <input type="hidden" name="selectedDate" value="<%= selectedDate %>">
                <input type="hidden" name="selectedTime" value="<%= timeInfo.get("time") %>">
                <button type="submit" name="selectedDateTimeButton">	
                    <%= timeInfo.get("time") %>
                </button><br><br>
            <% } %>
        </form>
    <% } else { %>
        <p>No available show times for the selected date</p>
    <% } %>
    <br><br>
    <a href='/TicketBooking/User.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
