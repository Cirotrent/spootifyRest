package it.prova.spootifyRest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titolo;
	private String genere;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "album", orphanRemoval = true)
	private List<Riproduzione> listaRiproduzioni = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "album", orphanRemoval = true)
	private List<Brano> brani = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "artista_id", nullable = false)
	private Artista artista;

	public Album() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public List<Riproduzione> getListaRiproduzioni() {
		return listaRiproduzioni;
	}

	public void setListaRiproduzioni(List<Riproduzione> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}

	public List<Brano> getBrani() {
		return brani;
	}

	public void setBrani(List<Brano> brani) {
		this.brani = brani;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

}
