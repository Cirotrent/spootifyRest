package it.prova.spootifyRest.web.dto.artistaDTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.spootifyRest.model.Album;
import it.prova.spootifyRest.model.Artista;
import it.prova.spootifyRest.web.dto.albumDTO.AlbumDTO;

public class ArtistaDTO {
// test
	private Long id;
	@NotBlank(message = "Campo 'nome': non puo' essere lasciato vuoto!")
	private String nome;
	@NotBlank(message = "Campo 'cognome': non puo' essere lasciato vuoto!")
	private String cognome;
	@NotBlank(message = "Campo 'nazionalita': non puo' essere lasciato vuoto!")
	private String nazionalita;
	@NotBlank(message = "Campo 'genere': non puo' essere lasciato vuoto!")
	private String genere;
	@JsonIgnoreProperties("artista")
	private List<AlbumDTO> listaAlbum = new ArrayList<>();

	public ArtistaDTO() {
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

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public List<AlbumDTO> getListaAlbum() {
		return listaAlbum;
	}

	public void setListaAlbum(List<AlbumDTO> listaAlbum) {
		this.listaAlbum = listaAlbum;
	}

	public static ArtistaDTO buildArtistaDTOFromModel(Artista source, boolean eagerAlbum) {
		ArtistaDTO result = new ArtistaDTO();
		result.setCognome(source.getCognome());
		result.setGenere(source.getGenere());
		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setNazionalita(source.getNazionalita());
		if (eagerAlbum) {
			for (Album albumItem : source.getListaAlbum()) {
				result.getListaAlbum().add(AlbumDTO.buildAlbumDTOFromModel(albumItem, false, false, false));
			}
		}
		return result;
	}

	public static Artista buildArtistaModelFromDTO(ArtistaDTO source, boolean eagerAlbum) {
		Artista result = new Artista();
		result.setCognome(source.getCognome());
		result.setGenere(source.getGenere());
		result.setId(source.getId());
		result.setNome(source.getNome());
		result.setNazionalita(source.getNazionalita());
		if (eagerAlbum) {
			for (AlbumDTO albumItem : source.getListaAlbum()) {
				result.getListaAlbum().add(AlbumDTO.buildAlbumModelFromDTO(albumItem, false, false, false));
			}
		}
		return result;
	}

}
