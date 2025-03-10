package com.jdc.ps.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String URL = "jdbc:mysql://localhost:3306/jdbc_db";
	private static final String USR = "onestop";
	private static final String PWD = "onestop";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USR, PWD);
	}
	
}
