<%@ page import="java.util.List" %>
<%@ page import="util.ShowtimeDetails" %>

<%
	String selectedUserId = request.getParameter("selectedUserId");
	List<ShowtimeDetails> showtimeDetail = (List<ShowtimeDetails>) request.getAttribute("showtimeDetailsList");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>
    <h2>Showtime Details</h2>
    
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

    <form action="/TicketBooking/RemoveShowtimeServlet" method="post">
    <% if (showtimeDetail != null && !showtimeDetail.isEmpty()) { %>
        <table>
            <tr>
            	
                <th>Theater Name</th>
                <th>Movie Name</th>
                <th>Showtime</th>
                <th>ShowDate</th>
                <th>Cost</th>
                <th>Total Seats</th>
                <th>Seats</th>
                <th>Booked Seats</th>
                <th>Created At</th>
                <th>Action</th>
            </tr>
            <% for (ShowtimeDetails showtimeDetails : showtimeDetail) { %>
                <tr>
                
                    <td><%= showtimeDetails.getTheatername() %></td>
                    <td><%= showtimeDetails.getMovieName() %></td>                 
                    <td><%= showtimeDetails.getShowtime() %></td>
                    <td><%= showtimeDetails.getShowdate() %></td>
                    <td><%= showtimeDetails.getCost() %></td>
                    <td><%= showtimeDetails.getTotalSeats() %></td>
                    <td><%= showtimeDetails.getAvailableSeats() %></td>
                    <td><%= showtimeDetails.getBookedSeats() %></td>
                    <td><%= showtimeDetails.getCreatedAt() %></td>
                    <td>
                        <button type="submit" name="removeShowtimeId" value="<%= showtimeDetails.getId() %>">Remove</button>
                    </td>
                </tr>
            <% } %>
        </table>
           <% } else { %>
        <p>No Movies or Showtimes Available</p>
    <% } %>
    </form>
    
    <br><br>
    <a href='/TicketBooking/Admin.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
</body>
</html>
