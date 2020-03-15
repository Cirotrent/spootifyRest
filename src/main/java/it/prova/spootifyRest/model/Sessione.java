package it.prova.spootifyRest.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.time.DateUtils;

@Entity
public class Sessione {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	@OneToOne(mappedBy = "sessione")
	private Utente utente;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataScadenza;

	public Sessione() {
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

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Date getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(Date dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public void nuova(Utente utente) {
		int addMinuteTime = 5;
		Date data = new Date(); 
		data = DateUtils.addHours(data, 1);// now
		data = DateUtils.addMinutes(data, addMinuteTime); // add minute
		this.dataScadenza=data;
		this.setUtente(utente);
		this.setToken(UUID.randomUUID().toString());

	}

	public void aggiorna() {
		Date data = new Date(); 
		data = DateUtils.addHours(data, 1);// now
		data = DateUtils.addMinutes(data, 5); // add minute
		this.dataScadenza=data;
		this.setToken(UUID.randomUUID().toString());
	}
	
	public void aggiornaData() {
		Date data = new Date(); 
		data = DateUtils.addHours(data, 1);// now
		data = DateUtils.addMinutes(data, 5);// add minute
		this.dataScadenza=data;
	}

}
