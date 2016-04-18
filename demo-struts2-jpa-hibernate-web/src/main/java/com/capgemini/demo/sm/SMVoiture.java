package com.capgemini.demo.sm;

import java.util.List;

import com.capgemini.demo.om.RechercheVoiture;
import com.capgemini.demo.om.Voiture;

/**
 * Interface des services métier pour l'entité Voiture.
 *
 * @author CAPGEMINI
 */
public interface SMVoiture {
	/**
	 * Effectue une recherche.
	 *
	 * @param criteres les critères de recherche
	 * @return une liste de Voiture correspondant aux critères
	 */
	List<Voiture> rechercher(RechercheVoiture criteres);
	
	/**
	 * Création d'un élément de type Voiture.
	 *
	 * @param voiture l'élément à créer
	 * @return l'élément nouvellement créé
	 */
	Voiture creer(Voiture voiture);
	
	/**
	 * Lecture d'un élément de type Voiture.
	 *
	 * @param id identifiant de l'élément à lire
	 * @return l'élément correspondant à l'id
	 */	
	Voiture lire(Integer id);
	
	/**
	 * Enregistrement d'un élément de type Voiture.
	 *
	 * @param voiture l'élément à enregistrer
	 */	
	void enregistrer(Voiture voiture);
	
	/**
	 * Suppression d'un élément de type Voiture.
	 *
	 * @param voiture l'élément à supprimer
	 */	
	void supprimer(Voiture voiture);
}