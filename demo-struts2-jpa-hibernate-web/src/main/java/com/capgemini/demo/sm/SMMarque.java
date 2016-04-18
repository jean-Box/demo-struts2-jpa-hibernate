package com.capgemini.demo.sm;

import java.util.List;

import com.capgemini.demo.om.Marque;

/**
 * Interface des services métier pour l'entité Marque.
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