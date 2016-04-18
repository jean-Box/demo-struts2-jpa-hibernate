package com.capgemini.demo.sm;

import java.util.List;

import com.capgemini.demo.om.Marque;

/**
 * Interface des services m�tier pour l'entit� Marque.
 *
 * @author CAPGEMINI
 */
public interface SMMarque {
	
	/**
	 * renvoie toutes les marques
	 * @return une liste de marques
	 */
	List<Marque> rechercherTous();
	
	
}