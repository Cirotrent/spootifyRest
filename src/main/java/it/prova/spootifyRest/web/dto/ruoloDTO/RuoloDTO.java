package it.prova.spootifyRest.web.dto.ruoloDTO;

import it.prova.spootifyRest.model.Ruolo;

public class RuoloDTO {

	private Long id;
	private String descrizione;
	private String codice;

	public RuoloDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public static RuoloDTO buildRuoloDTOFromModel(Ruolo source) {
		RuoloDTO result = new RuoloDTO();
		result.setId(source.getId());
		result.setDescrizione(source.getDescrizione());
		result.setCodice(source.getCodice());

		return result;
	}

	public static Ruolo buildRuoloModelFromDTO(RuoloDTO source) {
		Ruolo result = new Ruolo();
		result.setId(source.getId());
		result.setDescrizione(source.getDescrizione());
		result.setCodice(source.getCodice());

		return result;
	}

}
