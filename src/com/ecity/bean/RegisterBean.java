package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegisterBean {

	private String username, password, type;
	private String name, phone, b_name, b_acc_no, address;

	public RegisterBean(String address, String b_acc_no, String b_name,
			String name, String password, String phone, String type,
			String username) {
		this.address = address;
		this.b_acc_no = b_acc_no;
		this.b_name = b_name;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.type = type;
		this.username = username;
	}

	public boolean register() {
		Connection con = JdbcConnection.getConnection();
		CallableStatement cs;
		try {
			cs = con.prepareCall("{ call pro_register (?,?,?,?,?,?,?,?)}");

			cs.setString(1, username);
			cs.setString(2, password);
			cs.setString(3, type );
			cs.setString(4, name);
			cs.setString(5, phone);
			cs.setString(6, b_name);
			cs.setString(7, b_acc_no);
			cs.setString(8, address);

			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

}
