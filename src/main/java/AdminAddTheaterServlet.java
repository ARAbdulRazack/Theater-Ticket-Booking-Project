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
import util.Showtime;
import util.Theater;

@WebServlet("/AdminAddTheaterServlet")
public class AdminAddTheaterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve values from the form
    	String selectedUserId = request.getParameter("selectedUserId");
        String theaterName = request.getParameter("TheaterName");
        
        System.out.print(selectedUserId);

        try (Connection connection = ConnectionUtil.getConnection()) {
        	int createdByUserId = Integer.parseInt(selectedUserId);

        	Theater theater = new Theater(createdByUserId, theaterName);
            theater.setName(request.getParameter("TheaterName"));

			// Add theater to the database
            boolean isTheaterAdded = Showtime.addTheater(connection, theater, createdByUserId);

            if (isTheaterAdded) {
                // Theater added successfully
                response.getWriter().println("Theater added successfully");
            } else {
                // Failed to add theater
                response.getWriter().println("Failed to add theater");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error");
        }
    }
}

