package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class InsertQuestionBean {
	UsersQuestionBean qBean = null;

	public InsertQuestionBean(UsersQuestionBean bean) {
		//System.out.println("I'm in InsertQuestion BEan");
		qBean = bean;
	}

	public boolean insert() {
		System.out.println("I'm in Insert");
		Connection con = JdbcConnection.getConnection();
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{?=call fn_InsertQuestion(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, qBean.getUid());
			cs.setString(3, qBean.getLoc());
			cs.setString(4, qBean.getQuestion());
			cs.execute();
			if (cs.getInt(1) == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
}
