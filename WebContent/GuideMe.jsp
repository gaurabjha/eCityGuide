<jsp:include page="Welcome.jsp">
<jsp:param value="GuideMe" name="title"/>
</jsp:include>
<script type="text/javascript" >
function GuideMeValidate(frm){
	if(frm.question.value.length == 0)
	{
		alert('Enter a Question');
		return false;
	}
	if(frm.loc.value.length == 0){
		alert('Select a City');
		return false;
	}
	return true;
}
</script>
<form action="controller" onsubmit="return GuideMeValidate(this)">
<font color=orange>
*You Have
<%=session.getAttribute("count")%>
Questions Left*

<%if(((Integer)session.getAttribute("count")).intValue()>0){ %>

</font>
<h3><font color=red><%=request.getAttribute("err")==null?"":request.getAttribute("err") %></font></h3>
<table>
	<tr>
		<td align="center">
		<h2>Enter a Question</h2>
		</td>
	</tr>
	<tr>
		<td><textarea style="width: 300px;" name="question" placeholder="Enter Question"></textarea></td>
	</tr>
	<tr>
		<td align="center" >Select City : <select name="loc">
			<option value="">Select City</option>
			<option value="KOL">Kolkata</option>
			<option value="DEL">Delhi</option>
			<option value="AHM">Ahmedabad</option>
			<option value="MUM">Mumbai</option>
			<option value="BAN">Bangalore</option>
			<option value="OTH">Others</option>
		</select></td>
	</tr>
</table>
<table>
	<tr>
		<td><input type="submit" name="ref" value="Ask" /></td>
		<td></td>
		<td><input type="reset" value="Reset" /></td>
	</tr>
</table>
</form>
<%}else{
	%>
		<h3><font color=red>You Have No Questions Left</font></h3>

<%
}
%>
<%@include file="footer.jsp"%>