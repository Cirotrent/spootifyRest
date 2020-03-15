package it.prova.spootifyRest.service.album;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.spootifyRest.model.Album;
import it.prova.spootifyRest.reposiory.album.AlbumRepository;

@Component
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumRepository albumRepository;

	@Transactional(readOnly = true)
	public List<Album> listAllAlbum() {
		return (List<Album>) albumRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Album caricaSingoloAlbum(Long id) {
		return albumRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Album albumInstance) {
		albumRepository.save(albumInstance);

	}

	@Transactional
	public void inserisciNuovo(Album albumInstance) {
		albumRepository.save(albumInstance);

	}

	@Transactional
	public void rimuovi(Album albumInstance) {
		albumRepository.delete(albumInstance);

	}

	@Transactional(readOnly = true)
	public Album findByIdEagerArtistaBrani(Long id) {
		return albumRepository.findByIdEagerArtistaBrani(id);
	}

}
