package it.prova.spootifyRest.service.ruolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.spootifyRest.model.Ruolo;
import it.prova.spootifyRest.reposiory.ruolo.RuoloRepository;
@Component
public class RuoloServiceImpl implements RuoloService {
	
	@Autowired
	RuoloRepository ruoloRepository;
	
	
	@Override
	public List<Ruolo> listAllRuoli() {
		return (List<Ruolo>) ruoloRepository.findAll();
	}

	@Override
	public Ruolo caricaSingoloRuolo(Long id) {
		return ruoloRepository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
		
	}

	@Override
	public void inserisciNuovo(Ruolo ruoloInstance) {
		ruoloRepository.save(ruoloInstance);
		
	}

	@Override
	public void rimuovi(Ruolo ruoloInstance) {
		ruoloRepository.delete(ruoloInstance);
		
	}

}
