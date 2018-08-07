package com.ecity.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersAnswersBean {
	private String uid, from_date, to_date;

	public UsersAnswersBean() {
	}

	public UsersAnswersBean(String uid, String from_date, String to_date) {
		this.from_date = from_date;
		this.to_date = to_date;
		this.uid = uid;
		if (from_date == null) {
			this.from_date = "01/01/1991";
		}
		if (to_date == null) {
			this.to_date = "01/01/2075";
		}
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFrom_date() {
		return from_date;
	}

	public void setFrom_date(String from_date) {
		this.from_date = from_date;
		if (from_date == null) {
			this.from_date = "01/01/1991";
		}
	}

	public String getTo_date() {
		return to_date;
	}

	public void setTo_date(String to_date) {
		this.to_date = to_date;
		if (to_date == null) {
			this.to_date = "01/01/2075";
		}
	}

	public List<GuideQuestionsBean> getMyAns() {
		List<GuideQuestionsBean> userQues = new ArrayList<GuideQuestionsBean>();
		Connection con = JdbcConnection.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("select Q_ID,QUESTION,Q_DATE from QUESTIONS "
							+ " where U_ID=?"
							+ " and Q_DATE between to_date(?,'DD/MM/YYYY') and to_date(?,'DD/MM/YYYY')");

			ps.setString(1, uid);
			ps.setString(2, from_date);
			ps.setString(3, to_date);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				GuideQuestionsBean q = new GuideQuestionsBean();
				q.setQ_id(rs.getString(1));
				q.setQuestion(rs.getString(2));
				q.setQ_date(rs.getString(3));
				userQues.add(q);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userQues;

	}

}
