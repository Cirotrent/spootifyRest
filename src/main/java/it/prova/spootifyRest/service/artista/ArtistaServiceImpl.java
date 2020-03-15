package it.prova.spootifyRest.service.artista;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.spootifyRest.model.Artista;
import it.prova.spootifyRest.reposiory.artista.ArtistaRepository;

@Component
public class ArtistaServiceImpl implements ArtistaService {

	@Autowired
	private ArtistaRepository artistaRepository;

	@Transactional(readOnly = true)
	public List<Artista> listAllArtisti() {
		return (List<Artista>) artistaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Artista caricaSingoloArtista(Long id) {
		return artistaRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Artista artistaInstance) {
		artistaRepository.save(artistaInstance);
	}

	@Transactional
	public void inserisciNuovo(Artista artistaInstance) {
		artistaRepository.save(artistaInstance);

	}

	@Transactional
	public void rimuovi(Artista artistaInstance) {
		artistaRepository.delete(artistaInstance);

	}

	@Transactional(readOnly = true)
	public Artista findByIdEagerAlbum(Long id) {
		return artistaRepository.findByIdEagerAlbum(id);
	}

}
