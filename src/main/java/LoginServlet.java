import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ConnectionUtil;
import util.Showtime;
import util.Theater;
import util.User; 


// @WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phoneNumber = request.getParameter("phone_number");
        String userPassword = request.getParameter("password");

        User user = new User();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(userPassword);

        try (Connection connection = ConnectionUtil.getConnection()) {

            if (user.doesUserExist(connection)) {

                int selectedUserId = User.getUserIdFromPhoneNumber(connection, phoneNumber);

                boolean isAdmin = user.isAdmin(connection);
                boolean isOwner = user.isOwner(connection);

                if (isOwner) {
                    request.setAttribute("selectedUserId", selectedUserId);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginOwner.jsp");
                    dispatcher.forward(request, response);
                } else if (isAdmin) {
                    request.setAttribute("selectedUserId", selectedUserId);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginAdmin.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("selectedUserId", selectedUserId);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/LoginUser.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                response.getWriter().println("Invalid phone number or password. Please try again");
                response.setHeader("Refresh", "5; URL=/TicketBooking/Login.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error");
        }
    }
}