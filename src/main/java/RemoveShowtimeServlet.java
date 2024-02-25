import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ConnectionUtil;
import util.Movie;
import util.Showtime;
import util.Theater;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveShowtimeServlet")
public class RemoveShowtimeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedShowtimeIdParam = request.getParameter("removeShowtimeId");

        if (selectedShowtimeIdParam != null && !selectedShowtimeIdParam.isEmpty()) {
            int selectedShowtimeId = Integer.parseInt(selectedShowtimeIdParam);

            try (Connection connection = ConnectionUtil.getConnection()) {
                boolean isSuccess = Showtime.removeShowtime(connection, selectedShowtimeId);

                if (isSuccess) {
                    response.getWriter().println("Showtime successfully removed");
                } else {
                    response.getWriter().println("Failed to remove showtime");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQLException properly
                response.getWriter().println("Warning");
                response.getWriter().println("When the Movie is booked, it can't be removed");
            }
        } else {
            response.getWriter().println("Invalid selected ShowtimeId parameter");
        }
    }
}

