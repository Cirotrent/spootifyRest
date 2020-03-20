package it.prova.spootifyRest.service.riproduzione;

import java.util.List;

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RicercaDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

public interface RiproduzioneService {

	public List<Riproduzione> listAllRiproduzioni();

	public Riproduzione caricaSingolaRiproduzione(Long id);

	public void aggiorna(Riproduzione riproduzioneInstance);

	public void inserisciNuova(Riproduzione riproduzioneInstance);

	public void rimuovi(Riproduzione riproduzioneInstance);

	public Riproduzione findRiproduzioneByRicDTO(RicercaDTO input);

	public Message controllo(RicercaDTO input);

	public Riproduzione findByIdAlbumEUtente(Long idAlbum, Long idUtente);
	
	public Riproduzione findByIdPlaylisteEUtente(Long idPlaylist, Long idUtente);
	
	public Riproduzione findRiproduzioneByRic(RicercaDTO input);
	
	public Riproduzione nextRiproduzione(RicercaDTO input); 
	
	public Riproduzione previousRiproduzione(RicercaDTO input);

}
