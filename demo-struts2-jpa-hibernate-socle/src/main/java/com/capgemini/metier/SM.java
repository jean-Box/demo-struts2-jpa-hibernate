package com.capgemini.metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.configuration.Configuration;

import com.capgemini.technique.erreur.ErreurMetier;
import com.capgemini.technique.erreur.MetierException;
import com.capgemini.technique.util.ObjectUtil;

public class SM {
	
	@PersistenceContext
	protected EntityManager em;
	
	/**
	 * Configuration (acc�s aux properties)
	 */
	protected Configuration configuration;	
	
	/**
	 * Liste des erreurs m�tier
	 */
	private static final ThreadLocal<List<ErreurMetier>> TL_ERREURS_METIER = new ThreadLocal<List<ErreurMetier>>();
	
	/**
	 * Permet de remonter les erreurs m�tier sous forme d'exception.
	 * 
	 * @throws CEFMetierException l'exception contenat les erreurs m�tier
	 */
	public void remonterErreursMetier() throws MetierException {
		if (!aucuneErreurMetier()) {
			throw new MetierException(getErreursMetier());
		}
	}
	
	/**
	 * Ajoute une erreur m�tier dans la liste des erreurs.
	 * @param codeMessage le code du message d'erreur
	 * @param champs les champs en erreur
	 */
	protected void ajouterErreurMetier(final String codeMessage, final String... champs) {
		final ErreurMetier erreurMetier = new ErreurMetier(codeMessage, champs, null, null);
		
		getErreursMetier().add(erreurMetier);
	}
	
	/**
	 * Ajoute une erreur m�tier dans la liste des erreurs.
	 * @param codeMessage le code du message d'erreur
	 * @param data donn�es associ�es � l'erreur
	 */
	protected void ajouterErreurMetierAvecDonnees(final String codeMessage, final Object data) {
		final ErreurMetier erreurMetier = new ErreurMetier(codeMessage, null, null, data);
		
		getErreursMetier().add(erreurMetier);
	}
	
	/**
	 * Ajoute une erreur m�tier param�tr�e dans la liste des erreurs.
	 * @param codeMessage le code du message d'erreur
	 * @param params les param�tres du message d'erreur
	 */
	protected void ajouterErreurMetierParametree(final String codeMessage, final String... params) {
		final ErreurMetier erreurMetier = new ErreurMetier(codeMessage, null, params, null);
		
		getErreursMetier().add(erreurMetier);
	}
	
	/**
	 * Ajoute une erreur m�tier param�tr�e dans la liste des erreurs.
	 * @param codeMessage le code du message d'erreur
	 * @param champs les champs en erreur
	 * @param params les param�tres du message d'erreur
	 */
	protected void ajouterErreurMetierParametree(final String codeMessage, final String[] champs, final String[] params) {
		final ErreurMetier erreurMetier = new ErreurMetier(codeMessage, champs, params, null);
		
		getErreursMetier().add(erreurMetier);
	}
	
	/**
	 * Indique si aucune erreur m�tier n'a �t� d�tect�e.
	 * 
	 * @return true si aucune erreur m�tier
	 */
	protected boolean aucuneErreurMetier() {
		return getErreursMetier().isEmpty();
	}
	
	/**
	 * R�cup�re la liste courante d'erreurs m�tier.
	 * 
	 * @return liste des erreurs m�tiers
	 */
	public static List<ErreurMetier> getErreursMetier() {
		List<ErreurMetier> erreursMetier = TL_ERREURS_METIER.get();
		
		if (erreursMetier == null) {
			erreursMetier = new ArrayList<ErreurMetier>();
			TL_ERREURS_METIER.set(erreursMetier);
		}
		
		return erreursMetier;
	}

	/**
	 * Vide la liste des erreurs m�tier.
	 */
	public void nettoyerErreursMetier() {
		getErreursMetier().clear();
	}
	
    /**
     * Methode indiquant si la valeur est renseignee : 
     *      - la valeur n'est pas nulle
     *      - si la valeur est une String, elle n'est pas vide (apres trim)
     *  - si la valeur est un Integer, elle n'est pas a 0
     *  - si la valeur est une List, elle ne doit pas avoir une size a 0
     *  - si la valeur est une Map, elle ne doit pas avoir une size a 0
     * 
     * @param obj l'object a tester
     * @return true si la valeur est non nulle
     */
    protected boolean estRenseigne(Object obj) {
    	return ObjectUtil.estRenseigne(obj);
    }

	/**
	 * Renvoie configuration.
	 * 
	 * @return configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Valorise configuration.
	 * 
	 * @param config La valeur de configuration.
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}	

}
