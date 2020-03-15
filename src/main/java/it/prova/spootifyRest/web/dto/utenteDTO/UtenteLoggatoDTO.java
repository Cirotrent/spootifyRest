package it.prova.spootifyRest.web.dto.utenteDTO;

import it.prova.spootifyRest.model.Utente;

public class UtenteLoggatoDTO {

	private String nome;
	private String cognome;
	private String token;

	
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public static UtenteLoggatoDTO buildUtenteDTOFromModel(Utente source) {
		UtenteLoggatoDTO result = new UtenteLoggatoDTO();
		result.setCognome(source.getCognome());
		result.setNome(source.getNome());
		result.setToken(source.getSessione().getToken());
		return result;
	}

}
