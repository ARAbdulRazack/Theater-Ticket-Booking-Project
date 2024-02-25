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
import util.Movie;

@WebServlet("/UserMovieServlet3")
public class UserMovieServlet3 extends HttpServlet {
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String selectedUserIdParam = request.getParameter("selectedUserId");

	        try {
	            if (selectedUserIdParam != null && !selectedUserIdParam.isEmpty()) {
	                int selectedUserId = Integer.parseInt(selectedUserIdParam);

	                // Inside your doPost method
	                try (Connection connection = ConnectionUtil.getConnection()) {
	                    Map<String, Object> theatersDataMap = Showtime.getMoviesAndTheaterForUser2(connection, selectedUserId);

	                    List<Theater> theatersData = (List<Theater>) theatersDataMap.get("theatersData");

	                    request.setAttribute("theatersData", theatersData);
	                    request.setAttribute("selectedUserId", selectedUserId);

	                    RequestDispatcher dispatcher = request.getRequestDispatcher("/UserMovie3.jsp");
	                    dispatcher.forward(request, response);
	                } catch (SQLException e) {
	                    // Handle SQLException
	                    e.printStackTrace();
	                }
	            }
	        } catch (NumberFormatException e) {
	            // Handle the case where the parameter cannot be parsed as an integer
	            response.getWriter().println("Invalid selectedUserId parameter format");
	        }
	    }
	}
