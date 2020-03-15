package it.prova.spootifyRest.web.dto.riproduzioneDTO;

import javax.validation.constraints.NotBlank;

public class RicercaDTO {
	@NotBlank(message = "Campo 'token': non puo' essere lasciato vuoto!")
	private String token;
	private Long idPlaylist;
	private Long idAlbum;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getIdPlaylist() {
		return idPlaylist;
	}
	public void setIdPlaylist(Long idPlaylist) {
		this.idPlaylist = idPlaylist;
	}
	public Long getIdAlbum() {
		return idAlbum;
	}
	public void setIdAlbum(Long idAlbum) {
		this.idAlbum = idAlbum;
	}
	public RicercaDTO() {
		super();
	}
	
	
	

}
