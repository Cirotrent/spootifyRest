package it.prova.spootifyRest.web.dto.playlistDTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.model.PlayList;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.web.dto.branoDTO.BranoDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RiproduzioneDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.UtenteDTO;

public class PlaylistDTO {

	private Long id;
	@NotBlank(message = "Campo 'denominazione': non puo' essere lasciato vuoto!")
	private String denominazione;
	@JsonIgnoreProperties("listaPlaylist")
	private List<BranoDTO> brani = new ArrayList<>();
	@JsonIgnoreProperties("playlist")
	private List<RiproduzioneDTO> listaRiproduzioni = new ArrayList<>();
	@JsonIgnoreProperties("listaPlaylist")
	private UtenteDTO utente;

	public PlaylistDTO() {
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

	public List<BranoDTO> getBrani() {
		return brani;
	}

	public void setBrani(List<BranoDTO> brani) {
		this.brani = brani;
	}

	public List<RiproduzioneDTO> getListaRiproduzioni() {
		return listaRiproduzioni;
	}

	public void setListaRiproduzioni(List<RiproduzioneDTO> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}

	public UtenteDTO getUtente() {
		return utente;
	}

	public void setUtente(UtenteDTO utente) {
		this.utente = utente;
	}

	public static PlaylistDTO buildPlaylistDTOFromModel(PlayList source, boolean eagerUtente, boolean eagerRiproduzioni,
			boolean eagerBrani) {
		PlaylistDTO result = new PlaylistDTO();
		result.setDenominazione(source.getDenominazione());
		result.setId(source.getId());
		if (eagerUtente) {
			result.setUtente(UtenteDTO.buildUtenteDTOFromModel(source.getUtente(), false, false, false, false));
		}
		if (eagerRiproduzioni) {
			for (Riproduzione riproduzioneItem : source.getListaRiproduzioni()) {
				result.getListaRiproduzioni().add(RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzioneItem,false,false,false,false));
			}
		}
		if (eagerBrani) {
			for (Brano branoItem : source.getBrani()) {
				result.getBrani().add(BranoDTO.buildBranoDTOFromModel(branoItem, false,false,false));
			}
		}
		return result;

	}

	public static PlayList buildPlaylistModelFromDTO(PlaylistDTO source, boolean eagerUtente, boolean eagerRiproduzioni,
			boolean eagerBrani) {
		PlayList result = new PlayList();
		result.setDenominazione(source.getDenominazione());
		result.setId(source.getId());
		if (eagerUtente) {
			result.setUtente(UtenteDTO.buildUtenteModelFromDTO(source.getUtente(), false, false, false, false));
		}
		if (eagerRiproduzioni) {
			for (RiproduzioneDTO riproduzioneItem : source.getListaRiproduzioni()) {
				result.getListaRiproduzioni().add(RiproduzioneDTO.buildRiproduzioneModelFromDTO(riproduzioneItem, false,false,false,false));
			}
		}
		if (eagerBrani) {
			for (BranoDTO branoItem : source.getBrani()) {
				result.getBrani().add(BranoDTO.buildBranoModelFromDTO(branoItem,false,false,false));
			}
		}
		
		return result;

	}
	
	

}
