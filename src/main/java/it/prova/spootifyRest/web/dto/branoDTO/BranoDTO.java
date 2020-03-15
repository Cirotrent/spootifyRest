package it.prova.spootifyRest.web.dto.branoDTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.model.PlayList;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.web.dto.albumDTO.AlbumDTO;
import it.prova.spootifyRest.web.dto.playlistDTO.PlaylistDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RiproduzioneDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteDTO;

public class BranoDTO {

	private Long id;
	@NotBlank(message = "Campo 'titolo': non puo' essere lasciato vuoto!")
	private String titolo;
	@NotBlank(message = "Campo 'genere': non puo' essere lasciato vuoto!")
	private String genere;
	@NotNull(message = "Sceli l'album in cui va inserito!")
	@JsonIgnoreProperties("brani")
	private AlbumDTO album;
	@JsonIgnoreProperties("brano")
	private List<RiproduzioneDTO> listaRiproduzioni = new ArrayList<>();
	@JsonIgnoreProperties("brani")
	private List<PlaylistDTO> listaPlaylist = new ArrayList<>();

	public BranoDTO() {
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

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public List<RiproduzioneDTO> getListaRiproduzioni() {
		return listaRiproduzioni;
	}

	public void setListaRiproduzioni(List<RiproduzioneDTO> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}

	public List<PlaylistDTO> getListaPlaylist() {
		return listaPlaylist;
	}

	public void setListaPlaylist(List<PlaylistDTO> listaPlaylist) {
		this.listaPlaylist = listaPlaylist;
	}

	public static BranoDTO buildBranoDTOFromModel(Brano source, boolean eagerRiproduzioni, boolean eagerAlbum,
			boolean eagerPlaylist) {
		BranoDTO result = new BranoDTO();
		result.setId(source.getId());
		result.setGenere(source.getGenere());
		result.setTitolo(source.getTitolo());
		if (eagerAlbum) {
			result.setAlbum(AlbumDTO.buildAlbumDTOFromModel(source.getAlbum(), false, false, false));
		}
		if (eagerPlaylist) {
			for (PlayList playlistItem : source.getListaPlaylist()) {
				result.getListaPlaylist().add(PlaylistDTO.buildPlaylistDTOFromModel(playlistItem, false, false, false));
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

	public static Brano buildBranoModelFromDTO(BranoDTO source, boolean eagerRiproduzioni, boolean eagerAlbum,
			boolean eagerPlaylist) {
		Brano result = new Brano();
		result.setId(source.getId());
		result.setGenere(source.getGenere());
		result.setTitolo(source.getTitolo());
		if (eagerAlbum) {
			result.setAlbum(AlbumDTO.buildAlbumModelFromDTO(source.getAlbum(), false, false, false));
		}
		if (eagerPlaylist) {
			for (PlaylistDTO playlistItem : source.getListaPlaylist()) {
				result.getListaPlaylist().add(PlaylistDTO.buildPlaylistModelFromDTO(playlistItem, false, false, false));
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
	
public static List<BranoDTO> buildListFromModelList(List<Brano> input){
		
		List<BranoDTO> result= new ArrayList<BranoDTO>();
		for(Brano branoItem: input) {
			BranoDTO branoDTOtemp= BranoDTO.buildBranoDTOFromModel(branoItem, false, false, false);
			result.add(branoDTOtemp);
		}
		return result;
	}

}
