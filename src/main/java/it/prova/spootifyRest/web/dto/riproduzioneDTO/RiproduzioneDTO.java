package it.prova.spootifyRest.web.dto.riproduzioneDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.web.dto.albumDTO.AlbumDTO;
import it.prova.spootifyRest.web.dto.branoDTO.BranoDTO;
import it.prova.spootifyRest.web.dto.playlistDTO.PlaylistDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteDTO;

public class RiproduzioneDTO {

	private Long id;
	@JsonIgnoreProperties("listaRiproduzioni")
	private PlaylistDTO playList;
	@JsonIgnoreProperties("listaRiproduzioni")
	private UtenteDTO utente;
	@JsonIgnoreProperties("listaRiproduzioni")
	private BranoDTO brano;
	@JsonIgnoreProperties("listaRiproduzioni")
	private AlbumDTO album;

	public RiproduzioneDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlaylistDTO getPlayList() {
		return playList;
	}

	public void setPlayList(PlaylistDTO playList) {
		this.playList = playList;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public BranoDTO getBrano() {
		return brano;
	}

	public void setBrano(BranoDTO brano) {
		this.brano = brano;
	}

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	public static RiproduzioneDTO buildRiproduzioneDTOFromModel(Riproduzione source, boolean eagerPlailist,
			boolean eagerBrano, boolean eagerAlbum, boolean eagerUtente) {
		RiproduzioneDTO result = new RiproduzioneDTO();
		result.setId(source.getId());
		if (eagerAlbum) {
			result.setAlbum(AlbumDTO.buildAlbumDTOFromModel(source.getAlbum(), false, false, true));
		}

		if (eagerUtente) {
			result.setUtente(UtenteDTO.buildUtenteDTOFromModel(source.getUtente(), false, false, false, false));
		}

		if (eagerPlailist) {
			result.setPlayList(PlaylistDTO.buildPlaylistDTOFromModel(source.getPlayList(), false, false, true));
		}

		if (eagerBrano) {
			result.setBrano(BranoDTO.buildBranoDTOFromModel(source.getBrano(), false, false, false));
		}
		return result;
	}

	public static Riproduzione buildRiproduzioneModelFromDTO(RiproduzioneDTO source, boolean eagerPlailist,
			boolean eagerBrano, boolean eagerAlbum, boolean eagerUtente) {
		Riproduzione result = new Riproduzione();
		result.setId(source.getId());
		if (eagerAlbum) {
			result.setAlbum(AlbumDTO.buildAlbumModelFromDTO(source.getAlbum(), false, false, false));
		}

		if (eagerUtente) {
			result.setUtente(UtenteDTO.buildUtenteModelFromDTO(source.getUtente(), false, false, false, false));
		}

		if (eagerPlailist) {
			result.setPlayList(PlaylistDTO.buildPlaylistModelFromDTO(source.getPlayList(), false, false, false));
		}

		if (eagerBrano) {
			result.setBrano(BranoDTO.buildBranoModelFromDTO(source.getBrano(), false, false, false));
		}
		return result;
	}
	
	/**
	 * scelta: TRUE>> eagerPlaylist  FALSE>> eagerAlbum
	 */
	public static RiproduzioneDTO creaDTOconAlbumOrPlaylist(Riproduzione input, boolean scelta) {
		RiproduzioneDTO riproduzioneDTO= new RiproduzioneDTO();
		if (scelta) {
			riproduzioneDTO = RiproduzioneDTO.buildRiproduzioneDTOFromModel(input, true, true, false, false);//playlist
		} else {
			riproduzioneDTO = RiproduzioneDTO.buildRiproduzioneDTOFromModel(input, false, true, true, false);//album
		}
		return riproduzioneDTO;
	}

}
