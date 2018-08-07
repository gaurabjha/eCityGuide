package com.ecity.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;

public class JdbcConnection {
	private static String driver,url,username,password;

	
	public static Connection getConnection() {
		Connection con = getConnection(true);
		return con;

	}

	public static Connection getConnection(boolean flag) {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(flag);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void setCredentials(ServletContext servletContext) {
		JdbcConnection.driver=servletContext.getInitParameter("driver");
		JdbcConnection.url=servletContext.getInitParameter("url");
		JdbcConnection.username=servletContext.getInitParameter("username");
		JdbcConnection.password =servletContext.getInitParameter("password");
		
	}
}
