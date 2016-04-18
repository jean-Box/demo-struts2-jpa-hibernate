package com.capgemini.demo.sm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.demo.om.Marque;
import com.capgemini.demo.sm.SMMarque;
import com.capgemini.metier.SM;

/**
 * Implémentation des services métier pour l'entité Marque.
 *
 * @author CAPGEMINI
 */
public class SMMarqueImpl extends SM implements SMMarque {
		
	/**
	 * Effectue une recherche.
	 *
	 * @param criteres les critères de recherche
	 * @return une liste de Voiture correspondant aux critères
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Marque> rechercherTous() {
				
		List<Marque> marques = em.createQuery("select m from Marque m").getResultList();
		
		return marques;		
	}
}