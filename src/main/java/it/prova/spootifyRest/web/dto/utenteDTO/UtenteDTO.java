package it.prova.spootifyRest.web.dto.utenteDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import it.prova.spootifyRest.model.PlayList;
import it.prova.spootifyRest.model.Riproduzione;
import it.prova.spootifyRest.model.Ruolo;
import it.prova.spootifyRest.model.StatoUtente;
import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.web.dto.playlistDTO.PlaylistDTO;
import it.prova.spootifyRest.web.dto.riproduzioneDTO.RiproduzioneDTO;
import it.prova.spootifyRest.web.dto.ruoloDTO.RuoloDTO;
import it.prova.spootifyRest.web.dto.sessioneDTO.SessioneDTO;

public class UtenteDTO {
	
	private Long id;
	@NotBlank(message = "Campo 'nome': non puo' essere lasciato vuoto!")
	private String nome;
	@NotBlank(message = "Campo 'cognome': non puo' essere lasciato vuoto!")
	private String cognome;
	@NotBlank(message = "Campo 'username': non puo' essere lasciato vuoto!")
	private String username;
	@NotBlank(message = "Campo 'password': non puo' essere lasciato vuoto!")
	private String password;
	private Date dataRegistrazione;
	//@NotBlank(message = "Campo 'stato': non puo' essere lasciato vuoto!")
	private StatoUtente stato;
	@NotEmpty(message = "Seleziona almeno un ruolo dell'utente")
	@JsonIgnoreProperties(value= {"utente"})
	private List<RuoloDTO> ruoli =new ArrayList<>();
	@JsonIgnoreProperties(value= {"utente"})
	private SessioneDTO sessione;
	@JsonIgnoreProperties(value= {"utente"})
	private List<PlaylistDTO> listaPlaylist= new ArrayList<>();
	@JsonIgnoreProperties(value= {"utente"})
	private List<RiproduzioneDTO> listaRiproduzioni = new ArrayList<>();
	
	
	public UtenteDTO() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}
	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}
	public StatoUtente getStato() {
		return stato;
	}
	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	public List<RuoloDTO> getRuoli() {
		return ruoli;
	}
	public void setRuoli(List<RuoloDTO> ruoli) {
		this.ruoli = ruoli;
	}
	public SessioneDTO getSessione() {
		return sessione;
	}
	public void setSessione(SessioneDTO sessione) {
		this.sessione = sessione;
	}
	public List<PlaylistDTO> getListaPlaylist() {
		return listaPlaylist;
	}
	public void setListaPlaylist(List<PlaylistDTO> listaPlaylist) {
		this.listaPlaylist = listaPlaylist;
	}
	public List<RiproduzioneDTO> getListaRiproduzioni() {
		return listaRiproduzioni;
	}
	public void setListaRiproduzioni(List<RiproduzioneDTO> listaRiproduzioni) {
		this.listaRiproduzioni = listaRiproduzioni;
	}
	public static UtenteDTO buildUtenteDTOFromModel(Utente source, boolean eagerRuoli,boolean eagerSessione,boolean eagerPlaylist,boolean eagerRiproduzione) {
		UtenteDTO result = new UtenteDTO();
		result.setId(source.getId());
		result.setCognome(source.getCognome());
		result.setDataRegistrazione(source.getDataRegistrazione());
		result.setNome(source.getNome());
		result.setPassword(source.getPassword());
		result.setUsername(source.getUsername());
		result.setStato(source.getStato());
		if (eagerRuoli) {
			for (Ruolo ruoloItem : source.getRuoli()) {
				result.getRuoli().add(RuoloDTO.buildRuoloDTOFromModel(ruoloItem));
			}
		}
		if (eagerPlaylist) {
			for (PlayList playlistItem : source.getListaPlayList()) {
				result.getListaPlaylist().add(PlaylistDTO.buildPlaylistDTOFromModel(playlistItem,false,false,false));
			}
		}
		if (eagerRiproduzione) {
			for (Riproduzione riproduzioneItem : source.getListaRiproduzioni()) {
				result.getListaRiproduzioni().add(RiproduzioneDTO.buildRiproduzioneDTOFromModel(riproduzioneItem,false,false,false,false));
			}
		}
		if(eagerSessione)
			result.setSessione(SessioneDTO.buildSessioneDTOFromModel(source.getSessione(), false));
		
		return result;
	}
	
	public static Utente buildUtenteModelFromDTO(UtenteDTO source, boolean eagerRuolo,boolean eagerSessione,boolean eagerPlaylist,boolean eagerRiproduzione) {
		Utente result = new Utente();
		result.setId(source.getId());
		result.setCognome(source.getCognome());
		
			result.setDataRegistrazione(source.getDataRegistrazione());
		
		result.setNome(source.getNome());
		result.setPassword(source.getPassword());
		result.setUsername(source.getUsername());
		result.setStato(source.getStato());
		if (eagerRuolo) {
			for (RuoloDTO ruoloDTOItem : source.getRuoli()) {
				result.getRuoli().add(RuoloDTO.buildRuoloModelFromDTO(ruoloDTOItem));
			}
		}
		if (eagerPlaylist) {
			for (PlaylistDTO playlistDTOItem : source.getListaPlaylist()) {
				result.getListaPlayList().add(PlaylistDTO.buildPlaylistModelFromDTO(playlistDTOItem,false,false,false));
			}
		}
		if (eagerRiproduzione) {
			for (RiproduzioneDTO riproduzioneDTOItem : source.getListaRiproduzioni()) {
				result.getListaRiproduzioni().add(RiproduzioneDTO.buildRiproduzioneModelFromDTO(riproduzioneDTOItem,false,false,false,false));
			}
		}
		if(eagerSessione)
			result.setSessione(SessioneDTO.buildSessioneModelFromDTO(source.getSessione(), !eagerSessione));
		return result;
	}
	
	public static List<UtenteDTO> buildListFromModelList(List<Utente> input){
		
		List<UtenteDTO> result= new ArrayList<UtenteDTO>();
		for(Utente utenteItem: input) {
			UtenteDTO utenteDTOtemp= UtenteDTO.buildUtenteDTOFromModel(utenteItem, false, false, false, false);
			result.add(utenteDTOtemp);
		}
		return result;
	}
}
