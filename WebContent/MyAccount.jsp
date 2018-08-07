
<jsp:include page="Welcome.jsp"><jsp:param value="My Account" name="title"/></jsp:include>
<center>
<jsp:useBean id="acc"
	class="com.ecity.bean.AccountBean" scope="page"></jsp:useBean> <jsp:setProperty
	name="acc" property="uid" value="<%=session.getAttribute("uid")%>" />

<%
	acc.setAll();
%>
<h1><img src="images/guide.png" />&nbsp;&nbsp;My
Details&nbsp;&nbsp;<img src="images/guide.png" /></h1>
<br>
<table style="width:40%">
	<tr>
		<td><b>Full Name</b></td>
		<td>:</td>
		<td><jsp:getProperty property="name" name="acc" /></td>
	</tr>
	<tr>
		<td><b>User Name</b></td>
		<td>: &nbsp;</td>
		<td><jsp:getProperty property="username" name="acc" /></td>
	</tr>

	<tr>
		<td><b>Password</b></td>
		<td>: &nbsp;</td>
		<td><jsp:getProperty property="password" name="acc" /></td>
	</tr>
	<tr>
		<td><b>Phone Number</b></td>
		<td>: &nbsp;</td>
		<td><jsp:getProperty property="phone" name="acc" /></td>
	</tr>
	<tr>
		<td><b>Address</b></td>
		<td>:</td>
		<td><jsp:getProperty property="add" name="acc" /></td>
	</tr>
	<tr>
		<td><b>User Type</b></td>
		<td>: &nbsp;</td>
		<td><jsp:getProperty property="type" name="acc" /></td>
	</tr>
	<tr>
		<td><b>Registration Date</b></td>
		<td>: &nbsp;</td>
		<td><jsp:getProperty property="regDate" name="acc" /></td>
	</tr>
	<tr>
		<td><b>Membership End Date</b></td>
		<td>: &nbsp;</td>
		<td><jsp:getProperty property="expDate" name="acc" /></td>
	</tr>
	<tr>
		<td><b>Bank Name</b></td>
		<td>:</td>
		<td><jsp:getProperty property="b_name" name="acc" /></td>
	</tr>
	<tr>
		<td><b>Account Number</b></td>
		<td>:</td>
		<td><jsp:getProperty property="b_acc_no" name="acc" /></td>
	</tr>
</table>

<%@include file="footer.jsp"%>