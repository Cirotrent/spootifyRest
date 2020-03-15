package it.prova.spootifyRest.service.riproduzione;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.reposiory.riproduzione.RiproduzioneRepository;
import it.prova.spootifyRest.service.album.AlbumService;
import it.prova.spootifyRest.service.brano.BranoService;
import it.prova.spootifyRest.service.playlist.PlaylistService;
import it.prova.spootifyRest.service.utente.UtenteService;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RicercaDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RiproduzioneMessage;

@Component
public class RiproduzioneServiceImpl implements RiproduzioneService {

	@Autowired
	RiproduzioneRepository riproduzioneRepository;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private BranoService branoService;

	@Autowired
	private PlaylistService playlistService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Override
	public List<Riproduzione> listAllRiproduzioni() {
		return (List<Riproduzione>) riproduzioneRepository.findAll();
	}

	@Override
	public Riproduzione caricaSingolaRiproduzione(Long id) {
		return riproduzioneRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Riproduzione riproduzioneInstance) {
		riproduzioneRepository.save(riproduzioneInstance);

	}

	@Override
	public void inserisciNuova(Riproduzione riproduzioneInstance) {
		riproduzioneRepository.save(riproduzioneInstance);

	}

	@Override
	public void rimuovi(Riproduzione riproduzioneInstance) {
		riproduzioneRepository.delete(riproduzioneInstance);

	}

	public List<Brano> findBrani(RicercaDTO input) {
		List<Brano> result = new ArrayList<Brano>();
		if (input.getIdAlbum() != null && input.getIdPlaylist() == null) {
			result = branoService.findAllBraniByIdAlbum(input.getIdAlbum());
			return result;
		}
		if (input.getIdAlbum() == null && input.getIdPlaylist() != null) {
			result = branoService.findAllBraniByIdPlaylist(input.getIdPlaylist());
			return result;
		}

		return result;

	}

	public RiproduzioneMessage controllo(RicercaDTO input) {
		Utente utenteInSessione = utenteService.findByToken(input.getToken());
		if (utenteInSessione == null) {

			return new RiproduzioneMessage("Il Token inserito non è valido!");
		}
		Date dataDiScadenza = DateUtils.addHours(utenteInSessione.getSessione().getDataScadenza(), -1);
		// Controllo se la sessione è scaduta
		if (dataDiScadenza.before(new Date())) {

			return new RiproduzioneMessage("Sessione scaduta!");
		}
		if ((input.getIdAlbum() != null && input.getIdPlaylist() != null)
				|| (input.getIdAlbum() == null && input.getIdPlaylist() == null)) {
			return new RiproduzioneMessage("Inserisci l'album o la playlist. Non puoi inserire entrambi!");
		}
			return null;
	}

}
