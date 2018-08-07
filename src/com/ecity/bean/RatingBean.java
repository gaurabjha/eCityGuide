package com.ecity.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingBean {
	private String guide_name, G_ID, Q_ID;

	public String getGuide_name() {
		return guide_name;
	}

	public void setGuide_name(String guide_name) {
		this.guide_name = guide_name;
	}

	public String getG_ID() {
		return G_ID;
	}

	public void setG_ID(String g_id) {
		G_ID = g_id;
	}

	public String getQ_ID() {
		return Q_ID;
	}

	public void setQ_ID(String q_id) {
		Q_ID = q_id;
	}

	public RatingBean(String q_id) {
		Q_ID = q_id;
	}

	public RatingBean() {

	}

	public List<RatingBean> getDetails() {
		ArrayList<RatingBean> arr = new ArrayList<RatingBean>();
		Connection con = JdbcConnection.getConnection();
		try {
			PreparedStatement ps = con
					.prepareStatement("select a.g_id,g.name from answers a,guide g where g.u_id=a.g_id and a.q_id=?");
			
			ps.setString(1, getQ_ID());
			ResultSet rs = ps.executeQuery();
			RatingBean rb = null;
			while (rs.next()) {
				rb = new RatingBean();
				rb.setG_ID(rs.getString(1));
				rb.setGuide_name(rs.getString(2));
				arr.add(rb);
			}

		} catch (SQLException e) {
			System.out.println("some error occurred");

			e.printStackTrace();
		}
		return arr;

	}
}
