package it.prova.spootifyRest.web.dto.sessioneDTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.spootifyRest.model.Sessione;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteDTO;

public class SessioneDTO {

	private Long id;
	private String token;
	@JsonIgnoreProperties("sessione")
	private UtenteDTO utente;
	private Date dataScadenza;

	public SessioneDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public static SessioneDTO buildSessioneDTOFromModel(Sessione source, boolean eagerUtente) {
		SessioneDTO result = new SessioneDTO();
		result.setDataScadenza(source.getDataScadenza());
		result.setId(source.getId());
		result.setToken(source.getToken());
		if (eagerUtente) {
			result.setUtente(UtenteDTO.buildUtenteDTOFromModel(source.getUtente(), false, false, false, false));
		}

		return result;
	}

	public static Sessione buildSessioneModelFromDTO(SessioneDTO source, boolean eagerUtente) {
		Sessione result = new Sessione();
		result.setDataScadenza(source.getDataScadenza());
		result.setId(source.getId());
		result.setToken(source.getToken());
		if (eagerUtente) {
			result.setUtente(UtenteDTO.buildUtenteModelFromDTO(source.getUtente(), false, false, false, false));
		}
		return result;
	}

}
