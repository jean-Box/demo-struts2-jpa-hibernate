package com.capgemini.demo.sm;

import java.util.List;

import com.capgemini.demo.om.RechercheVoiture;
import com.capgemini.demo.om.Voiture;

/**
 * Interface des services m�tier pour l'entit� Voiture.
 *
 * @author CAPGEMINI
 */
public interface SMVoiture {
	/**
	 * Effectue une recherche.
	 *
	 * @param criteres les crit�res de recherche
	 * @return une liste de Voiture correspondant aux crit�res
	 */
	List<Voiture> rechercher(RechercheVoiture criteres);
	
	/**
	 * Cr�ation d'un �l�ment de type Voiture.
	 *
	 * @param voiture l'�l�ment � cr�er
	 * @return l'�l�ment nouvellement cr��
	 */
	Voiture creer(Voiture voiture);
	
	/**
	 * Lecture d'un �l�ment de type Voiture.
	 *
	 * @param id identifiant de l'�l�ment � lire
	 * @return l'�l�ment correspondant � l'id
	 */	
	Voiture lire(Integer id);
	
	/**
	 * Enregistrement d'un �l�ment de type Voiture.
	 *
	 * @param voiture l'�l�ment � enregistrer
	 */	
	void enregistrer(Voiture voiture);
	
	/**
	 * Suppression d'un �l�ment de type Voiture.
	 *
	 * @param voiture l'�l�ment � supprimer
	 */	
	void supprimer(Voiture voiture);
}