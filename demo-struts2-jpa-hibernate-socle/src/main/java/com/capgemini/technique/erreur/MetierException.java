package com.capgemini.technique.erreur;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception contenant des erreurs m�tier.
 */
public class MetierException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private final List<ErreurMetier> erreursMetier;

	/**
	 * Constructeur.
	 * 
	 * @param erreursMetier la liste des erreurs m�tier
	 */
	public MetierException(final List<ErreurMetier> erreursMetier) {
		this.erreursMetier = new ArrayList<ErreurMetier>(erreursMetier);
	}

	/**
	 * Retourne la liste des erreurs m�tier.
	 * 
	 * @return les erreurs m�tier
	 */
	public List<ErreurMetier> getErreursMetier() {
		return erreursMetier;
	}
}
