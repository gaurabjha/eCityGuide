<jsp:include page="Welcome.jsp"><jsp:param value="My Rewards" name="title"/></jsp:include>

<br>
<br>
<h1>
	You Have Earned <b>Rs. <%=request.getAttribute("balance") %>.00</b>  from eCity Guide Pvt. Ltd &reg;
</h1><br><br>
<h3><span>
<a href='Home.jsp' >Go to Home</a></span>
</h3>

<%@include file="footer.jsp"%>