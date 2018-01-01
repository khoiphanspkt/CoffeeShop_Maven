package com.hcmute.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static Connection conn;

	public static Connection getConnection() {
		try {
			String Url = "jdbc:mysql://localhost/CoffeeShop?user=root&password=123456&useSSL=true&charset=utf8";
			conn = DriverManager.getConnection(Url);
		} catch (SQLException ex) {
			// TODO: handle exception
		}
		
		return conn;
	}
}
