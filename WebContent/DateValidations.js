function validatedate(inpText) {

	var dateformat = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/([0-9]{4})$";

	var inpDate = inpText.value;

	// alert(inpDate);
	r = new RegExp(dateformat);
	// alert(r);
	var pdate = inpDate.split('/');

	if (r.test(inpDate)) {

		var dd = parseInt(pdate[0]);
		var mm = parseInt(pdate[1]);
		var yy = parseInt(pdate[2]);

		// creating list of days of month
		var ListofDays = [ 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 ];

		if (mm == 1 || mm > 2) {
			if (dd > ListofDays[mm - 1]) {
				alert('Invalid Date Format ...!!');
				inpText.focus();
				return false;
			}
		}
		if (mm == 2) {
			var lyear = false;
			if ((!(yy % 4) && yy % 100) || !(yy % 400)) {
				lyear = true;

			}
			if ((lyear == false) && (dd >= 29)) {
				alert('Invalid Date Format ...!!');
				inpText.focus();
				return false;
			}
			if ((lyear == true) && (dd > 29)) {
				alert('Invalid Date Format ...!!');
				inpText.focus();
				return false;
			}
		}

	} else {
		alert('Invalid Date Format ...!!');
		inpText.focus();
		return false;
	}
	return true;
}

function ValidateFromTo(frm) {
	if ((frm.filter.value != 'loc')
			&& (frm.from.value.length == 0 || frm.to.value.length == 0)) {
		alert(' Insert Date ');
		return false;
	} else {

		fromInp = frm.from.value.split('/')
		var fromDate = new Date(parseInt(fromInp[2]), parseInt(fromInp[1]),
				parseInt(fromInp[0]));
		toInp = frm.to.value.split('/')
		var toDate = new Date(parseInt(toInp[2]), parseInt(toInp[1]),
				parseInt(toInp[0]));

		if (toDate.getTime() - fromDate.getTime() <= 0) {
			alert('To Date must be Greater than From Date');
			frm.to.focus();
			return false;
		}
	}
	return true;
}