package it.prova.spootifyRest.web.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.spootifyRest.model.Album;
import it.prova.spootifyRest.service.album.AlbumService;
import it.prova.spootifyRest.web.dto.albumDTO.AlbumDTO;
import it.prova.spootifyRest.web.dto.utenteDTO.Message;

@RestController
@RequestMapping("/album")
public class AlbumController {
	
	@Autowired
	AlbumService albumService;
	
	@GetMapping("/dettaglio/{id}")
	public ResponseEntity<AlbumDTO> findById(@PathVariable Long id){
		Album album=albumService.findByIdEagerArtistaBrani(id);
		
		AlbumDTO albumDTO= AlbumDTO.buildAlbumDTOFromModel(album, true, true, true);
		return ResponseEntity.status(200).body(albumDTO);
	}
	
	@PostMapping("/inserisci")
	public ResponseEntity<Message> insert(@RequestBody AlbumDTO albumDTO){
		Album album = AlbumDTO.buildAlbumModelFromDTO(albumDTO, true, true, true);
		albumService.inserisciNuovo(album);
		
		return ResponseEntity.status(200).body(new Message("Inserimento avvenuto con successo"));
	}
	@PutMapping("/modifica/{id}")
	public ResponseEntity<Message> modifica(@RequestBody AlbumDTO albumDTO, @PathVariable Long id){
		Album album = AlbumDTO.buildAlbumModelFromDTO(albumDTO, false, false, true);
		album.setId(id);
		albumService.aggiorna(album);
		return ResponseEntity.status(200).body(new Message("Modifica avvenuta con successo"));
	}
	
	@DeleteMapping("/elimina/{id}")
	public ResponseEntity<Message> elimina(@PathVariable Long id){
		Album album= albumService.caricaSingoloAlbum(id);
		albumService.rimuovi(album);
		return ResponseEntity.status(200).body(new Message("Eliminazione avvenuta con successo"));
	}
	
	
}
