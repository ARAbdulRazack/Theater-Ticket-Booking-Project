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

@WebServlet("/UserShowTimesServlet2")
public class UserMoviesTimeServlet2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedMovieIdParam = request.getParameter("selectedMovieId");
        String selectedTheaterIdParam = request.getParameter("selectedTheaterId");
        String selectedDate = request.getParameter("selectedDate");
        String selectedUserIdParam = request.getParameter("selectedUserId");

        try {
            if (isValidParams(selectedMovieIdParam, selectedTheaterIdParam, selectedDate)) {
                int selectedMovieId = Integer.parseInt(selectedMovieIdParam);
                int selectedTheaterId = Integer.parseInt(selectedTheaterIdParam);
                int selectedUserId = Integer.parseInt(selectedUserIdParam);                

                try (Connection connection = ConnectionUtil.getConnection()) {
                    List<Map<String, Object>> availableTimes = Showtime.getAvailableTimesForMovieTheaterAndDate(connection, selectedMovieId, selectedTheaterId, selectedDate);

                    String selectedMovieName = Showtime.getMovieName(connection, selectedMovieId);
                    String selectedTheaterName = Showtime.getTheaterName(connection, selectedTheaterId);

                    request.setAttribute("selectedMovieId", selectedMovieId);
                    request.setAttribute("selectedMovieName", selectedMovieName);
                    request.setAttribute("selectedTheaterId", selectedTheaterId);
                    request.setAttribute("selectedTheaterName", selectedTheaterName);
                    request.setAttribute("selectedDate", selectedDate);
                    request.setAttribute("availableTimes", availableTimes);
                    request.setAttribute("selectedUserId", selectedUserId);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/UserMoviesTime2.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    handleSQLException(e, response);
                }            } else {
                response.getWriter().println("Invalid selected Movie, Theater, or Date parameters");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid selectedMovie or selectedTheater parameter format");
        }
    }

    private boolean isValidParams(String... params) {
        for (String param : params) {
            if (param == null || param.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private void handleSQLException(SQLException e, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        response.getWriter().println("Internal Server Error");
    }
}
