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
import util.Movie;
import util.Showtime;

@WebServlet("/ShowMoviesServlet2")
public class ShowMoviesServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String selectedUserIdParm = request.getParameter("selectedUserId");

        if (selectedUserIdParm != null && !selectedUserIdParm.isEmpty()) {
            int selectedUserId = Integer.parseInt(selectedUserIdParm);

            try (Connection connection = ConnectionUtil.getConnection()) {
                List<Movie> movies = Showtime.getMoviesName(connection);
                
                request.setAttribute("selectedUserId", selectedUserId);
                request.setAttribute("movies", movies);
                request.getRequestDispatcher("/ShowMovies2.jsp").forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("Internal Server Error");
            }
        }
    }
}


