
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ecity.bean.GuideQuestionsBean"%>
<jsp:include page="Welcome.jsp">
	<jsp:param value="All Questions" name="title" />
</jsp:include>

<div id='questions'>

<%
	ArrayList<com.ecity.bean.GuideQuestionsBean> arr =  (ArrayList<com.ecity.bean.GuideQuestionsBean>)session.getAttribute("Question_Array");
	Iterator it = arr.iterator();
	if(arr.size()>0){
		while(it.hasNext())
		{
	GuideQuestionsBean question = (GuideQuestionsBean)it.next();
%>

<a	href="controller?ref=give_ans&qid=<%=question.getQ_id() %>"> 

	<span style="float: left">QID : <%=question.getQ_id() %></span> 
	<span style="float: right"> Date : <%=question.getQ_date() %></span><br>
	<hr>
	 <%=question.getQuestion() %>
</a>

<%
	}}else
	{
		%>
		<h2><center><font color="red">No Questions Found</font></center></h2>
		<%
	}
%>
<div>


<%@include file="footer.jsp"%>