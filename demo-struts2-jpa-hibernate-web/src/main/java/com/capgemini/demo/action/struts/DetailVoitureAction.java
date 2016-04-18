package com.capgemini.demo.action.struts;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;

import com.capgemini.demo.om.ScrudVoiture;
import com.capgemini.demo.sm.SMVoiture;
import com.capgemini.presentation.struts.action.ParentAction;

/**
 * Action struts permettant de g�rer les interactions avec l'�cran de d�tail d'une entit� Voiture.
 *
 * @author CAPGEMINI
 */
public class DetailVoitureAction extends ParentAction {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private ScrudVoiture scrudVoiture;
	
	private SMVoiture smVoiture;
	
	private List<String> energies;
	
	/**
	 * Traitement d'entr�e dans l'�cran. L'entr�e via la m�thode afficher n'est pas
	 * sujette � la validation.
	 * 
	 * @return Le code du r�sultat de l'action.
	 */
	public String afficher() {
		
		
		energies = new ArrayList<String>();
		energies.add("Diesel");
		energies.add("Essence");
		energies.add("GPL");
		
		return "afficher";
	}
	
	public List<String> getEnergies() {
		return energies;
	}

	public void setEnergies(List<String> energies) {
		this.energies = energies;
	}

	public String modifier() {
		
		smVoiture.enregistrer(scrudVoiture.getVoiture());
		
		return "recherche";
	}
	
	public String creer() {
		
		smVoiture.creer(scrudVoiture.getVoiture());
		
		return "recherche";
	}
	
	public String supprimer() {
		
		smVoiture.supprimer(scrudVoiture.getVoiture());
		
		return "recherche";
	}
	
	public String retour() {
		return "retour";
	}

	public SMVoiture getSmVoiture() {
		return smVoiture;
	}

	public void setSmVoiture(SMVoiture smVoiture) {
		this.smVoiture = smVoiture;
	}

	public ScrudVoiture getScrudVoiture() {
		return scrudVoiture;
	}

	public void setScrudVoiture(ScrudVoiture scrudVoiture) {
		this.scrudVoiture = scrudVoiture;
	}
}