<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="util.BookingDetails" %>

<!-- Assuming 'bookingHistory' is an attribute in the request -->
<%
	String selectedUserId = request.getParameter("selectedUserId");
	List<BookingDetails> bookingHistory = (List<BookingDetails>) request.getAttribute("bookingHistory");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
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
</head>
<body>
    <h2>Booked Successfully</h2>
    <% if (bookingHistory != null && !bookingHistory.isEmpty()) { %>
    <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        <table>
            <tr>
                <th>Booked By</th>
                <th>Theater Name</th>
                <th>Movie Name</th>
                <th>Show Date</th>
                <th>Show Time</th>
                <th>Total Cost</th>
                <th>Booked Seats</th>
                <th>Created At</th>
            </tr>
            <%
                BookingDetails firstBooking = bookingHistory.get(0);
            %>
            <tr>
                <td><%= firstBooking.getBookedByName() %></td>
                <td><%= firstBooking.getTheaterName() %></td>
                <td><%= firstBooking.getMovieName() %></td>
                <td><%= firstBooking.getShowDate() %></td>
                <td><%= firstBooking.getShowTime() %></td>
                <td><%= firstBooking.getTotalCost() %></td>
                <td><%= firstBooking.getBookedSeats() %></td>
                <td><%= formatDate(firstBooking.getCreatedAt()) %></td>
            </tr>
        </table>
    <% } else { %>
        <p>No Seats Booked</p>
    <% } %>
    	<br>
	<font size="4">  
    Your seat has been booked successfully
	</font>    
    <%-- Function to format date --%>
    <%!
        public String formatDate(String dateStr) {
            try {
                SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sourceFormat.parse(dateStr);
                SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                return targetFormat.format(date);
            } catch (Exception e) {
                return dateStr; // Return original string if parsing fails
            }
        }
    %>
     <br><br>
    <a href='/TicketBooking/User.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>
