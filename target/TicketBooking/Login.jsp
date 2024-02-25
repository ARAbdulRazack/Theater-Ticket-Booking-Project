<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>Ticket Booking</title>
</head>
<body>
    <h2>User Login</h2>
    <form action="./LoginServlet" method="post">
        <label for="phone_number">Phone Number:</label>
        <input type="tel" id="phone_number" name="phone_number" pattern="[0-9]{10}" required>
        <br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>

        <br>
        <input type="submit" value="Login">
        <br><br>
        
    </form>
    <a href="/TicketBooking/Registration.jsp"><input type="submit" value="Create User"></a>
</body>
</html>