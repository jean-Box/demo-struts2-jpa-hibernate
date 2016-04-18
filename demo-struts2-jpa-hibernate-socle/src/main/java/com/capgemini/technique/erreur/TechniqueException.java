package com.capgemini.technique.erreur;

/**
 * Classe encapsulant les exceptions techniques de l'application.
 * 
 * Cette classe hérite de RuntimeException pour ne pas avoir à ajouter de clauses throws inutiles
 *
 */
public class TechniqueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur prenant une exception cause en paramètre.
	 *  
	 * @param cause la cause de problème technique
	 */
	public TechniqueException(final Throwable cause) {
		super(cause);
	}
	
	/**
	 * Constructeur prenant un message en paramètre.
	 * 
	 * @param message le message d'erreur
	 */
	public TechniqueException(final String message) {
		super(message);
	}
	
	/**
	 * Constructeur prenant un message et une exception cause en paramètres.
	 * 
	 * @param message le message d'erreur
	 * @param cause la cause de problème technique
	 */
	public TechniqueException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
