function ValidateRegister(frm) {

	// checking weather the fields are filled

	if (frm.name.value == '' || frm.username.value == ''
			|| frm.password.value == '' || frm.confirm.value == ''
			|| frm.phone.value == '' || frm.add.value == '') {
		alert('Fields Cannot Be Empty')
		return false;
	}

	else if ((frm.type.value != 'free')) {
		if (frm.b_name.value == '' || frm.b_acc_no.value == '') {
			alert('Fields Cannot Be Empty')
			return false;
			
		}
		if(frm.b_acc_no.value.length <10 ){
			alert('Account Number cannot be less than 10 Digits');
			return false;
		}
	}

	// if fields are not empty
	else {
		
		// User Name Validations( min lenth 5 max lenth 15 )
		if (usernameValidate(frm.username.value) == false) {
			return false;
		}

		// password min 8 characters
		if (frm.password.value.length < 8) {
			alert('Password must be atleast 8 Characters')
			return false;
		}
		
		// password miss match validations
		if (!(frm.password.value == frm.confirm.value)) {
			alert('Password Mismatch...!!!');
			return false;
		}

		// phone number validations
		if (isNaN(frm.phone.value) || frm.phone.value.length != 10) {
			alert('Invalid Phone Number');
			return false;
		}
		
		
	}
	return true;
}

function usernameValidate(username) {
	if (username.length = 0) {
		alert('Please Enter UserName');
		return false;
	}
	if (username.length < 5 || username.length > 15) {
		alert('Invalid Username (username Must me of 5-15 characters )');
		return false;
	}
	return true;
}

function userChange() {
	if (document.regForm.type.value != 'free') {
		document.regForm.b_name.disabled = false;
		document.regForm.b_acc_no.disabled = false;
		if (document.regForm.type.value == 'paid') {
			alert('You will be Charged Rs 100 for 30 Days (100 Question Validity)');
		}
		if (document.regForm.type.value == 'guide') {
			alert('You will be Charged Rs 500 for 30 days (10 Answers a day)');
		}
	} else {
		alert('Free User for 30 Days (1 Question Validity)');
		document.regForm.b_name.disabled = true;
		document.regForm.b_acc_no.disabled = true;
	}
}

function checkAvailablity() {
	if (usernameValidate(document.regForm.username.value) == false) {
		return;
	}
	var url = "check?username=" + document.regForm.username.value;
	window
			.open(url, '_blank',
					'height=150,width=300,left=500,top=200,titlebar=no,navigation=no,status=no');

}