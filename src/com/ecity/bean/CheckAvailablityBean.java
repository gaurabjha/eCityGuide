package com.ecity.bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class CheckAvailablityBean
 */
public class CheckAvailablityBean extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		Connection con = JdbcConnection.getConnection();
		PreparedStatement ps;
		out.println("<html>" +
				"<head>" +
				"<script>" +
				"function CloseWindow()" +
				"{" +
				"	window.open(\"\", '_self',\"\");"+
				"	window.close();" +
				"}" +
				"</script>" +
				"</head>" +
				"<body>");
		out.print("<center>");
		try {
			ps = con
					.prepareStatement("select count(*) from login where username=upper(?)");

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();

			if (rs.getInt(1) == 0) {
				out.println("<h2><font color=\"green\" ><b> " + username
						+ "</b> is available </font></h2>");
			} else {
				out
						.println("<h2><font color=\"red\" ><b>"
								+ username
								+ "</b> is not available... </font></h2>");
			}
		} catch (SQLException e) {

			out
					.print("<h2><font color=\"red\" >Some Error Occure ...Try Again<br>"
							+"Error --> "+e.getMessage()
							+"</font<h2>");
		}
		out.print("<input type=\"button\" onclick=\"CloseWindow()\" value=\"Done\" />");
		out.print("</center>" +
				"</body>" +
				"<html>");
	}
}
