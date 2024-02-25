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
import util.Theater;

@WebServlet("/UserTheaterServlet")
public class UserTheaterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedMovieIdParam = request.getParameter("selectedMovieId");
        String selectedUserIdParam = request.getParameter("selectedUserId");

        try {
            if (selectedMovieIdParam != null && !selectedMovieIdParam.isEmpty()) {
                int selectedMovieId = Integer.parseInt(selectedMovieIdParam);
                int selectedUserId = Integer.parseInt(selectedUserIdParam);

                // Inside your doPost method
                try (Connection connection = ConnectionUtil.getConnection()) {
                    Map<String, Object> theatersAndMovie = Showtime.getTheatersAndMovieForMovie(connection, selectedMovieId);
                    

                    List<Theater> theaters = (List<Theater>) theatersAndMovie.get("theaters");
                    
                    String movieName = (String) theatersAndMovie.get("movieName");

                    request.setAttribute("selectedMovieId", selectedMovieId);
                    request.setAttribute("movieName", movieName);
                    request.setAttribute("theaters", theaters);
                    request.setAttribute("selectedUserId", selectedUserId);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/UserTheater.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    // Handle SQLException
                    e.printStackTrace();
                }
            }
        } catch (NumberFormatException e) {
            // Handle the case where the parameter cannot be parsed as an integer
            response.getWriter().println("Invalid selectedMovieId parameter format");
        }
    }
}
