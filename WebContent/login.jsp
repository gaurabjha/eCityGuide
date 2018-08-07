<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>&nbsp;eCity Guide &nbsp; | &nbsp; LOGIN</title>


<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<link rel='stylesheet' href='style.css' type='text/css' />
<link rel='shortcut icon' href='fav.png' />
<script src='slideshow.js'></script>
<script src='LoginValidations.js'></script>
<body>
<img id="slider" class="backgroundslider"
	src="images/SlideShow/pic1.jpg" height=100% width=100% />
<div id='page'>
<div id='logo'><a href='index.jsp'><img alt='Home'
	src='images/logo.png' style="height:90%;width:90%" /></a></div>
<div id='nav'>
<ul>
	<li><a href='index.jsp'>Home</a></li>
	<li><a href='login.jsp'>Login</a></li>
	<li><a href='register.jsp'>Register</a></li>
</ul>
</div>
<div id='content'>
<center>


<br>
<form action="controller" name="login" method="post" onsubmit="return ValidateLogin(this)">
<fieldset>

<legend align="center">
<h1 ><img src="images/guide.png" />&nbsp;&nbsp;Login&nbsp;&nbsp;<img
	src="images/tourist.png" /></h1>
</legend>
<br>
<h3><font color="red"><%=request.getAttribute("err")==null?"":request.getAttribute("err")%></font><font color="orange"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%></font></h3>

<br>
<table>
	<tr>
		<td>User Name</td>
		<td>: &nbsp;</td>
		<td><input type="text" name="username" placeholder="Enter User Name" /></td>
	</tr>

	<tr>
		<td>Password</td>
		<td>: &nbsp;</td>
		<td><input type="password" name="password"
			placeholder="Enter Password" /></td>
	</tr>
</table>
<br>
<table>
	<tr>
		<td><input type="submit" name="ref" value="Login" /></td>
		<td></td>
		<td><input type="reset" value="Reset" /></td>
	</tr>
</table>
</fieldset>
</form>

<%@ include file="footer.jsp"%>