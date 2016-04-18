package com.capgemini.demo.om;

import java.util.List;


/**
 
 * @author CAPGEMINI
 */
public class ScrudVoiture {

    /**
     * l'attribut serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * l'attribut creation
     */
    private boolean creation = false;
	
	/**
     * l'attribut recherche
     */
	private boolean recherche = false;
	
	/**
     * l'attribut voiture
     */
	private Voiture voiture;
	
	/**
	 * l'attribut id
	 */
	private Integer id;
	
	/**
     * l'attribut rechercheVoiture
     */
	private RechercheVoiture rechercheVoiture;
	
	private List<Marque> marques;
	
	public List<Marque> getMarques() {
		return marques;
	}

	public void setMarques(List<Marque> marques) {
		this.marques = marques;
	}
	
	/**
     * Renvoie l'attribut voiture
     * @return l'attribut voiture 
     */
	public Voiture getVoiture() {
		return voiture;
	}
	
	/**
     * Valorise l'attribut voiture
     * @param voiture l'attribut voiture
     */
	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}
	
	/**
     * Renvoie l'attribut id
     * @return l'attribut id
     */
	public Integer getId() {
		return id;
	}
	
	/**
     * Valorise l'attribut id
     * @param id l'attribut id
     */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
     * Renvoie l'attribut rechercheVoiture
     * @return l'attribut rechercheVoiture 
     */
	public RechercheVoiture getRechercheVoiture() {
		return rechercheVoiture;
	}
	
	/**
     * Valorise l'attribut rechercheVoiture
     * @param rechercheVoiture l'attribut rechercheVoiture
     */
	public void setRechercheVoiture(RechercheVoiture rechercheVoiture) {
		this.rechercheVoiture = rechercheVoiture;
	}
	/**
     * Renvoie l'attribut creation
     * @return l'attribut creation 
     */
	public boolean isCreation() {
		return creation;
	}

	/**
     * Valorise l'attribut creation
     * @param creation l'attribut creation
     */
	public void setCreation(boolean creation) {
		this.creation = creation;
	}

	/**
     * Renvoie l'attribut recherche
     * @return l'attribut recherche 
     */
	public boolean isRecherche() {
		return recherche;
	}

	/**
     * Valorise l'attribut recherche
     * @param recherche l'attribut recherche
     */
	public void setRecherche(boolean recherche) {
		this.recherche = recherche;
	}
}