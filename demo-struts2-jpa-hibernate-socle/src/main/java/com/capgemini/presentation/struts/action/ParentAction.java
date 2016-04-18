package com.capgemini.presentation.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.capgemini.technique.erreur.ErreurMetier;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Classe m�re des actions Struts�
 */
public class ParentAction extends ActionSupport implements ServletRequestAware {

	public static final String AFFICHER = "afficher";

	public static final String ERREUR = "erreur";

	public static final String VALIDER = "valider";
	
	public static final String MODIFIER = "modifier";

	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;

	private List<String> messagesErreursMetier = new ArrayList<String>();
		
	/**
	 * M�thode appel�e lorsqu'une erreur m�tier est remont�e.
	 * 
	 * @param erreursMetier la liste des erreurs m�tier
	 * @return results Struts
	 */
	public String traiterErreursMetier(final List<ErreurMetier> erreursMetier) {

		for (ErreurMetier erreurMetier : erreursMetier) {
			if (erreurMetier.getChamps() != null) {
				for (String champ : erreurMetier.getChamps()) {
					//addFieldError(champ, getText(erreurMetier.getCodeMessage()));
					addFieldError(champ, "");
				}
			}

			this.messagesErreursMetier.add(getText(erreurMetier.getCodeMessage(), erreurMetier.getParams()));
		}
		
		return afficher();
	}
	
	/**
	 * M�thode appel�e pour afficher la page.
	 * 
	 * @return result Struts
	 */
	protected String afficher() {
		return AFFICHER;
	}

	/**
	 * Indique si aucune erreur m�tier n'a �t� d�tect�e.
	 * 
	 * @return true si aucune erreur m�tier
	 */
	protected boolean aucuneErreurMetier() {
		return this.messagesErreursMetier.isEmpty();
	}

	/**
	 * M�thode appel�e en cas d'erreur technique.
	 * 
	 * @return result struts
	 */
	public String traiterErreursTechnique() {

		request.setAttribute("erreurTechnique", Boolean.TRUE);
		return ERREUR;
	}

	/**
	 * Retourne la requ�te HTTP.
	 * 
	 * @return la requ�te HTTP
	 */
	public HttpServletRequest getRequest() {
		return this.request;
	}

	/**
	 * Positionne la requ�te HTTP.
	 * 
	 * @param httpRequest
	 *            la requ�te HTTP
	 */
	public void setServletRequest(final HttpServletRequest httpRequest) {
		this.request = httpRequest;
	}

	public List<String> getMessagesErreursMetier() {
		return messagesErreursMetier;
	}
	
	public void validate() {
		
		if (hasErrors()) {
			afficher();
		}
		
	}
}
