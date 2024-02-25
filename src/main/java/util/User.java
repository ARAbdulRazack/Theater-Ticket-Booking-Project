package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class User {
		private int id;
	    private String name;
	    private String password;
	    private String phoneNumber;
	    private boolean isAdmin;
	    private boolean isOwner;
	    private String createdAt;
	    private String updatedAt;
		
	    public String getName() {
			return name;
		}
	    public int getId(){
	    	return id;
	    }
	    public void setId(int id) {
	    	this.id = id;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public boolean isAdmin() {
			return isAdmin;
		}
		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}
		public void setIsOwner(boolean isOwner) {
			this.isOwner = isOwner;
		}
	    public void setCreatedAt(String createdAt) {
	    	this.createdAt = createdAt;
		}
	    public void setUpdatedAt(String updatedAt) {
	       this.updatedAt = updatedAt;
	    }
		
	    public boolean isPhoneNumberUnique(Connection connection) throws SQLException {
	        String sql = "SELECT * FROM \"user\" WHERE phone_number = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, phoneNumber);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                return !resultSet.next(); 
	            }
	        }
	    }

	    public boolean registerUser(Connection connection) throws SQLException {
	        String sql = "INSERT INTO \"user\" (name, password, phone_number, is_admin, created_at) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, name);
	            preparedStatement.setString(2, password);
	            preparedStatement.setString(3, phoneNumber);
	            preparedStatement.setBoolean(4, isAdmin);
	            preparedStatement.setTimestamp(5, new Timestamp(new Date().getTime()));

	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	        }
	    }
	    
	    public boolean doesUserExist(Connection connection) throws SQLException {
	        String sql = "SELECT * FROM \"user\" WHERE phone_number = ? AND password = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, phoneNumber);
	            preparedStatement.setString(2, password);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                return resultSet.next(); 
	            }
	        }
	    }

	    public boolean isAdmin(Connection connection) throws SQLException {
	        String sql = "SELECT is_admin FROM \"user\" WHERE phone_number = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, phoneNumber);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                return resultSet.next() && resultSet.getBoolean("is_admin");
	            }
	        }
	    }
	    
	    public boolean isOwner(Connection connection) throws SQLException {
	        String sql = "SELECT is_owner FROM \"user\" WHERE phone_number = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, phoneNumber); // Assuming you have a method getId() in your User class
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getBoolean("is_owner");
	                }
	            }
	        }
	        return false;
	    }
	    
	    public static boolean validateUserCredentials(Connection connection, String phoneNumber, String password) throws SQLException {
	        String sql = "SELECT id FROM \"user\" WHERE phone_number = ? AND password = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, phoneNumber);
	            preparedStatement.setString(2, password);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    int userId = resultSet.getInt("id");

	                   
	                    return true; // User exists with the given credentials
	                }
	            }
	        }
	        return false; // User does not exist with the given credentials
	    }
	    
	    public static int getUserIdFromPhoneNumber(Connection connection, String phoneNumber) throws SQLException {
	        int userId = -1; // Default value indicating user not found
	        String query = "SELECT id FROM \"user\" WHERE phone_number = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, phoneNumber);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    userId = resultSet.getInt("id");
	                }
	            }
	        } catch (SQLException e) {
	            // Log or print the exception for debugging
	            e.printStackTrace();
	        }
	        // Log or print the userId for debugging
	        return userId;
	    }  
	    
	    public int getUserId(Connection connection) throws SQLException {
	        int userId = -1; // Default value if user ID is not found
	        String query = "SELECT id FROM \"user\" WHERE phone_number = ? AND password = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, phoneNumber);
	            statement.setString(2, password);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    userId = resultSet.getInt("id");
	                }
	            }
	        }
	        return userId;
	    }
	    
	    public static int createUser(Connection connection, String userName, String phoneNumber, String password, boolean isAdmin, boolean isOwner) throws SQLException {
	        String query = "INSERT INTO \"user\" (name, phone_number, password, is_admin, is_owner) VALUES (?, ?, ?, ?, ?) RETURNING id";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, userName);
	            statement.setString(2, phoneNumber);
	            statement.setString(3, password);
	            statement.setBoolean(4, isAdmin);
	            statement.setBoolean(5, isOwner);

	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                return resultSet.getInt("id");
	            }
	        }
	        return -1; // Return -1 if user creation fails
	    }
		
	    public static User getUserDetails(Connection connection, int userId) {
	        User user = null;

	        // Use JDBC to retrieve user details from the database
	        String query = "SELECT * FROM \"user\" WHERE id = ?";
	        try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, userId);

	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                user = new User();
	                user.setId(resultSet.getInt("id"));
	                user.setName(resultSet.getString("name"));
	                user.setPassword(resultSet.getString("password"));
	                user.setPhoneNumber(resultSet.getString("phone_number"));
	                user.setAdmin(resultSet.getBoolean("is_admin"));
	                user.setIsOwner(resultSet.getBoolean("is_owner"));
	                user.setCreatedAt(resultSet.getString("created_at"));
	                user.setUpdatedAt(resultSet.getString("updated_at"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle SQLException properly
	        }

	        return user;
	    }

	}