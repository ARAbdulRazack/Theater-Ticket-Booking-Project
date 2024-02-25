<!DOCTYPE html>
<html lang="en">
<head>
    
    <title>Ticket Booking</title>
</head>
<body>
    <h2>Create new User</h2>
    
    <form action="./RegistrationServlet" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br/> <br/>
        
        <label for="phone_number">Phone Number:</label>
        <input type="tel" id="phone_number" name="phone_number" pattern="[0-9]{10}" required>
        <br/> <br/>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br/> <br/>

        <label for="is_admin">Is Admin:</label>
        <input type="checkbox" id="is_admin" name="is_admin">
        <br/> 	
		
        <br/>
        <input type="submit" value="Register">
    </form>
    
</body>
</html>