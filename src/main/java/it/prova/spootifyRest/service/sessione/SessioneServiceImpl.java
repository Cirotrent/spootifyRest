package it.prova.spootifyRest.service.sessione;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.spootifyRest.model.Sessione;
import it.prova.spootifyRest.reposiory.sessione.SessioneRepository;

@Component
public class SessioneServiceImpl implements SessioneService {

	@Autowired
	SessioneRepository sessioneRepository;

	@Transactional(readOnly = true)
	public List<Sessione> listAllSessioni() {
		return (List<Sessione>) sessioneRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Sessione caricaSingolaSessione(Long id) {
		return sessioneRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Sessione sessioneInstance) {
		sessioneRepository.save(sessioneInstance);

	}

	@Transactional
	public void inserisciNuovo(Sessione sessioneInstance) {
		sessioneRepository.save(sessioneInstance);
	}

	@Transactional
	public void rimuovi(Sessione sessioneInstance) {
		sessioneRepository.delete(sessioneInstance);

	}

	@Transactional(readOnly = true)
	public Sessione findByIdUtente(Long id) {
		return sessioneRepository.findByIdUtente(id);
	}

}
