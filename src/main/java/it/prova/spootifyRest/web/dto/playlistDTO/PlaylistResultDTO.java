package it.prova.spootifyRest.web.dto.playlistDTO;

import java.util.ArrayList;
import java.util.List;

import it.prova.spootifyRest.model.PlayList;

public class PlaylistResultDTO {
	
	private String denominazione;

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public PlaylistResultDTO() {
		super();
	}
	
	public static PlaylistResultDTO buildPlaylistDTOFromModel(PlayList source) {
		PlaylistResultDTO result=new PlaylistResultDTO();
		result.setDenominazione(source.getDenominazione());
		
		return result;
	}
	
	public static List<PlaylistResultDTO> buildListFromModelLis(List<PlayList> input){
		List<PlaylistResultDTO> result= new ArrayList<PlaylistResultDTO>();
		for(PlayList playlistItem:input) {
			PlaylistResultDTO playlistTemp= PlaylistResultDTO.buildPlaylistDTOFromModel(playlistItem);
			result.add(playlistTemp);
		}
		return result;
	}
	

}
