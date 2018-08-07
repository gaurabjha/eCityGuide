package com.ecity.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyQuestionsBean {

	String uid;

	public MyQuestionsBean(String uid) {
		this.uid = uid;
	}

	public List<GuideQuestionsBean> getMyQuestions() {

		Connection con = JdbcConnection.getConnection();

		ArrayList<GuideQuestionsBean> ques = new ArrayList<GuideQuestionsBean>();
		try {

			PreparedStatement ps = con
					.prepareStatement("select l.type type,q.q_date q_date,u.name name ,q.question  question ,q.q_id q_id"
							+ "	from users u, questions q,login l"
							+ "	where u.u_id = q.u_id"
							+ " and l.u_id=u.u_id"
							+ " and q.q_id in ( select q_id from answers where g_id = ?  )");

			ps.setString(1, uid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GuideQuestionsBean q = new GuideQuestionsBean();
				q.setType(rs.getString("type"));
				q.setQ_date(rs.getString("q_date"));
				q.setName(rs.getString("name"));
				q.setQuestion(rs.getString("question"));
				q.setQ_id(rs.getString("q_id"));

				ques.add(q);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ques;
	}

}
