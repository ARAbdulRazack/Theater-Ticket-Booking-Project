<%@ page import="java.util.List" %>
<%@ page import="util.Theater" %>

<%
    String selectedUserId = request.getParameter("selectedUserId");
    List<Theater> theaters = (List<Theater>) request.getAttribute("theaters");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ticket Booking</title>
</head>
<body>
    <h2>Theater</h2>
    <form action="/TicketBooking/RemoveTheaterServlet" method="post">
        <input type="hidden" name="selectedUserId" value="<%= selectedUserId %>">
        
        <% if (theaters != null && !theaters.isEmpty()) { %>
            <% for (Theater theater : theaters) { %>
                <label>
                    <input type="checkbox" name="selectedTheaterIds" value="<%= theater.getId() %>">
                    <%= theater.getName() %>
                </label>
                <br><br>
            <% } %>
            <input type="submit" value="Remove Selected Theaters">
        <% } else { %>
            <p>No theaters available</p>
        <% } %>
    </form>
    
    <br><br>
    <a href='/TicketBooking/Owner.jsp?selectedUserId=<%= selectedUserId %>'>
        <input type="submit" value="Home">
    </a>
    <br><br>
</body>
</html>
