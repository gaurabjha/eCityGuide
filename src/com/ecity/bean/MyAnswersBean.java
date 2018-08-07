package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

public class MyAnswersBean {

	// data for questions
	String uid, q_id, loc, question, q_date;
	int ans_count;

	// data for ans

	ArrayList<AnswersBean> answers = new ArrayList<AnswersBean>();;

	public MyAnswersBean() {

	}

	public void getAnswer() {

		Connection con = JdbcConnection.getConnection();
		ResultSet rs = null;
		CallableStatement cs = null;
		try {
			cs = con.prepareCall("{call prod_myquestions(?,?,?,?,?,?,?)}");

			cs.setString(1, this.getUid());

			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.INTEGER);
			cs.registerOutParameter(7, OracleTypes.CURSOR);

			cs.executeUpdate();

			q_id = cs.getString(2);
			loc = cs.getString(3);
			question = cs.getString(4);
			q_date = cs.getString(5);
			ans_count = cs.getInt(6);

			if (ans_count > 0) {
				AnswersBean ans = null;
				rs = (ResultSet) cs.getObject(7);
				while (rs.next()) {
					ans = new AnswersBean();
					ans.setG_name(rs.getString("name"));
					ans.setAnswer(rs.getString("ans"));
					ans.setRating(rs.getString("rating"));
					answers.add(ans);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getQ_id() {
		return q_id;
	}

	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQ_date() {
		return q_date;
	}

	public void setQ_date(String q_date) {
		this.q_date = q_date;
	}

	public int getAns_count() {
		return ans_count;
	}

	public void setAns_count(int ans_count) {
		this.ans_count = ans_count;
	}

	public ArrayList<AnswersBean> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<AnswersBean> answers) {
		this.answers = answers;
	}

}
