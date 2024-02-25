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
import util.Showtime;
import util.Movie;

@WebServlet("/UserMoviesServlet2")
public class UserMovieServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedTheaterIdParam = request.getParameter("selectedTheaterId");
        String selectedUserIdParam = request.getParameter("selectedUserId");

        try {
            if (selectedTheaterIdParam != null && !selectedTheaterIdParam.isEmpty()) {
                int selectedTheaterId = Integer.parseInt(selectedTheaterIdParam);
                int selectedUserId = Integer.parseInt(selectedUserIdParam); 

                // Inside your doPost method
                try (Connection connection = ConnectionUtil.getConnection()) {
                    Map<String, Object> moviesAndTheater = Showtime.getMoviesAndTheaterForTheater(connection, selectedTheaterId);

                    List<Movie> movies = (List<Movie>) moviesAndTheater.get("movies");
                    String theaterName = (String) moviesAndTheater.get("theaterName");

                    request.setAttribute("selectedTheaterId", selectedTheaterId);
                    request.setAttribute("theaterName", theaterName);
                    request.setAttribute("movies", movies);
                    request.setAttribute("selectedUserId", selectedUserId);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/UserMovie2.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    // Handle SQLException
                    e.printStackTrace();
                }
            }
        } catch (NumberFormatException e) {
            // Handle the case where the parameter cannot be parsed as an integer
            response.getWriter().println("Invalid selectedTheaterId parameter format");
        }
    }
}


