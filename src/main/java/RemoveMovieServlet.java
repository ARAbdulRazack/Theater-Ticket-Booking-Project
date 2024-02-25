import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionUtil;
import util.Showtime;

@WebServlet("/RemoveMovieServlet")
public class RemoveMovieServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the selected movie ID from the request parameter
        String selectedMovieIdParam = request.getParameter("selectedMovieId");

        // Check if selectedMovieIdParam is missing or empty
        if (selectedMovieIdParam == null || selectedMovieIdParam.isEmpty()) {
            response.getWriter().println("Invalid request. Please select at least one theater to remove.");
            return;
        }

        try (Connection connection = ConnectionUtil.getConnection()) {
            int selectedMovieId = Integer.parseInt(selectedMovieIdParam);

            // Remove the movie from the database
            if (Showtime.removeMovie(connection, selectedMovieId)) {
                // If removal is successful, set a success message
                response.getWriter().println("Movie successfully removed");
            } else {
                // If removal fails, set an error message
                response.getWriter().println("Error removing the movie");
            }

        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
            response.getWriter().println("Warning");
            response.getWriter().println("When the Movie is booked it can't be removed");
        }
    }
}
