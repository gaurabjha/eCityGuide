
<%@page import="com.ecity.bean.AnswersBean"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ecity.bean.GuideQuestionsBean"%>

<jsp:include page="Welcome.jsp">
	<jsp:param value="Answers" name="title" />
</jsp:include>


<%
	GuideQuestionsBean question = (GuideQuestionsBean) request
			.getAttribute("question");
%>


<span style="float: left">QID : <%=question.getQ_id()%></span>

<%if(((String)session.getAttribute("type")).equalsIgnoreCase("guide")){ %>

	Question By <%=question.getType()%>
	User <%=question.getName()%>

<%} %>

<span style="float: right"> Date : <%=question.getQ_date()%></span>
<br>
<hr>
<%=question.getQuestion()%>
<hr>
<hr>
<br>
<br>
<%
	ArrayList<AnswersBean> arr = (ArrayList<AnswersBean>) request
			.getAttribute("answers");
	Iterator it = arr.iterator();
	int counter = 0;
	boolean allowAns=true;
	if (arr.size() > 0) {
		while (it.hasNext()) {
			counter++;
			AnswersBean ans = (AnswersBean) it.next();
			if(ans.getG_name().equalsIgnoreCase((String)session.getAttribute("name"))){
				allowAns=false;
			}
%>
<div id="answer" >
<span style="float: left;">Answer <%=counter%> - Guide <%=ans.getG_name()%></span>
<%if(!((String)session.getAttribute("type")).equalsIgnoreCase("guide")){ %>
<span style="float: right"> Rating : <%=ans.getRating()==null?"Unrated":ans.getRating()%></span>
<%} %>
<br>
<hr>
<%=ans.getAnswer()%></div>
<br>
<br>

<%
	}
	
	
	} else {
%> <br>
<br>
<h3><font color="red">No Answers found</font></h3>
<%
	}if(((String)session.getAttribute("type")).equalsIgnoreCase("guide") && allowAns){
%>
	<div id="answer">Answer <%=counter+1%> - Guide <%=session.getAttribute("name")%>
	<hr>
	<script type="text/javascript">
		function AnsValidate(frm){
			if( frm.ans.value.length==0 ){
				alert('cannot submit empty answer');
				return false;
			}
			return true;
		}
	</script>
	<form action="controller" name="ans_form" onsubmit="return AnsValidate(this)">
	<textarea name="ans" placeholder="Put Your Answer Here" ></textarea>
	<br>
	<input type="hidden" name="q_id" value="<%=question.getQ_id()%>"/>
	<input type="hidden" name="q_type" value="<%=question.getType()%>"/>
	<input type="submit" name="ref" value="answer"/>
	</form>
	
<%
	}
%>

</div>
<%@include file="footer.jsp"%>