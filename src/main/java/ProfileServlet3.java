// ProfileServlet.java

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
import util.User;
import util.User;

@WebServlet("/ProfileServlet3")
public class ProfileServlet3 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the user ID from the request parameter
        String userIdParam = request.getParameter("selectedUserId");

        if (userIdParam != null && !userIdParam.isEmpty()) {
            try {
                int userId = Integer.parseInt(userIdParam);
                // Get the database connection
                try (Connection connection = ConnectionUtil.getConnection()) {
                    User user = User.getUserDetails(connection, userId);

                    if (user != null) {
                        // Set user details as request attributes
                        request.setAttribute("userDetails", user);
                        RequestDispatcher dispatcher = request.getRequestDispatcher("/Profile3.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        response.getWriter().println("User not found");
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle SQLException properly
                    response.getWriter().println("Internal Server Error");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid user ID parameter");
            }
        } else {
            response.getWriter().println("User ID parameter is missing");
        }
    }
}
