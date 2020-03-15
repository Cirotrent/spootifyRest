package it.prova.spootifyRest.service.brano;

import java.util.List;

import it.prova.spootifyRest.model.Brano;


public interface BranoService {
	
	public List<Brano> listAllBrani() ;

	public Brano caricaSingoloBrano(Long id);

	public void aggiorna(Brano branoInstance);

	public void inserisciNuovo(Brano branoInstance);

	public void rimuovi(Brano branoInstance);
	
	public 	Brano findByIdEagerPlaylistAlbum(Long id);

	public  List<Brano> findAllBraniByIdAlbum(Long id);

	public List<Brano> findAllBraniByIdPlaylist(Long id);
}
