package it.prova.spootifyRest.service.playlist;

import java.util.List;

import it.prova.spootifyRest.model.PlayList;


public interface PlaylistService {
	
	public List<PlayList> listAllPlayList() ;

	public PlayList caricaSingolaPlayList(Long id);

	public void aggiorna(PlayList playListInstance);

	public void inserisciNuova(PlayList playListInstance);

	public void rimuovi(PlayList playListInstance);
	
	public PlayList findByIdPlaylistEagerBrani(Long id);
	
	public List<PlayList> findAllPlaylistByIdUtente(Long id);


}
