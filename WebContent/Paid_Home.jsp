
<jsp:include page="Welcome.jsp"><jsp:param value="Paid Home" name="title"/></jsp:include>

<h3>
<font color="green"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%></font>
<font color="red"><%=request.getAttribute("err")==null?"":request.getAttribute("err")%></font>
</h3>

<div id="button"><center>


<%if(((Boolean)session.getAttribute("expired")).booleanValue() ==  false ){ %>
<a href='controller?ref=GuideMe'><h6>GuideMe</h6><div>Ask Questions</div></a><br>
<a href='MyAnswerFilter.jsp'><h6>MyAnswers</h6><div>See all My Answers</div></a><br>
<a href='controller?ref=MyFeedback'><h6>MyFeedback</h6><div>Rate My answers</div></a>

<br>
<%}else{
	%>
<h5><font color="red">Your Account has Expired please Extend Your Membership</font></h5>

<% }%>
<a href='controller?ref=ExtendMembership' onclick="return confirm('You will be charged Rs 100')"><h6>Extend Membership</h6><div>Increase 30 days Validity</div> </a><br><br>
</h1></center>
</div>


<%@include file="footer.jsp"%>