package it.prova.spootifyRest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Brano {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titolo;
	private String genere;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id", nullable = false)
	private Album album;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "brano", orphanRemoval = true)
	private List<Riproduzione> listaRiproduzioni = new ArrayList<>(0);
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "brani")
	private List<PlayList> listaPlaylist = new ArrayList<>(0);

	public Brano() {
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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<Riproduzione> getListaRiproduzioni() {
		return listaRiproduzioni;
	}

	public void setListaRiproduzioni(List<Riproduzione> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}

	public List<PlayList> getListaPlaylist() {
		return listaPlaylist;
	}

	public void setListaPlaylist(List<PlayList> listaPlaylist) {
		this.listaPlaylist = listaPlaylist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brano other = (Brano) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
