package it.prova.spootifyRest.service.sessione;

import java.util.List;

import it.prova.spootifyRest.model.Sessione;

public interface SessioneService {

	public List<Sessione> listAllSessioni();

	public Sessione caricaSingolaSessione(Long id);

	public void aggiorna(Sessione sessioneInstance);

	public void inserisciNuovo(Sessione sessioneInstance);

	public void rimuovi(Sessione sessioneInstance);
	
	public Sessione findByIdUtente(Long id);
}
