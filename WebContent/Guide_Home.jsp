<jsp:include page="Welcome.jsp"><jsp:param
		value="Guide Home" name="title" /></jsp:include>

<h3><font color="green"><%=request.getAttribute("msg") == null ? "" : request
					.getAttribute("msg")%></font> <font color="red"><%=request.getAttribute("err") == null ? "" : request
					.getAttribute("err")%></font></h3>

<div id="button">
<center>
<%if(((Boolean)session.getAttribute("expired")).booleanValue() ==  false ){ %>

<a href='Guide_Question.jsp'>
<h6>Questions</h6>
<div>See All Questions</div>
</a><br> <a href='controller?ref=MyQuestions'>
<h6>MyQuestions</h6>
<div>My Answered Questions</div>
</a><br> <a href='controller?ref=MyAwards'>
<h6>MyAwards</h6>
<div>My Earnings</div>
</a><br> 


<%}else{
	%>
<h5><font color="red">Your Account has Expired please Extend Your Membership</font></h5>

<% }%>

<a href='controller?ref=ExtendMembership'
	onclick="return confirm('You will be charged Rs 500')">
<h6>Extend Membership</h6>
<div>Increase 30 days Validity</div>
</a><br></center></div>



<%@include file="footer.jsp"%>