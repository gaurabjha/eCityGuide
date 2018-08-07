<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	if (session.getAttribute("name") == null) {
%>
		<script type="text/javascript" > 
			alert('You are not Logged In');
			window.location="index.jsp";;
		</script>
<%
	} else

	if (((String) session.getAttribute("type"))
			.equalsIgnoreCase("PAID")) {
		getServletContext().getRequestDispatcher("/Paid_Home.jsp").forward(
				request, response);
	} else if (((String) session.getAttribute("type"))
			.equalsIgnoreCase("guide")) {
		getServletContext().getRequestDispatcher("/Guide_Home.jsp").forward(
				request, response);
	} else if (((String) session.getAttribute("type"))
			.equalsIgnoreCase("free")) {
		getServletContext().getRequestDispatcher("/Free_Home.jsp").forward(
				request, response);
	}
%>