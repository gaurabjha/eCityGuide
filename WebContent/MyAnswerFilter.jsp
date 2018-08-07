<jsp:include page="Welcome.jsp">
<jsp:param value="Filter Answer" name="title"/>
</jsp:include>
<script type="text/javascript">
function ValidateMyFilter(frm){
	if((frm.to.value.length == 0) || (frm.from.value.length == 0) )
	{
		alert('Enter Dates');
		return false;
	}
	return true;
}
</script>
<br>
<h1>Filter By Date</h1>
<form action="controller" name="MyAnswerFilter" onsubmit="return ValidateMyFilter(this)">

<table>	
	<tr>
		<td>From Date</td>
		<td>:</td>
		<td><input type="text" name="from" placeholder="dd/mm/yyyy" onblur="validatedate(this)"></td>
	</tr>
	<tr>
		<td>To Date</td>
		<td>:</td>
		<td><input type="text" name="to" placeholder="dd/mm/yyyy" onblur="validatedate(this)"></td>
	</tr>

</table>
<table>
	<tr>
		<td>
		<button type="submit" name="ref" value="MyAnswers">Filter</button>
		</td>
		<td></td>
		<td><input type="reset" value="Reset" /></td>
	</tr>
</table>

</form>

<%@include file="footer.jsp"%>