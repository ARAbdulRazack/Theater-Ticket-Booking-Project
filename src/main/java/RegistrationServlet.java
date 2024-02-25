import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionUtil;  
import util.User; 


@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String name = request.getParameter("name");
        String userPassword = request.getParameter("password");
        String phoneNumber = request.getParameter("phone_number");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("is_admin"));

        User user = new User();
        user.setName(name);
        user.setPassword(userPassword);
        user.setPhoneNumber(phoneNumber);
        user.setAdmin(isAdmin);

        try (Connection connection = ConnectionUtil.getConnection()) {
            
            if (user.isPhoneNumberUnique(connection)) {              
                if (user.registerUser(connection)) {
                    response.getWriter().println("User registered successfully");
                    response.setHeader("Refresh", "5; URL=/TicketBooking/Login.jsp");
                } else {
                    response.getWriter().println("User registration failed");
                    response.setHeader("Refresh", "5; URL=/TicketBooking/Registration.jsp");
                }
            } 
            else {
                response.getWriter().println("Phone number must be unique");
                response.setHeader("Refresh", "5; URL=/TicketBooking/Registration.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error");
        }
    }
}