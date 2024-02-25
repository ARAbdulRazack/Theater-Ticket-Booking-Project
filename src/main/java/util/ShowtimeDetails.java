package util;
import java.util.List;

public class ShowtimeDetails {
		private int id;	
		private String theatername;
		private String movieName;
	    private String showdate;
	    private String showtime;
		private double cost;
	    private int totalSeats;
	    private List<Integer> availableSeats;
	    private List<Integer> bookedSeats;
	    private String createdAt;

	public ShowtimeDetails(double cost, int totalSeats, List<Integer> availableSeats, List<Integer> bookedSeats) {
        this.cost = cost;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.bookedSeats = bookedSeats;
    }
    
    public ShowtimeDetails() {
    		this.id = id;
    		this.theatername = theatername;
			this.movieName = movieName;
			this.showdate = showdate;
			this.showtime = showtime;
			this.cost = cost;
			this.totalSeats = totalSeats;
			this.availableSeats = availableSeats;
	        this.bookedSeats = bookedSeats;
			this.createdAt = createdAt;
		}
    
    public void setId(int id) {
    	this.id = id;
		}
    
    public int getId() {
			return id;
		}
    
    public String getMovieName() {
 			return movieName;
 		}

 		public void setMovieName(String movieName) {
 			this.movieName = movieName;
 		}

 		public String getShowdate() {
 			return showdate;
 		}

 		public void setShowdate(String showdate) {
 			this.showdate = showdate;
 		}

 		public String getShowtime() {
 			return showtime;
 		}

 		public void setShowtime(String showtime) {
 			this.showtime = showtime;
 		}

 		public String getCreatedAt() {
 			return createdAt;
 		}

 		public void setCreatedAt(String createdAt) {
 			this.createdAt = createdAt;
 		}

 		public void setCost(double cost) {
 			this.cost = cost;
 		}

    public String getTheatername() {
		return theatername;
	}

	public void setTheaterName(String theatername) {
		this.theatername = theatername;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public void setAvailableSeats(List<Integer> availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void setBookedSeats(List<Integer> bookedSeats) {
		this.bookedSeats = bookedSeats;
	}

    public double getCost() {
        return cost;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public List<Integer> getAvailableSeats() {
        return availableSeats;
    }

    public List<Integer> getBookedSeats() {
        return bookedSeats;
    }
}
