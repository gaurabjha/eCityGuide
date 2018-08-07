function ValidateLogin(frm){
	if( frm.username.value.length == 0 || frm.password.value.length == 0){
		alert('Insert Username/Password to Login');
		return false;
	}
	
	if (frm.username.value.length < 5 || frm.username.value.length > 15) {
	alert('Invalid Username...!!!');
	return false;
	}
	
	if(frm.password.value.length < 8 ){
		alert('Invalid Password...!!!');
		return false;
	}
	return true;
}


