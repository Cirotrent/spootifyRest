package it.prova.spootifyRest.service.brano;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.spootifyRest.model.Brano;
import it.prova.spootifyRest.reposiory.brano.BranoRepository;
@Component
public class BranoServiceImpl implements BranoService{
	
	@Autowired
	private BranoRepository branoRepository;

	@Transactional(readOnly = true)
	public List<Brano> listAllBrani() {
		return (List<Brano>) branoRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Brano caricaSingoloBrano(Long id) {
		return branoRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Brano branoInstance) {
		branoRepository.save(branoInstance);
	}

	@Transactional
	public void inserisciNuovo(Brano branoInstance) {
		branoRepository.save(branoInstance);
		
	}

	@Transactional
	public void rimuovi(Brano branoInstance) {
		branoRepository.delete(branoInstance);
		
	}

	@Transactional(readOnly = true)
	public Brano findByIdEagerPlaylistAlbum(Long id) {
		return branoRepository.findByIdEagerPlaylistAlbum(id);
	}

	@Override
	public List<Brano> findAllBraniByIdAlbum(Long id) {
		return branoRepository.findAllBraniByIdAlbum(id);
	}

	@Override
	public List<Brano> findAllBraniByIdPlaylist(Long id) {
		return branoRepository.findAllBraniByIdAlbum(id);
	}
	
	

}
