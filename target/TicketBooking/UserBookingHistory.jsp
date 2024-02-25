<%@ page import="java.util.List" %>
<%@ page import="util.BookingDetails" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking History</title>
</head>
<body>

<h2>Booking History</h2>

<table border="1">
    <thead>
        <tr>
            <th>Booking ID</th>
            <th>User Name</th>
            <th>Theater Name</th>
            <th>Movie Name</th>
            <th>Show Date</th>
            <th>Show Time</th>
            <th>Total Cost</th>
            <th>Booked Seats</th>
            <th>Created At</th>
        </tr>
    </thead>
    <tbody>
        <% List<BookingDetails> bookingHistory = (List<BookingDetails>) request.getAttribute("bookingHistory");
           for (BookingDetails booking : bookingHistory) { %>
            <tr>
                <td><%= booking.getBookingId() %></td>
                <td><%= booking.getTheaterName() %></td>
                <td><%= booking.getMovieName() %></td>
                <td><%= booking.getShowDate() %></td>
                <td><%= booking.getShowTime() %></td>
                <td><%= booking.getTotalCost() %></td>
                <td><%= booking.getCreatedAt() %></td>
            </tr>
        <% } %>
    </tbody>
</table>

</body>
</html>
