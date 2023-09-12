package base;

import java.sql.*;

public class DBA_Connector {

	public static Connection getConnection() {
		Connection connect;
		
		String dbURL = "jdbc:oracle:thin:@localhost:1521/orcl.docker.internal";
		String username = "Scott";
		String password = "tiger";
		try {
			return DriverManager.getConnection(dbURL, username, password);
		} catch (Exception e) {
			System.err.println(e);
		}
		return null;

	}
}
