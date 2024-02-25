<!DOCTYPE html>
<html>
<head>
<title>Ticket Booking</title>
</head>
<body>

    <h2>User Page</h2>
	
	<form action="./UserMoviesServlet" method="get">
    <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>">
    <input type="submit" value="Show Movies & Book Movie">
    <br><br>
    </form>

    <a href="/Ticketbooking/Theater_movies.jsp"><input type="submit" value="Show Theater & Movies Available"></a>
	<br><br>
	
    <form action="./UserBookingServlet" method="get">
    <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>">
    <input type="submit" value="Booking History">
    <br><br>
    </form>
	
    <a href='/Ticketbooking/Login.jsp'><input type="submit" value="Logout"></a>
	<br><br>
	 
</body>
</html>