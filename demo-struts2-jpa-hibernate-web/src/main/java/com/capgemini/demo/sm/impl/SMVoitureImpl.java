package com.capgemini.demo.sm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.demo.om.RechercheVoiture;
import com.capgemini.demo.om.Voiture;
import com.capgemini.demo.sm.SMVoiture;
import com.capgemini.metier.SM;

/**
 * Impl�mentation des services m�tier pour l'entit� Voiture.
 *
 * @author CAPGEMINI
 */
public class SMVoitureImpl extends SM implements SMVoiture {
	
	/**
	 * Effectue une recherche.
	 *
	 * @param criteres les crit�res de recherche
	 * @return une liste de Voiture correspondant aux crit�res
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Voiture> rechercher(RechercheVoiture criteres) {
		
		Map<String, Object> params	= new HashMap<String, Object>();
		
		StringBuffer sb = new StringBuffer();
				
		sb.append("select v from Voiture v where 1=1 ");
		
		if(estRenseigne(criteres.getMarque())) {
			sb.append("and v.marque.id = :marque ");
			params.put("marque", criteres.getMarque());
		}
		
		if(estRenseigne(criteres.getModele())) {
			sb.append("and lower(v.modele) like lower(:modele) ");
			params.put("modele", "%" + criteres.getModele() + "%");
		}
		
		if(estRenseigne(criteres.getReference())) {
			sb.append("and lower(v.reference) like lower(:reference) ");
			params.put("reference", "%" + criteres.getReference() + "%");
		}
		
		Query query = em.createQuery(sb.toString());
		
		for (String key: params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		
		List<Voiture> voitures = query.getResultList();
		
		return voitures;		
	}
	
	/**
	 * Cr�ation d'un �l�ment de type Voiture.
	 *
	 * @param voiture l'�l�ment � cr�er
	 * @return l'�l�ment nouvellement cr��
	 */
	@Transactional
	public Voiture creer(Voiture voiture) {
		
		controlerRG001(voiture); 
		controlerRG002(voiture); 
		
		em.persist(voiture);
	
		return voiture;
	}
	
	/**
	 * Controler RG001 : 
	 * il ne peut y avoir plusieurs voiture avec la m�me r�f�rence
	 * 
	 * @param voiture la voiture
	 */
	@SuppressWarnings("unchecked")
	protected void controlerRG001(Voiture nouvelleVoiture) {
		
		boolean erreur = false;
		
		
		Query query = em.createQuery("select v from Voiture v where lower(v.reference) = lower(:reference)");
		query.setParameter("reference", nouvelleVoiture.getReference());
		
		List<Voiture> voitures = query.getResultList();
		
		if(voitures != null && voitures.size() > 0) {
			
			if (voitures.size() > 1) {
				erreur = true;
			} else {
				
				Voiture voiture = voitures.get(0);
				
				// Creation
				if(nouvelleVoiture.getId() == null) {
					erreur = true;
					
				// Modification : test autre entit� que celle qui est modifi�e
				} else if (!voiture.getId().equals(nouvelleVoiture.getId())) {
					erreur = true;
				}
			}
			
			if (erreur) {
				ajouterErreurMetier("RG001");
			}
		}
	}
	
	/**
	 * Controler RG002 : 
	 * Si l'�nergie est du diesel, la puissance fiscale doit �tre sup�rieure � 5
	 * 
	 * @param voiture la voiture
	 */
	protected void controlerRG002(Voiture voiture) {
		
		if (voiture.getEnergie() != null && voiture.getPuissanceFiscale() != null)  {	
			if (voiture.getEnergie().equals("Diesel") && voiture.getPuissanceFiscale() <= 5)  {
				ajouterErreurMetier("RG002");
			}
		}
	}
	
	/**
	 * Lecture d'un �l�ment de type Voiture.
	 *
	 * @param id identifiant de l'�l�ment � lire
	 * @return l'�l�ment correspondant � l'id
	 */	
	@Transactional(readOnly=true)
	public Voiture lire(Integer id) {
		
		Voiture voiture = (Voiture) em.find(Voiture.class, id);
		
		return voiture; 
	}
	
	/**
	 * Enregistrement d'un �l�ment de type Voiture.
	 *
	 * @param voiture l'�l�ment � enregistrer
	 */	
	@Transactional
	public void enregistrer(Voiture voiture) {
		
		controlerRG001(voiture); 
		controlerRG002(voiture); 
		
		em.merge(voiture);
	}
	
	/**
	 * Suppression d'un �l�ment de type Voiture.
	 *
	 * @param voiture l'�l�ment � supprimer
	 */	
	@Transactional
	public void supprimer(Voiture voiture) {
				
		em.remove(em.find(Voiture.class, voiture.getId()));
	}
}