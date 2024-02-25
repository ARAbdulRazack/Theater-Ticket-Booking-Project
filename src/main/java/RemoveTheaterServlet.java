import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionUtil;
import util.Movie;
import util.Showtime;
import util.Theater;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveTheaterServlet")
public class RemoveTheaterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] selectedTheaterIds = request.getParameterValues("selectedTheaterIds");

        if (selectedTheaterIds != null && selectedTheaterIds.length > 0) {
            try (Connection connection = ConnectionUtil.getConnection()) {
                // Call your method to remove the selected theaters
                for (String theaterId : selectedTheaterIds) {
                    int id = Integer.parseInt(theaterId);
                    if (Showtime.removeTheater(connection, id)) {
                        // Theater successfully removed
                        response.getWriter().println("Selected Theater removed successfully");
                    } else {
                        // Failed to remove the theater
                        response.getWriter().println("Failed to remove the theater with ID " + id);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Warning");
                response.getWriter().println("When the Movie is booked, it can't be removed");
            }
        } else {
            // No theater IDs provided
            response.getWriter().println("Invalid request. Please select at least one theater to remove.");
        }
    }
}

