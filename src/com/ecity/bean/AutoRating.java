package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AutoRating {

	public static void rate() {
		Connection con = JdbcConnection.getConnection();
		try {
			CallableStatement st = con.prepareCall("{call AutoRate()}");
			st.execute();
			System.out.println("Auto Rating Done");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Unable To Auto Rate");
		}

	}
}
