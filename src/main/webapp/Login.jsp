<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>Ticket Booking</title>

</head>
<body>
<style>	

	body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    border: 2px solid #ccc;
    padding: 20px;
    width: 300px;
    text-align: center;
}



label {
    display: block;
    margin-bottom: 10px;
    text-align: left;
}

input {
    width: 90%;
    padding: 10px;
    margin-bottom: 15px;
}

input[type="submit"] {
    background-color: #4CAF50;
    color: white;
    padding: 10px 15px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    width: 100%;
}

input[type="submit"]:hover {
    background-color: #45a049;
}

</style>	
	
    <div class="container">
        <h2>Login</h2>
        <form action="./LoginServlet" method="post">
            <label for="phone_number">Phone Number:</label>
            <input type="tel" id="phone_number" placeholder="Enter Phone number" name="phone_number" pattern="[0-9]{10}" required>
            <br><br>

            <label for="password">Password:</label>
            <input type="password" id="password" placeholder="Enter Password" name="password" required>
            <br>

            <br>
            <input type="submit" value="Log in">
            <br><br>
        </form>
	
        <form action="/TicketBooking/Registration.jsp">
            <input type="submit" value="Sign in">
        </form>
    </div>
</body>
</html>