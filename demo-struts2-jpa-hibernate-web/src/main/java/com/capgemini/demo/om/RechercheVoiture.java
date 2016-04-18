package com.capgemini.demo.om;


/**
 * Recherche d'une voiture
 * 
 * @author CAPGEMINI
 */
public class RechercheVoiture {

    /**
     * l'attribut serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private Integer marque;

    /**
     * 
     */
    private String modele;

    /**
     * 
     */
    private String reference;

    /**
     * Renvoie l'attribut marque
     * @return l'attribut marque 
     */
    public Integer getMarque() {
        return marque;
    }
    
    /**
     * Valorise l'attribut marque
     * @param pMarque l'attribut marque
     */
    public void setMarque(Integer pMarque) {
    	this.marque = pMarque;
    }

    /**
     * Renvoie l'attribut modele
     * @return l'attribut modele 
     */
    public String getModele() {
        return modele;
    }
    
    /**
     * Valorise l'attribut modele
     * @param pModele l'attribut modele
     */
    public void setModele(String pModele) {
    	this.modele = pModele;
    }

    /**
     * Renvoie l'attribut reference
     * @return l'attribut reference 
     */
    public String getReference() {
        return reference;
    }
    
    /**
     * Valorise l'attribut reference
     * @param reference l'attribut reference
     */
    public void setReference(String reference) {
    	this.reference = reference;
    }
}