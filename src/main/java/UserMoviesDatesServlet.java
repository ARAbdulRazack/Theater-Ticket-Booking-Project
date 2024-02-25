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

@WebServlet("/UserMoviesDatesServlet")
public class UserMoviesDatesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedMovieIdParam = request.getParameter("selectedMovieId");
        String selectedTheaterIdParam = request.getParameter("selectedTheaterId");
        String selectedUserIdParam = request.getParameter("selectedUserId");

        try {
            if (selectedMovieIdParam != null && !selectedMovieIdParam.isEmpty()
                    && selectedTheaterIdParam != null && !selectedTheaterIdParam.isEmpty()) {

                int selectedMovieId = Integer.parseInt(selectedMovieIdParam);
                int selectedTheaterId = Integer.parseInt(selectedTheaterIdParam);
                int selectedUserId = Integer.parseInt(selectedUserIdParam);

                try (Connection connection = ConnectionUtil.getConnection()) {
                    Map<String, Object> datesAndNames = Showtime.getAvailableDatesAndNames(connection, selectedMovieId, selectedTheaterId);

                    List<String> availableDates = (List<String>) datesAndNames.get("availableDates");
                    
                    String movieName = (String) datesAndNames.get("movieName");
                    String theaterName = (String) datesAndNames.get("theaterName");
                    request.setAttribute("selectedMovieId", selectedMovieId);
                    request.setAttribute("selectedTheaterId", selectedTheaterId);
                    request.setAttribute("movieName", movieName);
                    request.setAttribute("theaterName", theaterName);
                    request.setAttribute("availableDates", availableDates);
                    request.setAttribute("selectedUserId", selectedUserId);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/UserMoviesDates.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.getWriter().println("Internal Server Error");
                }
            } else {
                // Handle the case where the parameters are missing or empty
                response.getWriter().println("Invalid selected Movie or Theater parameters");
            }
        } catch (NumberFormatException e) {
            // Handle the case where the parameters cannot be parsed as integers
            response.getWriter().println("Invalid selectedMovie or selectedTheater parameter format");
        }
    }
}
