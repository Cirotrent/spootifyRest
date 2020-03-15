package it.prova.spootifyRest.service.riproduzione;

import java.util.List;

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RicercaDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RiproduzioneMessage;


public interface RiproduzioneService {
	
	public List<Riproduzione> listAllRiproduzioni() ;

	public Riproduzione caricaSingolaRiproduzione(Long id);

	public void aggiorna(Riproduzione riproduzioneInstance);

	public void inserisciNuova(Riproduzione riproduzioneInstance);

	public void rimuovi(Riproduzione riproduzioneInstance);
	
	public List<Brano> findBrani(RicercaDTO input);
	
	public RiproduzioneMessage controllo(RicercaDTO input);

}
