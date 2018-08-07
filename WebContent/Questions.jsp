<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>&nbsp;eCity Guide &nbsp; | &nbsp; HOME</title>
<link rel='stylesheet' href='style.css' type='text/css' />
<link rel='shortcut icon' href='fav.png' />
<script src='slideshow.js'></script>
</head>
<body>
<div id='page'>
<div id='logo'><a href='index.html'><img alt='Home'
	src='images/logo.png' /></a></div>
<div id='nav'>
<ul>
	<li><a href='index.html'>Home</a></li>
	<li><a href='login.html'>Login</a></li>
	<li><a href='register.jsp'>Register</a></li>
</ul>
</div>
<center>
<div id='content' style="min-height: 40px">

<h1>Questions
<h1>

<h3>
<table>
	<tr>
		<td>Filter by</td>
		<td>Location</td>
		<td>:</td>
		<td>Select City : <select name="city">
			<option val=" ">Select City</option>
			<option val="KOL">Kolkata</option>
			<option val="AHM">Ahmedabad</option>
			<option val="MUM">Mumbai</option>
			<option val="BAN">Bangalore</option>
			<option val="OTH">Others</option>
		</select></td>
		<td>Date</td>
		<td>:</td>
		<td><input</td>
	</tr>
</table>

<div id='questions'><a href="controller?button=ans&qid=1">
QID: 1 <br>
<hr>
This is Question Number 1 </a> <a href="controller?button=ans&qid=2">QID
: 2 <br>
<hr>
This is Question Number 2 </a> <a href="controller?button=ans&qid=1">QID
: 3 <br>
<hr>
This is Question Number 3 </a></div>
</div>
</center>
<div id='footer'>&copy; All Rights Reserved e-City Guide Pvt. Ltd.</div>

</div>
</body>
</html>