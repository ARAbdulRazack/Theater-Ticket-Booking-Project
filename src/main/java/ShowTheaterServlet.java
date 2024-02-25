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
import util.Movie;
import util.Showtime;
import util.Theater;

@WebServlet("/ShowTheaterServlet")
public class ShowTheaterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String userIdParam = request.getParameter("selectedUserId");
    	
        try (Connection connection = ConnectionUtil.getConnection()) {
        	int selectedUserId = Integer.parseInt(userIdParam);
        	
            List<Theater> theaters = Showtime.getTheaters(connection);
            request.setAttribute("selectedUserId", selectedUserId);
            request.setAttribute("theaters", theaters);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/RemoveTheater.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error");
        }
    }
 
}
