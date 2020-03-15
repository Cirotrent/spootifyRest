package it.prova.spootifyRest.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class PlayList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String denominazione;
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	@JoinTable(name = "playList_brano", joinColumns = @JoinColumn(name = "playList_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "brano_id", referencedColumnName = "id"))
	private List<Brano> brani = new ArrayList<>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "playList", orphanRemoval = true)
	private List<Riproduzione> listaRiproduzioni = new ArrayList<>(0);
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id", nullable = false)
	private Utente utente;

	public PlayList() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public List<Brano> getBrani() {
		return brani;
	}

	public void setBrani(List<Brano> brani) {
		this.brani = brani;
	}

	public List<Riproduzione> getListaRiproduzioni() {
		return listaRiproduzioni;
	}

	public void setListaRiproduzioni(List<Riproduzione> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
