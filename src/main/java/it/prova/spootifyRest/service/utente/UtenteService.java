package it.prova.spootifyRest.service.utente;

import java.util.List;

import it.prova.spootifyRest.model.Utente;

public interface UtenteService {

	public List<Utente> listAllUtenti();

	public Utente caricaSingoloUtente(Long id);

	public void aggiorna(Utente utenteInstance);

	public void inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);
	
	public Utente eseguiAccesso(String username, String password);

	public Utente findByIdEagerRuoli(Long id);
	
	public Utente findByToken(String token);
	
}
