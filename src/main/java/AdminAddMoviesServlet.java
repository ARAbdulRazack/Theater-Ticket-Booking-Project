import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

@WebServlet("/AdminAddMoviesServlet")
public class AdminAddMoviesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve selectedUserId from the request
        String selectedUserId = request.getParameter("selectedUserId");

        // Retrieve movie details from the form
        String movieName = request.getParameter("MovieName");
        String directorName = request.getParameter("DirectorName");
        String heroName = request.getParameter("HeroName");

        // Assuming you have a method to add movie in your DAO
        try (Connection connection = ConnectionUtil.getConnection()) {
            int createdByUserId = Integer.parseInt(selectedUserId);
            Movie movie = new Movie(createdByUserId, movieName, directorName, heroName);

            if (Showtime.addMovie(connection, movie, createdByUserId)) {
                response.getWriter().println("Movie added successfully");
            } else {
                response.getWriter().println("Failed to add movie");
            }
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
            response.getWriter().println("Internal Server Error");
        }
    }
}
