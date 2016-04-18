package com.capgemini.technique.erreur;

/**
 * Classe encapsulant les exceptions techniques de l'application.
 * 
 * Cette classe h�rite de RuntimeException pour ne pas avoir � ajouter de clauses throws inutiles
 *
 */
public class TechniqueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur prenant une exception cause en param�tre.
	 *  
	 * @param cause la cause de probl�me technique
	 */
	public TechniqueException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructeur prenant un message en param�tre.
	 * 
	 * @param message le message d'erreur
	 */
	public TechniqueException(final String message) {
		super(message);
	}
	
	/**
	 * Constructeur prenant un message et une exception cause en param�tres.
	 * 
	 * @param message le message d'erreur
	 * @param cause la cause de probl�me technique
	 */
	public TechniqueException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
