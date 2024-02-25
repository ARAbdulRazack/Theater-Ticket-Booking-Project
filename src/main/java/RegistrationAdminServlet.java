import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionUtil;
import util.Showtime;
import util.User; 


@WebServlet("/RegistrationAdminServlet")
public class RegistrationAdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/RegistrationAdmin.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
        boolean isOwner = Boolean.parseBoolean(request.getParameter("isOwner"));
        String theaterName = request.getParameter("theaterName");
        
        try (Connection connection = ConnectionUtil.getConnection()) {
        // Create user
        int userId = User.createUser(connection, userName, phoneNumber, password, isAdmin, isOwner);

        if (userId != -1) {
            Showtime.createTheater(connection, userId, theaterName);

            // Forward to a success page or perform other actions
            response.getWriter().println("Registration successful");
        } else {
            response.getWriter().println("Error in user creation");
        }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Phone number must be unique");
        }
    }
}