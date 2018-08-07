
<jsp:include page="Welcome.jsp">
<jsp:param value="Free Home" name="title"/>
</jsp:include>


<h3>
<font color="green"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%></font>
<font color="red"><%=request.getAttribute("err")==null?"":request.getAttribute("err")%></font>
</h3>

<br>
<div id="button" >
<center>
<%if(((Boolean)session.getAttribute("expired")).booleanValue() ==  false ){ %>
<a href='controller?ref=GuideMe'>

	<h6>GuideMe</h6>
	<div>Ask Questions</div>

</a><br>


<a href='controller?ref=MyAnswers'>

	<h6>MyAnswers</h6>
	<div>See Answers to My Question</div>	

</a><br>

<%}else{
	%>
<h5><font color="red">Your Account has Expired please Extend Your Membership</font></h5>
<% }%>

</center>

</div>


<!-- <a href='Upgrade.jsp'>Become Paid User</a><br>  -->

<%@include file="footer.jsp"%>