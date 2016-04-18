package com.capgemini.demo.sm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.demo.om.Marque;
import com.capgemini.demo.sm.SMMarque;
import com.capgemini.metier.SM;

/**
 * Impl�mentation des services m�tier pour l'entit� Marque.
 *
 * @author CAPGEMINI
 */
public class SMMarqueImpl extends SM implements SMMarque {
		
	/**
	 * Effectue une recherche.
	 *
	 * @param criteres les crit�res de recherche
	 * @return une liste de Voiture correspondant aux crit�res
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Marque> rechercherTous() {
				
		List<Marque> marques = em.createQuery("select m from Marque m").getResultList();
		
		return marques;		
	}
}