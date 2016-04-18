package com.capgemini.technique.erreur;

/**
 * Classe représentant une erreur métier.
 */
public class ErreurMetier {

	/** Code du message d'erreur. */
	private final String codeMessage;
	
	/** Champs en erreur. */
	private final String[] champs; 
	
	/** Paramètres du message d'erreur. */
	private final String[] params;

	/** Données associées à l'erreur. */
	private final Object data;
	
	/**
	 * Constructeur d'une ErreurMetier.
	 * 
	 * @param codeMessage le code du message d'erreur
	 * @param champs les champs en erreur
	 * @param params les paramètres du message d'erreur
	 * @param data les données associées à l'erreur
	 */
	public ErreurMetier(final String codeMessage, final String[] champs, final String[] params, final Object data) {
		this.codeMessage = codeMessage;
		this.champs = champs;
		this.params = params;
		this.data = data;
	}

	/**
	 * Retourne le code du message d'erreur.
	 * 
	 * @return le code du message d'erreur
	 */
	public String getCodeMessage() {
		return codeMessage;
	}

	/**
	 * Retourne les champs en erreur.
	 * 
	 * @return les champs en erreur
	 */
	public String[] getChamps() {
		return champs;
	}
	
	/**
	 * Retourne les paramètres du message d'erreur.
	 * 
	 * @return les paramètres du message d'erreur
	 */
	public String[] getParams() {
		return params;
	}

	/**
	 * Retourne les données message d'erreur.
	 * 
	 * @return les données du message d'erreur
	 */
	public Object getData() {
		return data;
	}
}
