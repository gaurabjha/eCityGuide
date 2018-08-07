<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>&nbsp;eCity Guide &nbsp; | &nbsp; REGISTER</title>
<script type="text/javascript">
	
</script>

<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<link rel='stylesheet' href='style.css' type='text/css' />
<link rel='shortcut icon' href='fav.png' />
<script src='slideshow.js'></script>
<script src='RegisterValidations.js'></script>
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


<noscript>
<h1><font color="red">Please Enable Java Script</font></h1>
</noscript>

<form action="controller" name="regForm"  method="post"  onsubmit="return ValidateRegister(this)">
<fieldset>
<legend align="center">
<h1><img src="images/guide.png" />&nbsp;&nbsp;Register&nbsp;&nbsp;<img
	src="images/tourist.png" /></h1></legend>
<h3><font color="red"><%=request.getAttribute("err")==null?"":request.getAttribute("err")%></font></h3>

<table>
	<tr>
		<td>Full Name</td>
		<td>:</td>
		<td><input type="text" name="name" placeholder="Enter Full Name" /></td>
	</tr>
	<tr>
		<td>User Name</td>
		<td>: &nbsp;</td>
		<td><input type="text" name="username"
			placeholder="Enter User Name" /></td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td align="center"><input type="button"
			onclick="checkAvailablity()" value="Check Availablity" /></td>
	</tr>

	<tr>
		<td>Password</td>
		<td>: &nbsp;</td>
		<td><input type="password" name="password"
			placeholder="Enter Password" /></td>
	</tr>
	<tr>
		<td>Confirm Password</td>
		<td>: &nbsp;</td>
		<td><input type="password" name="confirm"
			placeholder="Enter Password again" /></td>
	</tr>
	<tr>
	</tr>
	<tr>
		<td>Phone Number</td>
		<td>: &nbsp;</td>
		<td><input type="text" name="phone"
			placeholder="Enter Phone Number" /></td>
	</tr>
	<tr>
		<td>Address</td>
		<td>:</td>
		<td><textarea name="add" placeholder="Full Address with Pincode"></textarea></td>
	</tr>
	<tr>
		<td>User Type</td>
		<td>: &nbsp;</td>
		<td><select name="type" onchange="userChange()">
			<option value="free">Free User</option>
			<option value="paid">Paid User</option>
			<option value="guide">Guide</option>
		</select></td>
	</tr>
	<tr>
		<td>Bank Name</td>
		<td>:</td>
		<td><input type="text" name="b_name"
			placeholder="Enter Bank Name" disabled=true /></td>
	</tr>
	<tr>
		<td>Account Number</td>
		<td>:</td>
		<td><input type="text" name="b_acc_no"
			placeholder="Enter Bank Account Number" disabled=true /></td>
	</tr>
</table>


<table>
	<tr>
		<td><input type="submit" name="ref" value="Register"
			disabled=true /></td>
		<td></td>
		<td><input type="reset" value="Reset" /></td>
	</tr>
</table>
</fieldset>
<script type="text/javascript">
	document.regForm.ref.disabled = false;
</script></form>

<%@ include file="footer.jsp"%>