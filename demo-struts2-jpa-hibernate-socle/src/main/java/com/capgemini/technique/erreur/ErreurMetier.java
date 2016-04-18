package com.capgemini.technique.erreur;

/**
 * Classe repr�sentant une erreur m�tier.
 */
public class ErreurMetier {

	/** Code du message d'erreur. */
	private final String codeMessage;
	
	/** Champs en erreur. */
	private final String[] champs; 
	
	/** Param�tres du message d'erreur. */
	private final String[] params;

	/** Donn�es associ�es � l'erreur. */
	private final Object data;
	
	/**
	 * Constructeur d'une ErreurMetier.
	 * 
	 * @param codeMessage le code du message d'erreur
	 * @param champs les champs en erreur
	 * @param params les param�tres du message d'erreur
	 * @param data les donn�es associ�es � l'erreur
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
	 * Retourne les param�tres du message d'erreur.
	 * 
	 * @return les param�tres du message d'erreur
	 */
	public String[] getParams() {
		return params;
	}

	/**
	 * Retourne les donn�es message d'erreur.
	 * 
	 * @return les donn�es du message d'erreur
	 */
	public Object getData() {
		return data;
	}
}
