import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.User;
import util.BookingDetails;
import util.ConnectionUtil;
import util.Showtime;

/// Import statements

@WebServlet("/BookSeatsServlet2")
public class BookSeatsServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // Retrieve selected parameters
            int selectedMovieId = Integer.parseInt(request.getParameter("selectedMovieId"));
            int selectedTheaterId = Integer.parseInt(request.getParameter("selectedTheaterId"));
            String selectedDate = request.getParameter("selectedDate");
            String selectedTime = request.getParameter("selectedTime");
            String seats = request.getParameter("seatsNumber");
            String phoneNumber = request.getParameter("phone_number");
            String password = request.getParameter("password");
            String selectedUserIdParam = request.getParameter("selectedUserId");
            
            int selectedUserId = Integer.parseInt(selectedUserIdParam);
       
            //int userId = User.getUserIdFromPhoneNumber(connection, phoneNumber);

            // Perform booking action
            boolean isSuccess = Showtime.bookSeats(connection, selectedTheaterId, selectedMovieId,
            	    selectedDate, selectedTime, seats, selectedUserId);
                       
            if (isSuccess) { 
            	List<BookingDetails> bookingHistory = Showtime.getBookingHistoryRecords(connection, selectedUserId);
            	
            	
            	request.setAttribute("selectedUserId", selectedUserId);
                request.setAttribute("bookingHistory", bookingHistory);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("/BookSuccess2.jsp");
                dispatcher.forward(request, response);
            } else {
            	response.getWriter().println("Invalid Seat Number Enter available seats");

            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error");
        }
    }
}

