package it.prova.spootifyRest.service.album;

import java.util.List;

import it.prova.spootifyRest.model.Album;


public interface AlbumService {
	
	public List<Album> listAllAlbum();

	public Album caricaSingoloAlbum(Long id);

	public void aggiorna(Album AlbumInstance);

	public void inserisciNuovo(Album AlbumInstance);

	public void rimuovi(Album AlbumInstance);
	
	public 	Album findByIdEagerArtistaBrani(Long id);


}
