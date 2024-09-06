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
    <h2>User Booking History</h2>
    <% if (bookingHistory != null && !bookingHistory.isEmpty()) { %>
        <table>
            <tr>
                <th>Theater Name</th>
                <th>Movie Name</th>
                <th>Show Date</th>
                <th>Show Time</th>
                <th>Total Cost</th>
                <th>Booked Seats</th>
                <th>Booked At</th>
            </tr>
            <% for (BookingDetails bookingHistoryItem : bookingHistory) { %>
                <tr>
                    <td><%= bookingHistoryItem.getTheaterName() %></td>
                    <td><%= bookingHistoryItem.getMovieName() %></td>
                    <td><%= bookingHistoryItem.getShowDate() %></td>
                    <td><%= bookingHistoryItem.getShowTime() %></td>
                    <td><%= bookingHistoryItem.getTotalCost() %></td>
                    <td><%= bookingHistoryItem.getBookedSeats() %></td>
                    <td><%= formatDate(bookingHistoryItem.getCreatedAt()) %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No Seats Booked</p>
    <% } %>
     <%-- Function to format date --%>
    <%!
        public String formatDate(String dateStr) {
            try {
                SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = sourceFormat.parse(dateStr);
                SimpleDateFormat targetFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
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
