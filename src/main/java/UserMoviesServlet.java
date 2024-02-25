import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ConnectionUtil;
import util.Movie;
import util.Showtime;

@WebServlet("/UserMoviesServlet")
public class UserMoviesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String selectedUserIdParam = request.getParameter("selectedUserId");
    	
        try (Connection connection = ConnectionUtil.getConnection()) {
        	int selectedUserId = Integer.parseInt(selectedUserIdParam);
            List<Movie> movies = Showtime.getMovies(connection);
            
            request.setAttribute("movies", movies);
            request.setAttribute("selectedUserId", selectedUserId);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/UserMovies.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error");
        }
    }
}
