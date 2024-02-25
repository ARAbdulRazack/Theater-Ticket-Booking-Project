import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConnectionUtil;
import util.Showtime;
import util.ShowtimeDetails;

@WebServlet("/UserMovieSeats2")
public class UserMovieSeats2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // Retrieve selected parameters
            int selectedMovieId = Integer.parseInt(request.getParameter("selectedMovieId"));
            int selectedTheaterId = Integer.parseInt(request.getParameter("selectedTheaterId"));
            String selectedDate = request.getParameter("selectedDate");
            String selectedTime = request.getParameter("selectedTime");
            String selectedUserIdParam = request.getParameter("selectedUserId");
            
            int selectedUserId = Integer.parseInt(selectedUserIdParam); 
            
            // Fetch showtime details
            ShowtimeDetails showtimeDetails = Showtime.getShowtimeDetails(connection, selectedTheaterId,
                    selectedMovieId, selectedDate, selectedTime);
            
            request.setAttribute("selectedMovieId", selectedMovieId);
            request.setAttribute("selectedTheaterId", selectedTheaterId);
            request.setAttribute("selectedMovieName", Showtime.getMovieName(connection, selectedMovieId));
            request.setAttribute("selectedTheaterName", Showtime.getTheaterName(connection, selectedTheaterId));
            request.setAttribute("selectedDate", selectedDate);
            request.setAttribute("selectedTime", selectedTime);
            request.setAttribute("showtimeDetails", showtimeDetails);
            request.setAttribute("selectedUserId", selectedUserId);

            // Forward the request to UserMovieSeats.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("/UserMovieSeats2.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error.");
        }
    }
}
