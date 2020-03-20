function controlloTavolo() {
	var denominazione = $("#denominazioneInputId").val();
	var esperMin = $("#esperienzaMinInputId").val();
	var cifMin = $("#cifraMinInputId").val();

	isNumeric = /^[-+]?(\d+|\d+\.\d*|\d*\.\d+)$/;

	var errore = "";

	if (!denominazione || /^\s*$/.test(denominazione)) {
		errore += "Campo 'denominazione' non valido\n";
	}
	if (!esperMin || /^\s*$/.test(esperMin) || !isNumeric.test(esperMin)) {
		errore += "Campo 'esperienza minima' non valido\n";
	}
	if (!cifMin || /^\s*$/.test(cifMin) || !isNumeric.test(cifMin)) {
		errore += "Campo 'puntata minima' non valida\n";
	}

	if (errore != "") {
		alert(errore);
		return false;
	}

}

function controlloCercaTavolo() {

	var cifMin = $("#costoInputId").val();

	isNumeric = /^[-+]?(\d+|\d+\.\d*|\d*\.\d+)$/;

	var errore = "";
	
	if (!(!cifMin || /^\s*$/.test(cifMin))) {
	 if (!isNumeric.test(cifMin)) {
		
			errore += "Campo 'puntata minima' non valida\n";
		}
	}
	

	if (errore != "") {
		alert(errore);
		return false;
	}

}