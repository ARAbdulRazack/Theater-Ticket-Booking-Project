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

import util.ConnectionUtil;
import util.Showtime;
import util.BookingDetails;

@WebServlet("/UserBookingServlet")
public class UserBookingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedUserIdParam = request.getParameter("selectedUserId");

        try {
            if (selectedUserIdParam != null && !selectedUserIdParam.isEmpty()) {
                int selectedUserId = Integer.parseInt(selectedUserIdParam);

                try (Connection connection = ConnectionUtil.getConnection()) {
                    List<BookingDetails> bookingHistory = Showtime.getBookingHistoryRecords(connection, selectedUserId);
                    request.setAttribute("bookingHistory", bookingHistory);
                    request.setAttribute("selectedUserId", selectedUserId);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/UserBookingHistory.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    // Handle SQLException
                    e.printStackTrace();
                    response.getWriter().println("Internal Server Error");
                }
            } else {
                // Handle the case where selectedUserId parameter is missing or empty
                response.getWriter().println("Invalid or missing selectedUserId parameter");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the parameter cannot be parsed as an integer
            response.getWriter().println("Invalid selectedUserId parameter format");
        }
    }
}


