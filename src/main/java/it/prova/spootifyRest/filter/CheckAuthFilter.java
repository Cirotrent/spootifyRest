package it.prova.spootifyRest.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.prova.spootifyRest.model.Utente;
import it.prova.spootifyRest.service.sessione.SessioneService;
import it.prova.spootifyRest.service.utente.UtenteService;

@Component
public class CheckAuthFilter implements Filter {

	@Autowired
	UtenteService utenteService;

	@Autowired
	SessioneService sessioneService;

	private static final String HOME_PATH = "";
	private static final String[] EXCLUDED_URLS = { "/login/","/riproduzione/"};
	private static final String[] PROTECTED_URLS = { "/admin/" };
	private static final String[] PROTECTED_URLS_CUSTOMER = { "/customer/" };

	@SuppressWarnings("unused")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String pathAttuale = httpRequest.getServletPath();
		boolean isInWhiteList = isPathInWhiteList(pathAttuale);

		// se non lo e' bisogna controllare sia sessione che percorsi protetti
		if (!isInWhiteList) {

			String token = httpRequest.getHeader("token");
			Utente utenteInSession = utenteService.findByToken(token);
			
			// intanto verifico se utente in sessione
			
			if (utenteInSession == null) {
				httpResponse.sendRedirect("/login/error");
				return;
			}
			// controllo che utente abbia ruolo admin se nel path risulta presente /admin/
			if (isPathForOnlyAdministrators(pathAttuale) && !utenteInSession.isAdmin()) {
				httpResponse.sendRedirect("/login/error");
				return;
			}
			// controllo che utente abbia ruolo customer se nel path risulta presente
			// /customer/
			if (isPathForOnlyCliente(pathAttuale) && !utenteInSession.isCliente()) {
				httpResponse.sendRedirect("/login/error");
				return;
			}
			//tra database ed eclipse c'è una differenza di orario di un ora e non sapendo cosa fare ho risolto cosi!
			Date dataDiScadenza= DateUtils.addHours(utenteInSession.getSessione().getDataScadenza(), -1);
			// Controllo se la sessione è scaduta
			if (dataDiScadenza.before(new Date())) {
				httpResponse.sendRedirect("/login/scaduta");
				return;
			}

			// aggiorno la sessione
			utenteInSession.getSessione().aggiornaData();
			sessioneService.aggiorna(utenteInSession.getSessione());


		}

		chain.doFilter(request, response);

	}

	private boolean isPathInWhiteList(String requestPath) {
		// bisogna controllare che se il path risulta proprio "" oppure se
		// siamo in presenza un url 'libero'
		if (requestPath.equals(HOME_PATH))
			return true;

		for (String urlPatternItem : EXCLUDED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyAdministrators(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

	private boolean isPathForOnlyCliente(String requestPath) {
		for (String urlPatternItem : PROTECTED_URLS_CUSTOMER) {
			if (requestPath.contains(urlPatternItem)) {
				return true;
			}
		}
		return false;
	}

}
