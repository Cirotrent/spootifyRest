package it.prova.spootifyRest.service.utente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import it.prova.spootifyRest.model.StatoUtente;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.reposiory.utente.UtenteRepository;

@Component
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	UtenteRepository utenteRepository;

	@Transactional(readOnly = true)
	public List<Utente> listAllUtenti() {
		return (List<Utente>) utenteRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Utente caricaSingoloUtente(Long id) {
		return utenteRepository.findById(id).orElse(null);
	}

	@Transactional
	public void aggiorna(Utente utenteInstance) {
		utenteRepository.save(utenteInstance);

	}

	@Transactional
	public void inserisciNuovo(Utente utenteInstance) {
		utenteRepository.save(utenteInstance);

	}

	@Transactional
	public void rimuovi(Utente utenteInstance) {
		utenteRepository.delete(utenteInstance);

	}

	@Transactional(readOnly = true)
	public Utente eseguiAccesso(String username, String password) {
		return utenteRepository.findByUsernameAndPasswordAndStato(username, password,StatoUtente.ATTIVO);

	}

	@Transactional(readOnly = true)
	public Utente findByIdEagerRuoli(Long id) {
		return utenteRepository.findByIdEagerRuoli(id);
	}

	@Override
	public Utente findByToken(String token) {
		return utenteRepository.findByToken(token);
	}

}
