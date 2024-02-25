package util;

import java.sql.*;

public class ConnectionUtil {

	public static Connection getConnection() {

		String jdbcUrl = "jdbc:postgresql://localhost:5432/student";
        String username = "postgres";
        String password = "user";

		Connection conn = null;

		try {

			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(jdbcUrl, username, password);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return conn;

	}
	
	public static void close(Connection connection, PreparedStatement presta) {
		try {
			if (presta != null) {
				presta.close();
			}
			if (connection != null) {

				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection connection, PreparedStatement presta, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (presta != null) {
				presta.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static PreparedStatement prepareStatement(String string) {
		
		return null;
	}

}
