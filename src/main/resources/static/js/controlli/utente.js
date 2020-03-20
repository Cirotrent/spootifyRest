function controlloUtente() {
	var nome = $("#nomeInputId").val();
	var cognome = $("#cognomeInputId").val();
	var username = $("#usernameInputId").val();
	var password = $("#passwordInputId").val();
	
	isNumeric = /^[-+]?(\d+|\d+\.\d*|\d*\.\d+)$/;

	var ruoli = $("#ruoloInputId").val();
	var errore = "";

	if (!nome || /^\s*$/.test(nome)) {
		errore += "Compila il campo nome\n";
	}
	if (!cognome || /^\s*$/.test(cognome)) {
		errore += "Compila il campo cognome\n";
	}
	if (!username || /^\s*$/.test(username)) {
		errore += "Compila il campo username\n";
	}
	if (!password || /^\s*$/.test(password)) {
		errore += "Compila il campo password\n";
	}
	
	if (errore != "") {
		alert(errore);
		return false;
	}

}