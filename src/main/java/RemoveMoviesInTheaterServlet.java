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
import util.Showtime;
import util.ShowtimeDetails;

@WebServlet("/RemoveMoviesInTheaterServlet")
public class RemoveMoviesInTheaterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedUserIdParam = request.getParameter("selectedUserId");

        if (selectedUserIdParam != null && !selectedUserIdParam.isEmpty()) {
            int selectedUserId = Integer.parseInt(selectedUserIdParam);

            try (Connection connection = ConnectionUtil.getConnection()) {
                List<ShowtimeDetails> showtimeDetailsList = Showtime.getShowtimesForUser(connection, selectedUserId);

                request.setAttribute("showtimeDetailsList", showtimeDetailsList);
                request.setAttribute("selectedUserId", selectedUserId);
                
                request.getRequestDispatcher("/RemoveMovies&ShowtimeInTheater.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace(); // Handle SQLException properly
            }
        } else {
            response.getWriter().println("Invalid selectedUserId parameter");
        }
    }
}

