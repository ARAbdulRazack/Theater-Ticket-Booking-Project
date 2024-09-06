<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="util.ShowtimeDetails" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>

    <%
    	String selectedUserId = request.getParameter("selectedUserId");
    
        int selectedMovieId = (int) request.getAttribute("selectedMovieId");
        String selectedMovieName = (String) request.getAttribute("selectedMovieName");
        
        int selectedTheaterId = (int) request.getAttribute("selectedTheaterId");
        String selectedTheaterName = (String) request.getAttribute("selectedTheaterName");
        
        String selectedDate = (String) request.getAttribute("selectedDate");
        
        String selectedTime = (String) request.getAttribute("selectedTime");
        
        ShowtimeDetails showtimeDetails = (ShowtimeDetails) request.getAttribute("showtimeDetails");
    %>
    
    
    <h2>Showtime Details for Movie: <%= selectedMovieName %> , Theater: <%= selectedTheaterName %>, Date: <%= selectedDate %>, Time: <%= selectedTime %></h2>

    <p>Cost: <%= showtimeDetails.getCost() %></p>
    <p>Total Seats: <%= showtimeDetails.getTotalSeats() %></p>
    <p>Available Seats: <%= showtimeDetails.getAvailableSeats() %></p>
    <p>Booked Seats: <%= showtimeDetails.getBookedSeats() %></p>
      <br><br>
      
     <h2>Booking Option for this Showtime</h2>

    <form action="/TicketBooking/BookSeatsServlet" method="post">
    	<input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <input type="hidden" name="selectedMovieId" value="<%= selectedMovieId %>">
        <input type="hidden" name="selectedTheaterId" value="<%= selectedTheaterId %>">
        <input type="hidden" name="selectedDate" value="<%= selectedDate %>">
        <input type="hidden" name="selectedTime" value="<%= selectedTime %>">
        
        <label for="seatsNumber">Enter the number of seats to book (Separated by commas):</label>
		<input type="text" id="seatsNumber" name="seatsNumber" required>
        <br><br>
        
       <label for="phone_number">Enter Phone Number:</label>
        <input type="tel" id="phone_number" name="phone_number" pattern="[0-9]{10}" required>
        <br><br>
        
        <label for="password">Enter your password:</label>
        <input type="password" id="password" name="password" required>
        <br><br>
        
        <button type="submit" name="bookSeatsButton">Book Seats</button>
    </form>
    <br><br>
    <a href='/TicketBooking/User.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
