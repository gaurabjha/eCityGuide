package com.ecity.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.concurrent.Callable;

public class RateUtilBean {
	private String qid;
	private String best;
	private String better;
	private String good;
	private String ok;
	private String notok;

	public RateUtilBean() {
		// TODO Auto-generated constructor stub
	}

	public RateUtilBean(String qid, String best, String better, String good,
			String ok, String notok) {
		this.best = best;
		this.better = better;
		this.good = good;
		this.notok = notok;
		this.ok = ok;
		this.qid = qid;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getBest() {
		return best;
	}

	public void setBest(String best) {
		this.best = best;
	}

	public String getBetter() {
		return better;
	}

	public void setBetter(String better) {
		this.better = better;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	public String getNotok() {
		return notok;
	}

	public void setNotok(String notok) {
		this.notok = notok;
	}

	public boolean rate() {
		Connection con = JdbcConnection.getConnection();
		try {
			CallableStatement cs = con
					.prepareCall("{?= call fn_Rate_It(?,?,?,?,?,? )}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, best);
			cs.setString(3, better);
			cs.setString(4, good);
			cs.setString(5, ok);
			cs.setString(6, notok);
			cs.setString(7, qid);

			cs.execute();

			if (cs.getInt(1) > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
