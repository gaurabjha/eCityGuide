
<%@page import="com.ecity.bean.AnswersBean"%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.ecity.bean.GuideQuestionsBean"%>
<%@page import="com.ecity.bean.RatingBean"%>
<jsp:include page="Welcome.jsp">
	<jsp:param value="MyFeedback" name="title" />
</jsp:include>


<%
	GuideQuestionsBean question = (GuideQuestionsBean) request
			.getAttribute("question");
if(question.getQ_id()!=null){
%>


<span style="float: left">QID : <%=question.getQ_id()%></span>


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
	Iterator<AnswersBean> it = arr.iterator();
	int counter = 0;
	if (arr.size() > 0) {
		while (it.hasNext()) {
			counter++;
			AnswersBean ans = (AnswersBean) it.next();
%>
<div id="answer"><span style="float: left;">Answer <%=counter%>
- Guide <%=ans.getG_name()%></span> <br>
<hr>
<%=ans.getAnswer()%></div>
<br>
<br>

<%
	}
%>
<script type="text/javascript">

	function DisableMe(inpRadio){
	
		/*setting the rate to the hidden repective rate inputs*/
		document.getElementById(inpRadio.name).value = inpRadio.value;


		/* now disabling the all the buttons of clicked rating */
		radioBox = document.getElementsByName(inpRadio.name);
	
		for(  index = 0 ; index < radioBox.length ; index++)
		{
			radioBox[index].disabled=true;
		}

		/* now disabling the all the buttons with same guide name */
		radioBox2 = document.getElementsByClassName(inpRadio.value);

		for(  index = 0 ; index < radioBox2.length ; index++)
		{
			radioBox2[index].disabled=true;
		}
		return true;	
	}
	
</script>
<form action="controller" name="MyFeedBackForm" onsubmit="return MyFeedackValidate(this)">

	<input type="hidden" name="qid" value="<%=question.getQ_id()%>" />
	<input type="hidden" id="best" name="best_rate"  />
	<input type="hidden" id="better" name="better_rate"  />
	<input type="hidden" id="good"  name="good_rate" />
	<input type="hidden" id="ok"  name="ok_rate"  />
	<input type="hidden" id="notok" name="notok_rate"  /> <%
 	ArrayList<RatingBean> RatesArr = (ArrayList<RatingBean>) request
 			.getAttribute("ids");
 	Iterator<RatingBean> RatesIt = RatesArr.iterator();
 %>
<table  >
	<tr>
		<th>Best</th>
		<th>Better</th>
		<th>Good</th>
		<th>Ok</th>
		<th>Not Ok</th>
	</tr>
	<%
		while (RatesIt.hasNext()) {
			RatingBean rate = RatesIt.next();
	%>
	<tr  >
		<td><input type="radio" class="<%=rate.getG_ID()%>" name="best"  value="<%=rate.getG_ID()%>" 
			onclick=" return ( confirm('Good Rating Goes to <%=rate.getGuide_name()%> ') && DisableMe(this));"/><%=rate.getGuide_name()%></td>
		
		<td><input type="radio" class="<%=rate.getG_ID()%>" name="better"  value="<%=rate.getG_ID()%>" 
			onclick="return ( confirm('Good Rating Goes to <%=rate.getGuide_name()%> ') && DisableMe(this));"/><%=rate.getGuide_name()%></td>

		<td><input type="radio" class="<%=rate.getG_ID()%>" name="good"  value="<%=rate.getG_ID()%>" 
			onclick="return ( confirm('Good Rating Goes to <%=rate.getGuide_name()%> ') && DisableMe(this));"/><%=rate.getGuide_name()%></td>

		<td><input type="radio" class="<%=rate.getG_ID()%>" name="ok"  value="<%=rate.getG_ID()%>" 
			onclick="return ( confirm('Good Rating Goes to <%=rate.getGuide_name()%> ') && DisableMe(this));"/><%=rate.getGuide_name()%></td>

		<td><input type="radio" class="<%=rate.getG_ID()%>" name="notok"  value="<%=rate.getG_ID()%>" 
			onclick="return ( confirm('Good Rating Goes to <%=rate.getGuide_name()%> ') && DisableMe(this));"/><%=rate.getGuide_name()%></td>
	</tr>

	<%
		}
	%>

</table>

<input type="submit" name="ref" value="Rate" />&nbsp;&nbsp;&nbsp;&nbsp;</form>
<%}
	} else {%><br><br>
	<h3><font color="red">No Answerd Questions</h3>
<%} %>
<%@include file="footer.jsp"%>