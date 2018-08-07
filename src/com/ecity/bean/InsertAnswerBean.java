package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class InsertAnswerBean {

	private String g_id;
	private String q_id;
	private String ans;
	private String q_type;

	public InsertAnswerBean() {

	}

	public InsertAnswerBean(String g_id, String q_id, String ans, String q_type) {
		this.ans = ans;
		this.g_id = g_id;
		this.q_id = q_id;
		this.q_type = q_type;
	}

	public String getG_id() {
		return g_id;
	}

	public void setG_id(String g_id) {
		this.g_id = g_id;
	}

	public String getQ_id() {
		return q_id;
	}

	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getQ_type() {
		return q_type;
	}

	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}

	public boolean insert() {
		Connection con = JdbcConnection.getConnection();
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{? = call fn_ins_ans(?,?,?,?) }");

			cs.registerOutParameter(1, Types.INTEGER);
			
			cs.setString(2, g_id);
			cs.setString(3, q_id);
			cs.setString(4, ans);
			cs.setString(5, q_type);
			
			cs.executeUpdate();
			
			if(cs.getInt(1)==1){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
