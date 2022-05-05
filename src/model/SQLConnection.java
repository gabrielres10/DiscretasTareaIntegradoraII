package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

	private static final String CONTROL = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/persondatabase";
	private static final String USER = "root";
	private static final String PASS = "root";

	static {
		try {
			Class.forName(CONTROL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error UnU");
			e.printStackTrace();
		}
	}

	public Connection sqlConnection() {
		// TODO Auto-generated method stub

		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASS);

			System.out.println("Conexión ok!");
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
		}

		return connection;
	}

}
