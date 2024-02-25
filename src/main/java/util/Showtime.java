package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Showtime {
    private int id;
    private int createdBy;
    private int updatedBy;
    private int movieId;
    private int theaterId;
    private Date showdate;
    private String showtime;
    private double cost;
    private int totalSeats;
    private List<Integer> seats;
    private List<Integer> bookedSeats;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    public Showtime(int id, int createdBy, int updatedBy, int movieId, int theaterId, Date showdate,
            String showtime, double cost, int totalSeats, List<Integer> seats, List<Integer> bookedSeats,
            Timestamp createdAt, Timestamp updatedAt) {
    		this.id = id;
    		this.createdBy = createdBy;
    		this.updatedBy = updatedBy;
    		this.movieId = movieId;
    		this.theaterId = theaterId;
    		this.showdate = showdate;
    		this.showtime = showtime;
    		this.cost = cost;
    		this.totalSeats = totalSeats;
    		this.seats = seats;
    		this.bookedSeats = bookedSeats;
    		this.createdAt = createdAt;
    		this.updatedAt = updatedAt;
}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(int theaterId) {
		this.theaterId = theaterId;
	}
	public Date getShowdate() {
		return showdate;
	}
	public void setShowdate(Date showdate) {
		this.showdate = showdate;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public List<Integer> getSeats() {
		return seats;
	}
	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}
	public List<Integer> getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(List<Integer> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	
	public static List<Movie> getMovies(Connection connection) throws SQLException {
        List<Movie> movies = new ArrayList<>();

        String sql = "SELECT * FROM movie ORDER BY id DESC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int createdBy = resultSet.getInt("created_by");
                String name = resultSet.getString("name");
                String directorName = resultSet.getString("director_name");
                String heroName = resultSet.getString("hero_name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");

                Movie movie = new Movie(id, createdBy, name, directorName, heroName, createdAt, updatedAt);
                movies.add(movie);
            }
        }

        return movies;
    }
	
	public static List<Theater> getTheaters(Connection connection) throws SQLException {
	    List<Theater> theaters = new ArrayList<>();
	    String query = "SELECT * FROM theater ORDER BY id DESC";
	    try (PreparedStatement statement = connection.prepareStatement(query);
	         ResultSet resultSet = statement.executeQuery()) {
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            int createdBy = resultSet.getInt("created_by");
	            String name = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");
	            // You can retrieve other attributes similarly
	            Theater theater = new Theater(id, createdBy, name, createdAt, updatedAt);
	            theaters.add(theater);
	            
	        }
	    }
	    return theaters;
	}
	
	    // Add the following method to get theaters for a specific movie
	public static Map<String, Object> getTheatersAndMovieForMovie(Connection connection, int movieId) throws SQLException {
	    Map<String, Object> result = new HashMap<>();

	    String theaterSql = "SELECT DISTINCT ON (t.id) t.id, t.created_by, t.name, t.created_at, t.updated_at " +
                "FROM showtime s " +
                "JOIN theater t ON s.theater_id = t.id " +
                "WHERE s.movie_id = ? " +
                "ORDER BY t.id, s.id DESC";

	    String movieSql = "SELECT name FROM movie WHERE id = ?";

	    try (PreparedStatement theaterStatement = connection.prepareStatement(theaterSql);
	         PreparedStatement movieStatement = connection.prepareStatement(movieSql)) {
	        theaterStatement.setInt(1, movieId);
	        movieStatement.setInt(1, movieId);

	        try (ResultSet theaterResultSet = theaterStatement.executeQuery();
	             ResultSet movieResultSet = movieStatement.executeQuery()) {

	            List<Theater> theaters = new ArrayList<>();
	            String movieName = null;

	            while (theaterResultSet.next()) {
	                int id = theaterResultSet.getInt("id");
	                int createdBy = theaterResultSet.getInt("created_by");
	                String name = theaterResultSet.getString("name");
	                Timestamp createdAt = theaterResultSet.getTimestamp("created_at");
	                Timestamp updatedAt = theaterResultSet.getTimestamp("updated_at");

	                Theater theater = new Theater(id, createdBy, name, createdAt, updatedAt);
	                theaters.add(theater);
	            }

	            if (movieResultSet.next()) {
	                movieName = movieResultSet.getString("name");
	            }

	            result.put("theaters", theaters);
	            result.put("movieName", movieName);
	        }
	    }

	    return result;
	}
	
	public static Map<String, Object> getMoviesAndTheaterForTheater(Connection connection, int theaterId) throws SQLException {
	    Map<String, Object> result = new HashMap<>();

	    String movieSql = "SELECT DISTINCT ON (m.id) m.id, m.created_by, m.name, m.director_name, m.hero_name, m.created_at, m.updated_at " +
	                      "FROM showtime s " +
	                      "JOIN movie m ON s.movie_id = m.id " +
	                      "WHERE s.theater_id = ?" +
	                      "ORDER BY m.id, s.id DESC";

	    String theaterSql = "SELECT name FROM theater WHERE id = ?";

	    try (PreparedStatement movieStatement = connection.prepareStatement(movieSql);
	         PreparedStatement theaterStatement = connection.prepareStatement(theaterSql)) {
	        movieStatement.setInt(1, theaterId);
	        theaterStatement.setInt(1, theaterId);

	        try (ResultSet movieResultSet = movieStatement.executeQuery();
	             ResultSet theaterResultSet = theaterStatement.executeQuery()) {

	            List<Movie> movies = new ArrayList<>();
	            String theaterName = null;

	            while (movieResultSet.next()) {
	                int id = movieResultSet.getInt("id");
	                int createdBy = movieResultSet.getInt("created_by");
	                String name = movieResultSet.getString("name");
	                String directorName = movieResultSet.getString("director_name");
	                String heroName = movieResultSet.getString("hero_name");
	                Timestamp createdAt = movieResultSet.getTimestamp("created_at");
	                Timestamp updatedAt = movieResultSet.getTimestamp("updated_at");

	                Movie movie = new Movie(id, createdBy, name, directorName, heroName, createdAt, updatedAt);
	                movies.add(movie);
	            }

	            if (theaterResultSet.next()) {
	                theaterName = theaterResultSet.getString("name");
	            }

	            result.put("movies", movies);
	            result.put("theaterName", theaterName);
	        }
	    }

	    return result;
	}
	
	public static Map<String, Object> getMoviesAndTheaterForUser2(Connection connection, int userId) throws SQLException {
	    Map<String, Object> result = new HashMap<>();

	    // Query to find theaters associated with the user
	    String theaterIdSql = "SELECT id, name FROM theater WHERE created_by = ?" +
	    		"ORDER BY id DESC";

	    try (PreparedStatement theaterIdStatement = connection.prepareStatement(theaterIdSql)) {
	        theaterIdStatement.setInt(1, userId);

	        try (ResultSet theaterIdResultSet = theaterIdStatement.executeQuery()) {
	            List<Theater> theatersData = new ArrayList<>();

	            while (theaterIdResultSet.next()) {
	                int theaterId = theaterIdResultSet.getInt("id");
	                String theaterName = theaterIdResultSet.getString("name");

	                // Query to get movies for each theater
	                String movieSql = "SELECT DISTINCT ON (m.id) m.id, m.created_by, m.name, m.director_name, m.hero_name, m.created_at, m.updated_at " +
	                        "FROM showtime s " +
	                        "JOIN movie m ON s.movie_id = m.id " +
	                        "WHERE s.theater_id = ? " +
	                        "ORDER BY m.id, s.id DESC";
	                
	                try (PreparedStatement movieStatement = connection.prepareStatement(movieSql)) {
	                    movieStatement.setInt(1, theaterId);

	                    try (ResultSet movieResultSet = movieStatement.executeQuery()) {
	                        List<Movie> movies = new ArrayList<>();

	                        while (movieResultSet.next()) {
	                            int id = movieResultSet.getInt("id");
	                            int createdBy = movieResultSet.getInt("created_by");
	                            String name = movieResultSet.getString("name");
	                            String directorName = movieResultSet.getString("director_name");
	                            String heroName = movieResultSet.getString("hero_name");
	                            Timestamp createdAt = movieResultSet.getTimestamp("created_at");
	                            Timestamp updatedAt = movieResultSet.getTimestamp("updated_at");

	                            Movie movie = new Movie(id, createdBy, name, directorName, heroName, createdAt, updatedAt);
	                            movies.add(movie);
	                        }

	                        Theater theaterData = new Theater(theaterId, theaterName, movies);
	                        theatersData.add(theaterData);
	                    }
	                }
	            }

	            result.put("theatersData", theatersData);
	        }
	    }

	    return result;
	}

	public static Map<String, Object> getAvailableDatesAndNames(Connection connection, int movieId, int theaterId)
	        throws SQLException {
	    Map<String, Object> result = new HashMap<>();

	    String datesSql = "SELECT DISTINCT showdate FROM showtime " +
	                      "WHERE movie_id = ? AND theater_id = ?";

	    String movieAndTheaterNamesSql = "SELECT m.name AS movie_name, t.name AS theater_name " +
	                                     "FROM movie m " +
	                                     "JOIN showtime s ON m.id = s.movie_id " +
	                                     "JOIN theater t ON s.theater_id = t.id " +
	                                     "WHERE m.id = ? AND t.id = ?" ;

	    try (PreparedStatement datesStatement = connection.prepareStatement(datesSql);
	         PreparedStatement namesStatement = connection.prepareStatement(movieAndTheaterNamesSql)) {
	        datesStatement.setInt(1, movieId);
	        datesStatement.setInt(2, theaterId);

	        namesStatement.setInt(1, movieId);
	        namesStatement.setInt(2, theaterId);

	        try (ResultSet datesResultSet = datesStatement.executeQuery();
	             ResultSet namesResultSet = namesStatement.executeQuery()) {

	            List<String> availableDates = new ArrayList<>();
	            String movieName = null;
	            String theaterName = null;

	            while (datesResultSet.next()) {
	                String date = datesResultSet.getString("showdate");
	                availableDates.add(date);
	            }

	            if (namesResultSet.next()) {
	                movieName = namesResultSet.getString("movie_name");
	                theaterName = namesResultSet.getString("theater_name");
	            }

	            result.put("availableDates", availableDates);
	            result.put("movieName", movieName);
	            result.put("theaterName", theaterName);
	        }
	    }

	    return result;
	}
	
	public static List<Map<String, Object>> getAvailableTimesForMovieTheaterAndDate(Connection connection, int movieId, int theaterId, String selectedDate)
	        throws SQLException {
	    List<Map<String, Object>> availableTimesList = new ArrayList<>();

	    String sql = "SELECT DISTINCT showtime, m.id AS movie_id, m.name AS movie_name, t.id AS theater_id, t.name AS theater_name "
	    		+ "FROM showtime s " +
	                 "JOIN movie m ON s.movie_id = m.id " +
	                 "JOIN theater t ON s.theater_id = t.id " +
	                 "WHERE s.movie_id = ? AND s.theater_id = ? AND s.showdate = ?::date" ;

	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, movieId);
	        preparedStatement.setInt(2, theaterId);
	        preparedStatement.setString(3, selectedDate);

	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            while (resultSet.next()) {
	                String time = resultSet.getString("showtime");

	                int resultMovieId = resultSet.getInt("movie_id");
	                String resultMovieName = resultSet.getString("movie_name");
	                int resultTheaterId = resultSet.getInt("theater_id");
	                String resultTheaterName = resultSet.getString("theater_name");
	                
	                Map<String, Object> availableTimeInfo = new HashMap<>();
	                availableTimeInfo.put("time", time);
	                availableTimeInfo.put("movieId", resultMovieId);
	                availableTimeInfo.put("movieName", resultMovieName);
	                availableTimeInfo.put("theaterId", resultTheaterId);
	                availableTimeInfo.put("theaterName", resultTheaterName);
	                availableTimeInfo.put("date", selectedDate);

	                availableTimesList.add(availableTimeInfo);
	            }
	        }
	    }

	    return availableTimesList;
	}
	
	public static String getMovieName(Connection connection, int movieId) throws SQLException {
        String sql = "SELECT name FROM movie WHERE id = ?" +
        		"ORDER BY id DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, movieId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        }
        return null;
    }
	
	public static String getTheaterName(Connection connection, int theaterId) throws SQLException {
        String sql = "SELECT name FROM theater WHERE id = ?" +
        		"ORDER BY id DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, theaterId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        }
        return null;
    }
	
	public static List<String> getShowtimesForMovieAndDate(Connection connection, int movieId, Date date)
            throws SQLException {
        List<String> showtimes = new ArrayList<>();

        String query = "SELECT DISTINCT showtime FROM showtime WHERE movife_id = ? AND showdate = ?" +
        		"ORDER BY id DESC";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, movieId);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String showtime = resultSet.getString("showtime");
                    showtimes.add(showtime);
                }
            }
        }

        return showtimes;
    }
	
	public static ShowtimeDetails getShowtimeDetails(Connection connection, int theaterId, int movieId, String date, String time)
	        throws SQLException {
		String sql = "SELECT s.cost, s.total_seats, s.seats, s.booked_seats, m.name AS movie_name, t.name AS theater_name " +
	             "FROM showtime s " +
	             "JOIN movie m ON s.movie_id = m.id " +
	             "JOIN theater t ON s.theater_id = t.id " +
	             "WHERE s.theater_id = ? AND s.movie_id = ? AND s.showdate = CAST(? AS DATE) AND s.showtime = ?" +
	             "ORDER BY s.id DESC";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setInt(1, theaterId);
	        preparedStatement.setInt(2, movieId);
	        preparedStatement.setString(3, date);
	        preparedStatement.setString(4, time);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                double cost = resultSet.getDouble("cost");
	                int totalSeats = resultSet.getInt("total_seats");
	                List<Integer> seats = getIntegerArray(resultSet.getArray("seats"));
	                List<Integer> bookedSeats = getIntegerArray(resultSet.getArray("booked_seats"));
	                String movieName = resultSet.getString("movie_name");
	                String theaterName = resultSet.getString("theater_name");
	                return new ShowtimeDetails(cost, totalSeats, seats, bookedSeats);
	            }
	        }
	    }
	    return null;
	}

	private static List<Integer> getIntegerArray(java.sql.Array array) throws SQLException {
	    List<Integer> list = new ArrayList<>();
	    if (array != null) {
	        Object[] objArray = (Object[]) array.getArray();
	        for (Object obj : objArray) {
	            if (obj instanceof Integer) {
	                list.add((Integer) obj);
	            }
	        }
	    }
	    return list;
	}
	
	public static boolean bookSeats(Connection connection, int theaterId, int movieId, String date, String time, 
            String seatsString, int userId) throws SQLException {
        // Split the input seats string and convert them to integers
        String[] seatsArray = seatsString.split(",");
        List<Integer> seatsToBook = new ArrayList<>();
        for (String seat : seatsArray) {
            seatsToBook.add(Integer.parseInt(seat));
        }
        
        // Get available seats
        List<Integer> availableSeats = getAvailableSeats(connection, theaterId, movieId, date, time);
        
        // Check if the requested seats are available
        for (int seat : seatsToBook) {
            if (!availableSeats.contains(seat)) {
                return false; // Selected seat not available
            }
        }

        // Update booked seats and available seats in the database
        updateBookedSeats(connection, theaterId, movieId, date, time, seatsToBook, availableSeats);
        
        // Record booking details in the bookingHistory table
        int showtimeId = getShowtimeId(connection, theaterId, movieId, date, time);
        double totalCost = getTotalCost(connection, theaterId, movieId, date, time, seatsToBook.size());
        recordBookingHistory(connection, userId, theaterId, movieId, showtimeId, totalCost, seatsToBook);

        return true;
    }

	    // Method to get available seats
	    public static List<Integer> getAvailableSeats(Connection connection, int theaterId, int movieId, String date, String time) throws SQLException {
	        List<Integer> availableSeats = new ArrayList<>();

	        String query = "SELECT seats FROM showtime WHERE theater_id = ? AND movie_id = ? AND showdate = CAST(? AS DATE) "
	        		+ "AND showtime = ?" +
	        		"ORDER BY id DESC";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, theaterId);
	            preparedStatement.setInt(2, movieId);
	            preparedStatement.setString(3, date);
	            preparedStatement.setString(4, time);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    Integer[] seats = (Integer[]) resultSet.getArray("seats").getArray();
	                    availableSeats.addAll(Arrays.asList(seats));
	                }
	            }
	        }

	        return availableSeats;
	    }

	    public static void updateBookedSeats(Connection connection, int theaterId, int movieId, String date, String time, 
	            List<Integer> seatsToBook, List<Integer> availableSeats) throws SQLException {
	        // Calculate total seats
	        

	        // Subtract booked seats from available seats
	        availableSeats.removeAll(seatsToBook);
	        int totalSeats = availableSeats.size();
	        // Concatenate newly booked seats with existing booked seats
	        List<Integer> existingBookedSeats = getExistingBookedSeats(connection, theaterId, movieId, date, time);
	        if (existingBookedSeats == null) {
	            existingBookedSeats = new ArrayList<>(seatsToBook);
	        } else {
	            // If existingBookedSeats is not null, add seatsToBook to it
	            existingBookedSeats.addAll(seatsToBook);
	        }

	        // Update seats and available seats in the database
	        String updateSql = "UPDATE showtime SET booked_seats = ?, seats = ?, total_seats = ? WHERE "
	                + "theater_id = ? AND movie_id = ? AND showdate = CAST(? AS DATE) AND showtime = ?";
	        try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
	            Integer[] bookedSeatsArray = existingBookedSeats.toArray(new Integer[existingBookedSeats.size()]);
	            Integer[] availableSeatsArray = availableSeats.toArray(new Integer[availableSeats.size()]);
	            updateStatement.setArray(1, connection.createArrayOf("integer", bookedSeatsArray));
	            updateStatement.setArray(2, connection.createArrayOf("integer", availableSeatsArray));
	            updateStatement.setInt(3, totalSeats);
	            updateStatement.setInt(4, theaterId);
	            updateStatement.setInt(5, movieId);
	            updateStatement.setString(6, date);
	            updateStatement.setString(7, time);
	            updateStatement.executeUpdate();
	        }
	    }
	    
	    public static List<Integer> getExistingBookedSeats(Connection connection, int theaterId, int movieId, String date, String time)
	            throws SQLException {
	        List<Integer> existingBookedSeats = new ArrayList<>();

	        String query = "SELECT booked_seats FROM showtime WHERE theater_id = ? AND movie_id = ? AND showdate = CAST(? AS DATE)"+
	        " AND showtime = ? ORDER BY id DESC";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, theaterId);
	            preparedStatement.setInt(2, movieId);
	            preparedStatement.setString(3, date);
	            preparedStatement.setString(4, time);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    java.sql.Array bookedSeatsArray = resultSet.getArray("booked_seats");
	                    if (bookedSeatsArray != null) {
	                        existingBookedSeats.addAll(Arrays.asList((Integer[]) bookedSeatsArray.getArray()));
	                    }
	                    // Handle the case where bookedSeatsArray is null (no seats booked yet)
	                }
	            }
	        }
	        return existingBookedSeats;
	    }


	    // Method to get the ID of the showtime
	    public static int getShowtimeId(Connection connection, int theaterId, int movieId, String date, String time) throws SQLException {
	        int showtimeId = -1; // Initialize with an invalid value

	        String query = "SELECT id FROM showtime WHERE theater_id = ? AND movie_id = ? "
	        		+ "AND showdate = CAST(? AS DATE) AND showtime = ?" +
	        		"ORDER BY id DESC";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, theaterId);
	            preparedStatement.setInt(2, movieId);
	            preparedStatement.setString(3, date);
	            preparedStatement.setString(4, time);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    showtimeId = resultSet.getInt("id");
	                }
	            }
	        }

	        return showtimeId;
	    }
	    
	    // Method to calculate total cost
	    public static double getTotalCost(Connection connection, int theaterId, int movieId, String date, String time, int numSeats) throws SQLException {
	        double costPerSeat = getCostForSeats(connection, theaterId, movieId, date, time);
	        return costPerSeat * numSeats;
	    }

	    // Method to get the cost per seat
	    public static double getCostForSeats(Connection connection, int theaterId, int movieId, String date, String time) throws SQLException {
	        double cost = 0.0;

	        String sql = "SELECT cost FROM showtime WHERE theater_id = ? AND movie_id = ? AND showdate = CAST(? AS DATE) "
	        		+ "AND showtime = ? ORDER BY id DESC";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setInt(1, theaterId);
	            preparedStatement.setInt(2, movieId);
	            preparedStatement.setString(3, date);
	            preparedStatement.setString(4, time);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    cost = resultSet.getDouble("cost");
	                }
	            }
	        }

	        return cost;
	    }

	    // Method to record booking history
		public static void recordBookingHistory(Connection connection, int userId, int theaterId, int movieId, int showtimeId, 
				double totalCost, List<Integer> seatsToBook) throws SQLException {	    	
		    	String insertSql = "INSERT INTO bookingHistory (booked_by, theater_id, movie_id, showtime_id, "
		        		+ "total_cost, booked_seats) VALUES (?, ?, ?, ?, ?, ?)";
		        try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
		            insertStatement.setInt(1, userId);
		            insertStatement.setInt(2, theaterId);
		            insertStatement.setInt(3, movieId);
		            insertStatement.setInt(4, showtimeId);
		            insertStatement.setDouble(5, totalCost);
		            Integer[] seatsArray = seatsToBook.toArray(new Integer[seatsToBook.size()]);
		            insertStatement.setArray(6, connection.createArrayOf("integer", seatsArray));
		            insertStatement.executeUpdate();
		        }
		    }
		
		public static List<BookingDetails> getBookingHistoryRecords(Connection connection, int userId) throws SQLException {
	        List<BookingDetails> bookingDetailsList = new ArrayList<>();

	        String query = "SELECT bh.id, u.name AS booked_name, th.name AS theater_name, m.name AS movie_name, s.showdate, s.showtime, "
	        		+ "bh.total_cost, bh.booked_seats, bh.created_at " +
	                   "FROM bookingHistory bh " +
	                   "INNER JOIN theater th ON bh.theater_id = th.id " +
	                   "INNER JOIN movie m ON bh.movie_id = m.id " +
	                   "INNER JOIN showtime s ON bh.showtime_id = s.id " +
	                   "INNER JOIN \"user\" u ON bh.booked_by = u.id " + // Add join with user table
	                   "WHERE booked_by = ?" +
	                   "ORDER BY bh.id DESC";
	        
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            // Set user ID parameter
	            statement.setInt(1, userId);

	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                int bookingId = resultSet.getInt("id");
	                String bookedByUserId = resultSet.getString("booked_name");
	                String theaterName = resultSet.getString("theater_name");
	                String movieName = resultSet.getString("movie_name");
	                String showDate = resultSet.getString("showdate");
	                String showTime = resultSet.getString("showtime");
	                double totalCost = resultSet.getDouble("total_cost");

	                // Assuming the booked seats are stored as an array of integers in the database
	                Integer[] bookedSeatsArray = (Integer[]) resultSet.getArray("booked_seats").getArray();
	                List<Integer> bookedSeats = new ArrayList<>();
	                for (Integer seat : bookedSeatsArray) {
	                    bookedSeats.add(seat);
	                }

	                // Assuming the created_at column is a TIMESTAMP
	                String createdAt = resultSet.getString("created_at");

	                // Create a BookingDetails object and add it to the list
	                BookingDetails bookingDetails = new BookingDetails();
	                bookingDetails.setBookingId(bookingId);
	                bookingDetails.setBookedByName(resultSet.getString("booked_name"));
	                bookingDetails.setTheaterName(theaterName);
	                bookingDetails.setMovieName(movieName);
	                bookingDetails.setShowDate(showDate);
	                bookingDetails.setShowTime(showTime);
	                bookingDetails.setTotalCost(totalCost);
	                bookingDetails.setBookedSeats(bookedSeats);
	                bookingDetails.setCreatedAt(createdAt);
	                bookingDetailsList.add(bookingDetails);
	            }
	        }

	        return bookingDetailsList;
	    }
		
		public static boolean addTheater(Connection connection, Theater theater, int userId) throws SQLException {
	        String query = "INSERT INTO theater (created_by, name) VALUES (?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, userId);
	            statement.setString(2, theater.getName());

	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0;
	        }
	    }
		
	    public static boolean addMovie(Connection connection, Movie movie, int createdByUserId) throws SQLException {
	        String query = "INSERT INTO movie (created_by, name, director_name, hero_name) VALUES (?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, createdByUserId);
	            statement.setString(2, movie.getName());
	            statement.setString(3, movie.getDirectorName());
	            statement.setString(4, movie.getHeroName());

	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0;
	        }
	    }
	    
	    public static List<Movie> getMoviesName(Connection connection) throws SQLException {
	        List<Movie> movies = new ArrayList<>();
	        String query = "SELECT * FROM movie ORDER BY id DESC" ;
	        
	        try (PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {
	            
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                int createdBy = resultSet.getInt("created_by");
	                String name = resultSet.getString("name");
	                String directorName = resultSet.getString("director_name");
	                String heroName = resultSet.getString("hero_name");
	                // Add other attributes if needed

	                Movie movie = new Movie(id, createdBy, name, directorName, heroName);
	                movies.add(movie);
	            }
	        }
	        return movies;
	    }
	    
	    public static boolean removeMovie(Connection connection, int movieId) throws SQLException {
	        String query = "DELETE FROM movie WHERE id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, movieId);
	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0;
	        }
	    }
	    
	    public static boolean removeTheater(Connection connection, int theaterId) throws SQLException {
	        String query = "DELETE FROM theater WHERE id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, theaterId);

	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0;
	        }
	    }
	    
	    public static boolean addShowtime(Connection connection, int selectedUserId, int selectedMovieId,
                String showdate, String showtime, double cost, int totalSeats) throws SQLException {
				// Get theater IDs associated with the user
				List<Integer> theaterIds = getTheaterIdsForUser(connection, selectedUserId);
				
				Date parsedDate = parseDate(showdate);
				
				// Insert showtime data for each theater
				for (int theaterId : theaterIds) {
				// Calculate seats array
				Integer[] seatsArray = new Integer[totalSeats];
				for (int i = 1; i <= totalSeats; i++) {
				seatsArray[i - 1] = i;
				}
				
				// Insert showtime data into the showtime table
				String query = "INSERT INTO showtime (created_by, movie_id, theater_id, showdate, showtime, cost, total_seats, seats) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
				
				try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, selectedUserId);
				statement.setInt(2, selectedMovieId);
				statement.setInt(3, theaterId);
				statement.setDate(4, new java.sql.Date(parsedDate.getTime()));
				statement.setString(5, showtime);
				statement.setDouble(6, cost);
				statement.setInt(7, totalSeats);
				statement.setArray(8, connection.createArrayOf("INTEGER", seatsArray));
				
				int rowsAffected = statement.executeUpdate();
				if (rowsAffected <= 0) {
				return false; // Return false if insertion fails for any theater
				}
				}
				}
				
				return true; // Return true if insertion is successful for all theaters
				}
	    
	    private static Date parseDate(String dateString) {
	        try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            return dateFormat.parse(dateString);
	        } catch (ParseException e) {
	            e.printStackTrace(); // Handle ParseException properly
	            return null;
	        }
	    }
	    
	    
	    private static List<Integer> getTheaterIdsForUser(Connection connection, int userId) throws SQLException {
	        List<Integer> theaterIds = new ArrayList<>();

	        String query = "SELECT id FROM theater WHERE created_by = ?" +
	        		"ORDER BY id DESC";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, userId);

	            try (ResultSet resultSet = statement.executeQuery()) {
	                while (resultSet.next()) {
	                    int theaterId = resultSet.getInt("id");
	                    theaterIds.add(theaterId);
	                }
	            }
	        }

	        return theaterIds;
	    }
	    
	    public static List<ShowtimeDetails> getShowtimesForUser(Connection connection, int userId) throws SQLException {
	        List<ShowtimeDetails> showtimeDetailsList = new ArrayList<>();

	        String query = "SELECT s.id, th.name AS theater_name, m.name AS movie_name, s.showdate, s.showtime, "
	                + "s.cost, s.total_seats, s.seats, s.booked_seats, s.created_at " +
	                "FROM showtime s " +
	                "INNER JOIN theater th ON s.theater_id = th.id " +
	                "INNER JOIN movie m ON s.movie_id = m.id " +
	                "WHERE s.created_by = ? " +
	                "ORDER BY s.id DESC";

	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, userId);

	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                ShowtimeDetails showtimeDetails = new ShowtimeDetails();
	                showtimeDetails.setId(resultSet.getInt("id"));
	                showtimeDetails.setTheaterName(resultSet.getString("theater_name"));
	                showtimeDetails.setMovieName(resultSet.getString("movie_name"));
	                showtimeDetails.setShowdate(resultSet.getString("showdate"));
	                showtimeDetails.setShowtime(resultSet.getString("showtime"));
	                showtimeDetails.setCost(resultSet.getDouble("cost"));
	                showtimeDetails.setTotalSeats(resultSet.getInt("total_seats"));

	                // Assuming seats and booked_seats are arrays
	                java.sql.Array seatsArray = resultSet.getArray("seats");
	                java.sql.Array bookedSeatsArray = resultSet.getArray("booked_seats");

	                // Convert SQL arrays to Java arrays
	                if (seatsArray != null) {
	                    Integer[] seats = (Integer[]) seatsArray.getArray();
	                    showtimeDetails.setAvailableSeats(Arrays.asList(seats));
	                }

	                if (bookedSeatsArray != null) {
	                    Integer[] bookedSeats = (Integer[]) bookedSeatsArray.getArray();
	                    showtimeDetails.setBookedSeats(Arrays.asList(bookedSeats));
	                }

	                showtimeDetails.setCreatedAt(resultSet.getString("created_at"));

	                showtimeDetailsList.add(showtimeDetails);
	            }
	        }

	        return showtimeDetailsList;
	    }
	    
	    public static boolean removeShowtime(Connection connection, int showtimeId) throws SQLException {
	        String query = "DELETE FROM showtime WHERE id = ?";

	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, showtimeId);

	            int rowsAffected = statement.executeUpdate();
	            return rowsAffected > 0;
	        }
	    }
	    
	    public static void createTheater(Connection connection, int userId, String theaterName) throws SQLException {
	        String query = "INSERT INTO theater (created_by, name) VALUES (?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, userId);
	            statement.setString(2, theaterName);

	            statement.executeUpdate();
	        }
	    }
}
		

