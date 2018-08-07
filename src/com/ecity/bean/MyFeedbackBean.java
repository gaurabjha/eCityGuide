package com.ecity.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyFeedbackBean {
	private String uid;

	public MyFeedbackBean(String uid) {
		this.uid = uid;
	}

	public GuideQuestionsBean getQuestion() {
		Connection con = JdbcConnection.getConnection();

		GuideQuestionsBean q = new GuideQuestionsBean();
		try {

			PreparedStatement ps = con
					.prepareStatement("select q.q_date q_date,q.question  question ,q.q_id q_id"
							+ "	from users u, questions q,login l"
							+ "	where u.u_id = q.u_id"
							+ " and l.u_id=u.u_id"
							+ " and q.u_id = ?" 
							+ " and q.status = 'UNRATED'"
							+ " and q.ans_count > 0");

			//syso
			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				q.setQ_date(rs.getString("q_date"));
				q.setQuestion(rs.getString("question"));
				q.setQ_id(rs.getString("q_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return q;
		
		
	}

}
