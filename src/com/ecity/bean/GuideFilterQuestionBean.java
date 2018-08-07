package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleTypes;

public class GuideFilterQuestionBean {
	private String g_id;
	private String filter;
	private String loc;
	private String to_date;
	private String from_date;

	public GuideFilterQuestionBean(String g_id, String filter, String loc,
			String to_date, String from_date) {
		this.g_id = g_id;
		this.filter = filter;
		this.from_date = from_date;
		this.loc = loc;
		this.to_date = to_date;
		if (filter.equalsIgnoreCase("date")) {
			this.loc = "%";
		}
		if (filter.equalsIgnoreCase("loc")) {
			this.from_date = "01/01/1991";
			this.to_date = "01/01/2075";
		}
	}

	public String getG_id() {
		return g_id;
	}

	public String getFilter() {
		return filter;
	}

	public String getLoc() {
		return loc;
	}

	public String getTo_date() {
		return to_date;
	}

	public String getFrom_date() {
		return from_date;
	}

	public ArrayList<GuideQuestionsBean> fetchQuestions() {
		Connection con = JdbcConnection.getConnection();

		ArrayList<GuideQuestionsBean> ques = new ArrayList<GuideQuestionsBean>();
		try {
			
			PreparedStatement ps = con
					.prepareStatement("select l.type type,q.q_date q_date,u.name name ,q.question  question ,q.q_id q_id"
							+ "	from users u, questions q,login l"
							+ "	where u.u_id = q.u_id"
							+ " and l.u_id=u.u_id"
							+ "	and q.status <> 'RATED'"
							+ "	and loc like '"+ loc + "' "
							+ "	and q_Date between to_date('"
							+ from_date
							+ "','DD/MM/YYYY') and to_date('"
							+ to_date
							+ "','DD/MM/YYYY')+1"
							+ "	and q_id not in (select q_id from answers where g_id='"+ g_id + "') "  
							+ " and ans_count < 5 " 
							+ " and q_id not in (select q_id from questions " 
							+ " where ans_count=2" 
							+ " and status in 'N/A')");
			 
							
			//System.out.println(filter + loc + from_date + to_date + g_id);
			//System.out.println(g_id);
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
