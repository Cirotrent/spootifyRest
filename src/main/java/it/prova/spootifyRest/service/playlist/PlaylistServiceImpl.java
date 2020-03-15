package it.prova.spootifyRest.service.playlist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.spootifyRest.model.PlayList;
import it.prova.spootifyRest.reposiory.playlist.PlaylistRepository;
@Component
public class PlaylistServiceImpl implements PlaylistService {

	@Autowired
	PlaylistRepository playlistRepository;
	
	@Transactional(readOnly = true)
	public List<PlayList> listAllPlayList() {
		return (List<PlayList>) playlistRepository.findAll();
	}

	@Transactional(readOnly = true)
	public PlayList caricaSingolaPlayList(Long id) {
		return playlistRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(PlayList playListInstance) {
		playlistRepository.save(playListInstance);
		
	}

	@Transactional
	public void inserisciNuova(PlayList playListInstance) {
		playlistRepository.save(playListInstance);
		
	}

	@Transactional
	public void rimuovi(PlayList playListInstance) {
		playlistRepository.delete(playListInstance);
	}

	@Transactional(readOnly = true)
	public PlayList findByIdEager(Long id) {
		return playlistRepository.findByIdEager(id);
	}

	@Transactional(readOnly = true)
	public List<PlayList> findAllPlaylistByIdUtente(Long id) {
		return playlistRepository.findAllPlaylistByIdUtente(id);
	}

}
