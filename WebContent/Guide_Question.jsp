<jsp:include page="Welcome.jsp"><jsp:param
		value="Questions" name="title" /></jsp:include>

<script type="text/javascript">

<!--

function filterChange(){
	if(document.filter_form.filter.value=="both"){
		document.filter_form.to.disabled=false;
		document.filter_form.from.disabled=false;
		document.filter_form.loc.disabled=false;
	}else if(document.filter_form.filter.value=="date"){
		document.filter_form.to.disabled=false;
		document.filter_form.from.disabled=false;
		document.filter_form.loc.disabled=true;
	}else if(document.filter_form.filter.value=="loc"){
		document.filter_form.to.disabled=true;
		document.filter_form.from.disabled=true;
		document.filter_form.loc.disabled=false;
	}
}


//-->
</script>

<%
	int count = ((Integer)session.getAttribute("count")).intValue();
%>
<h3> You Can Answer <%= 10-count %> Questions Today </h3>

<%
if(count>=10){
	%>
	<br><br><h2><font color="red">You Cannot Answer anymore Questions Today </font></h2>
	<%
}else{ %>
<form action="controller" name="filter_form" onsubmit="return ValidateFromTo(this)" >
<table>
	<tr>
		<td>Filter By</td>
		<td>:</td>
		<td><select name="filter" onchange="filterChange()">
			<option value="both">Both</option>
			<option value="date">Date</option>
			<option value="loc">City</option>
		</select></td>
	</tr>
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
	<tr>
		<td>Select City</td>
		<td>:</td>
		<td><select name="loc">
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
		<td>
		<button type="submit" name="ref" value="question">Filter</button>
		</td>
		<td></td>
		<td><input type="reset" value="Reset" /></td>
	</tr>
</table>
</form>
<%} %>

<%@include file="footer.jsp"%>