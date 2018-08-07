package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class AccountBean {
	String uid, username, type, password, name, phone, regDate, expDate,
			b_name, b_acc_no, add;

	public AccountBean() {

	}

	public AccountBean(String add, String b_acc_no, String b_name,
			String expDate, String name, String password, String phone,
			String regDate, String type, String username) {
		this.add = add;
		this.b_acc_no = b_acc_no;
		this.b_name = b_name;
		this.expDate = expDate;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.regDate = regDate;
		this.type = type;
		this.username = username;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public String getB_name() {
		return b_name;
	}

	public void setB_name(String b_name) {
		if (b_name == null) {
			this.b_name = "N/A";
		} else {
			this.b_name = b_name;
		}
	}

	public String getB_acc_no() {
		return b_acc_no;
	}

	public void setB_acc_no(String b_acc_no) {
		if (b_acc_no == null) {
			this.b_acc_no = "N/A";
		} else {
			this.b_acc_no = b_acc_no;
		}
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAll() {
		Connection con = JdbcConnection.getConnection();
		CallableStatement cs = null;
		try {
			cs = con
					.prepareCall("{ call prod_account(?,?,?,?,?,?,?,?,?,?,?) }");

			cs.setString(1, this.getUid());
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.registerOutParameter(10, Types.VARCHAR);
			cs.registerOutParameter(11, Types.VARCHAR);

			cs.execute();

			setUsername(cs.getString(2));
			setPassword(cs.getString(3));
			setType(cs.getString(4));
			setName(cs.getString(5));
			setPhone(cs.getString(6));
			setRegDate(cs.getString(7));
			setExpDate(cs.getString(8));
			setB_acc_no(cs.getString(9));
			setB_name(cs.getString(10));
			setAdd(cs.getString(11));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return uid + username + type + password + name + phone + regDate
				+ expDate + b_name + b_acc_no + add;
	}
}
