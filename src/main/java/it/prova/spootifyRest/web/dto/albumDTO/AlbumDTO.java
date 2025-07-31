package it.prova.spootifyRest.web.dto.albumDTO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.spootifyRest.model.Album;
import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.web.dto.artistaDTO.ArtistaDTO;
import it.prova.spootifyRest.web.dto.branoDTO.BranoDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RiproduzioneDTO;

public class AlbumDTO {
//test test test ciao
	private Long id;
	private String titolo;
	private String genere;
	@JsonIgnoreProperties("album")
	private List<RiproduzioneDTO> listaRiproduzioni = new ArrayList<>();
	@JsonIgnoreProperties("album")
	private List<BranoDTO> brani = new ArrayList<>();
	@JsonIgnoreProperties("listaAlbum")
	private ArtistaDTO artista;

	public AlbumDTO() {
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

	public List<RiproduzioneDTO> getListaRiproduzioni() {
		return listaRiproduzioni;
	}

	public void setListaRiproduzioni(List<RiproduzioneDTO> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}

	public List<BranoDTO> getBrani() {
		return brani;
	}

	public void setBrani(List<BranoDTO> brani) {
		this.brani = brani;
	}

	public ArtistaDTO getArtista() {
		return artista;
	}

	public void setArtista(ArtistaDTO artista) {
		this.artista = artista;
	}

	public static AlbumDTO buildAlbumDTOFromModel(Album source, boolean eagerArtista, boolean eagerRiproduzioni,
			boolean eagerBrani) {
		AlbumDTO result = new AlbumDTO();
		result.setId(source.getId());
		result.setGenere(source.getGenere());
		result.setTitolo(source.getTitolo());
		if (eagerArtista) {
			result.setArtista(ArtistaDTO.buildArtistaDTOFromModel(source.getArtista(), false));
		}
		if (eagerBrani) {
			for (Brano branoItem : source.getBrani()) {
				result.getBrani().add(BranoDTO.buildBranoDTOFromModel(branoItem, false, false, false));
			}
		}
		if (eagerRiproduzioni) {
			for (Riproduzione riproduzioneItem : source.getListaRiproduzioni()) {
				result.getListaRiproduzioni().add(
						RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzioneItem, false, false, false, false));
			}
		}

		return result;
	}

	public static Album buildAlbumModelFromDTO(AlbumDTO source, boolean eagerArtista, boolean eagerRiproduzioni,
			boolean eagerBrani) {
		Album result = new Album();
		result.setId(source.getId());
		result.setGenere(source.getGenere());
		result.setTitolo(source.getTitolo());
		if (eagerArtista) {
			result.setArtista(ArtistaDTO.buildArtistaModelFromDTO(source.getArtista(), false));
		}
		if (eagerBrani) {
			for (BranoDTO branoItem : source.getBrani()) {
				result.getBrani().add(BranoDTO.buildBranoModelFromDTO(branoItem, false, false, false));
			}
		}
		if (eagerRiproduzioni) {
			for (RiproduzioneDTO riproduzioneItem : source.getListaRiproduzioni()) {
				result.getListaRiproduzioni().add(
						RiproduzioneDTO.buildRiproduzioneModelFromDTO(riproduzioneItem, false, false, false, false));
			}
		}

		return result;
	}

}
