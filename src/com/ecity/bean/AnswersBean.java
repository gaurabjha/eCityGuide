package com.ecity.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswersBean {
	String g_name, answer, rating,ans_date;



	public AnswersBean(String g_name, String answer, String rating,
			String ans_date) {
		this.ans_date = ans_date;
		this.answer = answer;
		this.g_name = g_name;
		this.rating = rating;
	}

	public AnswersBean() {
		// TODO Auto-generated constructor stub
	}

	public String getAns_date() {
		return ans_date;
	}

	public void setAns_date(String ans_date) {
		this.ans_date = ans_date;
	}

	public String getG_name() {
		return g_name;
	}

	public void setG_name(String g_name) {
		this.g_name = g_name;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public List<AnswersBean> fetchAnswers(String q_id) {
		Connection con = JdbcConnection.getConnection();
		List<AnswersBean> ans = new ArrayList<AnswersBean>();
		try {
			PreparedStatement ps = con
					.prepareStatement(" select g.name,a.answer,a.rating,a.ans_date"
							+ " from answers a,guide g"
							+ " where a.g_id=g.u_id"
							+ " and a.q_id LIKE ? ");
			/*
			 * System.out.println(" select g.name,a.answer,a.rating,a.ans_date"
			*		+ " from answers a,guide g"
			*		+ " where a.g_id=g.u_id"
			*		+ " and a.q_id LIKE ? ");
			*
			*/
			ps.setString(1, q_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ans.add(new AnswersBean(rs.getString(1), rs.getString(2), 
						rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ans;

	}
}
