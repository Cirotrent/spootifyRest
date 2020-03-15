package it.prova.spootifyRest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;
	@Enumerated(EnumType.STRING)
	private StatoUtente stato = StatoUtente.CREATO;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private List<Ruolo> ruoli = new ArrayList<>(0);
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sessione_id", referencedColumnName = "id")
	private Sessione sessione;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utente", orphanRemoval = true)
	private List<PlayList> listaPlayList = new ArrayList<>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utente", orphanRemoval = true)
	private List<Riproduzione> listaRiproduzioni = new ArrayList<>(0);

	public Utente() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Sessione getSessione() {
		return sessione;
	}

	public void setSessione(Sessione sessione) {
		this.sessione = sessione;
	}

	public List<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public List<PlayList> getListaPlayList() {
		return listaPlayList;
	}

	public void setListaPlayList(List<PlayList> listaPlayList) {
		this.listaPlayList = listaPlayList;
	}

	public List<Riproduzione> getListaRiproduzioni() {
		return listaRiproduzioni;
	}

	public void setListaRiproduzioni(List<Riproduzione> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}

	public boolean isAdmin() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.ADMIN_ROLE))
				return true;
		}
		return false;
	}

	public boolean isCliente() {
		for (Ruolo ruoloItem : ruoli) {
			if (ruoloItem.getCodice().equals(Ruolo.CUSTOMER_ROLE))
				return true;
		}
		return false;
	}

}
