package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.oracore.OracleType;

public class LoginUtil {

	private String u_id, userName, password, type, name;
	private Connection con = null;
	private int count;
	private boolean expired;

	public LoginUtil(String userName, String password) {
		this.userName = userName;
		this.password = password;
		con = JdbcConnection.getConnection();
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public void setU_id(String u_id) {
		this.u_id = u_id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getU_id() {
		return u_id;
	}

	public int getCount() {
		return count;
	}

	public boolean Validate() {
		try {
			CallableStatement login_sql = con
					.prepareCall("{? = call fn_login_user(?,?,?,?,?,?,?)}");
			login_sql.registerOutParameter(1, Types.INTEGER);
			login_sql.registerOutParameter(4, Types.VARCHAR);
			login_sql.registerOutParameter(5, Types.VARCHAR);
			login_sql.registerOutParameter(6, Types.VARCHAR);
			login_sql.registerOutParameter(7, Types.INTEGER);
			login_sql.registerOutParameter(8, Types.INTEGER);
			

			login_sql.setString(2, userName);
			login_sql.setString(3, password);
			login_sql.execute();
			
			if (login_sql.getInt(1) == 1) {
				u_id = login_sql.getString(4);
				name = login_sql.getString(5);
				type = login_sql.getString(6);
				count = login_sql.getInt(7);
				if(login_sql.getInt(8) == 1){
					expired = true;
				}
				else
				{
					expired = false;
				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
