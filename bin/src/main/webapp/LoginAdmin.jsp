<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.lang.Integer" %>

<!-- Assuming 'movies' is an attribute in the request -->
<%
Integer selectedUserId = (Integer) request.getAttribute("selectedUserId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ticket Booking</title>

<style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh; /* Ensure the page takes up the full viewport height */
            margin: 0;
        }

        h2 {
            text-align: center;
        }

        a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #3498db; /* Example background color */
            color: #fff; /* Example text color */
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px; /* Adjust as needed */
        }
    </style>
    
</head>
<body>
<h2>Welcome to Ticket Booking Web Application</h2>
	<a href='/TicketBooking/Admin.jsp?selectedUserId=<%= selectedUserId %>'>
    	<input type="submit" value="Home">
	</a>
	<br><br>
</body>
</html>