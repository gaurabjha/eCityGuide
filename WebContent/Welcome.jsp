<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>&nbsp;eCity Guide &nbsp; | &nbsp; <%=request.getParameter("title") %></title>


<meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
<link rel='stylesheet' href='style.css' type='text/css' />
<link rel='shortcut icon' href='fav.png' />
<script src='slideshow.js'></script>
<script src='DateValidations.js'></script>
<body>
<img id="slider" class="backgroundslider"
	src="images/SlideShow/pic1.jpg" height=100% width=100% />
<div id='page'>
<div id='logo'><a href='index.jsp'><img alt='Home'
	src='images/logo.png' style="height:90%;width:90%" /></a></div>
<div id='nav'>
<ul>
	<li><a href='Home.jsp'>Home</a></li>
	<li><a href='MyAccount.jsp'>Account</a></li>
	<li><a href='controller?ref=logout'>Logout</a></li>
</ul>
</div>
<div id='content'>
<%
	if (session.getAttribute("name") == null) {
%> <script type="text/javascript">
	alert('You are not Logged In');
	window.location = "index.jsp";
</script> <%
 	}
 %>

<h1>
<p><b id="welcome" >
	<img src="images/guide.png" />
					 &nbsp;&nbsp;Hi, <%=session.getAttribute("name")%>&nbsp;&nbsp;
					<img src="images/tourist.png" />
	<span style="float:right;font-size: small;">	ID : <%= session.getAttribute("uid")  %></span>
</b></p>	
<hr>
</h1>
<center>