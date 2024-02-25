import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionUtil;
import util.Movie;
import util.Showtime;

@WebServlet("/AddMovieInTheaterServlet2")
public class AddMovieInTheaterServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // Get parameters from the form
            int selectedUserId = Integer.parseInt(request.getParameter("selectedUserId"));
            int selectedMovieId = Integer.parseInt(request.getParameter("selectedMovieId"));
            String showdate = request.getParameter("showdate");
            String showtime = request.getParameter("showtime");
            double cost = Double.parseDouble(request.getParameter("cost"));
            int totalSeats = Integer.parseInt(request.getParameter("total_seats"));

            // Call a method to store this data in the showtime table
            boolean isSuccess = Showtime.addShowtime(connection, selectedUserId, selectedMovieId, showdate, showtime, cost, totalSeats);

            if (isSuccess) {
                // Display success message
                response.getWriter().println("Showtime added successfully");
            } else {
                // Display error message
                response.getWriter().println("Error adding showtime");
            }
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle the case where the parameters cannot be parsed as expected
            response.getWriter().println("Invalid input format");
        }
    }
}
