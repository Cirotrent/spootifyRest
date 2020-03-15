package it.prova.spootifyRest.service.artista;

import java.util.List;

import it.prova.spootifyRest.model.Artista;


public interface ArtistaService {
	
	public List<Artista> listAllArtisti() ;

	public Artista caricaSingoloArtista(Long id);

	public void aggiorna(Artista artistaInstance);

	public void inserisciNuovo(Artista artistaInstance);

	public void rimuovi(Artista artistaInstance);
	
	public 	Artista findByIdEagerAlbum(Long id);


}
