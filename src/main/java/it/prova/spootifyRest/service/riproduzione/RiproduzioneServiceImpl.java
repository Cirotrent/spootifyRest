package it.prova.spootifyRest.service.riproduzione;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.spootifyRest.model.Album;
import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.model.PlayList;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.model.Sessione;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.reposiory.riproduzione.RiproduzioneRepository;
import it.prova.spootifyRest.service.album.AlbumService;
import it.prova.spootifyRest.service.brano.BranoService;
import it.prova.spootifyRest.service.playlist.PlaylistService;
import it.prova.spootifyRest.service.sessione.SessioneService;
import it.prova.spootifyRest.service.utente.UtenteService;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RicercaDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

@Component
public class RiproduzioneServiceImpl implements RiproduzioneService {

	@Autowired
	RiproduzioneRepository riproduzioneRepository;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private BranoService branoService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private PlaylistService playlistService;

	@Autowired
	private SessioneService sessioneService;

	@Transactional(readOnly = true)
	public List<Riproduzione> listAllRiproduzioni() {
		return (List<Riproduzione>) riproduzioneRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Riproduzione caricaSingolaRiproduzione(Long id) {
		return riproduzioneRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Riproduzione riproduzioneInstance) {
		riproduzioneRepository.save(riproduzioneInstance);

	}

	@Transactional
	public void inserisciNuova(Riproduzione riproduzioneInstance) {
		riproduzioneRepository.save(riproduzioneInstance);

	}

	@Transactional
	public void rimuovi(Riproduzione riproduzioneInstance) {
		riproduzioneRepository.delete(riproduzioneInstance);

	}

	@Transactional
	public Riproduzione findByIdAlbumEUtente(Long idAlbum, Long idUtente) {
		return riproduzioneRepository.findByIdAlbumEUtente(idAlbum, idUtente);
	}

	@Transactional
	public Riproduzione findByIdPlaylisteEUtente(Long idPlaylist, Long idUtente) {
		return riproduzioneRepository.findByIdPlaylisteEUtente(idPlaylist, idUtente);
	}

	public Riproduzione findOrCreateRiproduzioneByRicDTO(RicercaDTO input) {
		Utente utenteInSessione = utenteService.findByToken(input.getToken());
		Riproduzione result = new Riproduzione();
		if (input.getIdAlbum() != null && input.getIdPlaylist() == null) {
			result = findByIdAlbumEUtente(input.getIdAlbum(), utenteInSessione.getId());
			if (result == null) {
				Riproduzione riproduzioneNuova = new Riproduzione();
				Album album = albumService.findByIdEagerArtistaBrani(input.getIdAlbum());
				riproduzioneNuova.setAlbum(album);
				riproduzioneNuova.setBrano(album.getBrani().get(0));
				riproduzioneNuova.setUtente(utenteInSessione);
				inserisciNuova(riproduzioneNuova);
				return riproduzioneNuova;
			}
			return result;
		}
		if (input.getIdAlbum() == null && input.getIdPlaylist() != null) {
			result = findByIdPlaylisteEUtente(input.getIdPlaylist(), utenteInSessione.getId());
			if (result == null) {
				Riproduzione riproduzioneNuova = new Riproduzione();
				PlayList playlist = playlistService.findByIdPlaylistEagerBrani(input.getIdPlaylist());
				riproduzioneNuova.setPlayList(playlist);
				riproduzioneNuova.setBrano(playlist.getBrani().get(0));
				riproduzioneNuova.setUtente(utenteInSessione);
				inserisciNuova(riproduzioneNuova);
				return riproduzioneNuova;
			}
			return result;
		}

		return result;

	}

	public Message controllo(RicercaDTO input) {
		Utente utenteInSessione = utenteService.findByToken(input.getToken());
		if ((input.getIdAlbum() != null && input.getIdPlaylist() != null)
				|| (input.getIdAlbum() == null && input.getIdPlaylist() == null)) {
			return new Message("Inserisci l'album o la playlist!");
		}
		if (input.getIdAlbum() != null) {
			if (!albumService.listAllAlbum().contains(albumService.caricaSingoloAlbum(input.getIdAlbum()))) {
				return new Message("L'album scelto non esiste");
			}
		} else if (!playlistService.findAllPlaylistByIdUtente(utenteInSessione.getId())
				.contains(playlistService.caricaSingolaPlayList(input.getIdPlaylist()))) {
			return new Message("La playlist scelta non t appartiene o non esiste!");
		}

		return null;
	}

	public Riproduzione findRiprByRicDTO(RicercaDTO input) {
		Utente utenteInSessione = utenteService.findByToken(input.getToken());
		if (input.getIdAlbum() != null && input.getIdPlaylist() == null) {
			return findByIdAlbumEUtente(input.getIdAlbum(), utenteInSessione.getId());
		}
		if (input.getIdAlbum() == null && input.getIdPlaylist() != null) {
			return findByIdPlaylisteEUtente(input.getIdPlaylist(), utenteInSessione.getId());
		}

		return null;
	}

	public Riproduzione nextRiproduzione(RicercaDTO input) {
		Riproduzione riproduzione = findRiprByRicDTO(input);
		List<Brano> brani = new ArrayList<Brano>();
		if (input.getIdAlbum() != null) {
			brani = riproduzione.getAlbum().getBrani();
		}else brani = riproduzione.getPlayList().getBrani();
		
			for (int i = 0; i < brani.size(); i++) {

				if (brani.get(i).equals(riproduzione.getBrano())) {
					if (i == brani.size() - 1) {
						riproduzione.setBrano(brani.get(0));
						return riproduzione;
					}
					riproduzione.setBrano(brani.get(i + 1));
					return riproduzione;
				}
			}

		return riproduzione;
	}
	
	public Riproduzione previousRiproduzione(RicercaDTO input) {
		Riproduzione riproduzione = findRiprByRicDTO(input);
		List<Brano> brani = new ArrayList<Brano>();
		if (input.getIdAlbum() != null) {
			brani = riproduzione.getAlbum().getBrani();
		}else brani = riproduzione.getPlayList().getBrani();
		
			for (int i = 0; i < brani.size(); i++) {

				if (brani.get(i).equals(riproduzione.getBrano())) {
					if (i == 0) {
						riproduzione.setBrano(brani.get(brani.size()-1));
						return riproduzione;
					}
					riproduzione.setBrano(brani.get(i-1));
					return riproduzione;
				}
			}

		return riproduzione;
	}

}
