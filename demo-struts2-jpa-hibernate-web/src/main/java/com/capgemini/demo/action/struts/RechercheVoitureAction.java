package com.capgemini.demo.action.struts;

import java.util.List;

import com.capgemini.demo.om.Marque;
import com.capgemini.demo.om.ScrudVoiture;
import com.capgemini.demo.om.Voiture;
import com.capgemini.demo.sm.SMMarque;
import com.capgemini.demo.sm.SMVoiture;
import com.capgemini.presentation.struts.action.ParentAction;

/**
 * Action struts permettant de gérer les interactions avec l'écran de recherche des entités Voiture.
 *
 * @author CAPGEMINI
 */
public class RechercheVoitureAction extends ParentAction {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private ScrudVoiture scrudVoiture;
	
	private List<Voiture> voitures;
	
	private SMVoiture smVoiture;
	
	private SMMarque smMarque;
	
	private Integer idSelectionne;

	public Integer getIdSelectionne() {
		return idSelectionne;
	}

	public void setIdSelectionne(Integer idSelectionne) {
		this.idSelectionne = idSelectionne;
	}

	/**
	 * Traitement d'entrée dans l'écran. L'entrée via la méthode afficher n'est pas
	 * sujette à la validation.
	 * 
	 * @return Le code du résultat de l'action.
	 * 
	 * @throws Exception en cas d'erreur.
	 */
	public String afficher() {
		
		if (scrudVoiture.getMarques() == null) {
			scrudVoiture.setMarques(smMarque.rechercherTous());
		}
		
		if (scrudVoiture.isRecherche()) {
			voitures = smVoiture.rechercher(scrudVoiture.getRechercheVoiture());
		}
		
		return "afficher";
	}
	
	public String rechercher() {
		
		scrudVoiture.setRecherche(true);
				
		return afficher();
	}
	
	public String creer() {
				
		scrudVoiture.setVoiture(new Voiture());
		scrudVoiture.setCreation(true);
		
		return "detail";
	}
	
	public String selectionner() {
		
		scrudVoiture.setId(getIdSelectionne());
		
		Voiture voiture = smVoiture.lire(scrudVoiture.getId());
			
		scrudVoiture.setVoiture(voiture);
		scrudVoiture.setCreation(false);
		
		return "detail";
	}

	public SMVoiture getSmVoiture() {
		return smVoiture;
	}

	public void setSmVoiture(SMVoiture smVoiture) {
		this.smVoiture = smVoiture;
	}
	
	public SMMarque getSmMarque() {
		return smMarque;
	}

	public void setSmMarque(SMMarque smMarque) {
		this.smMarque = smMarque;
	}

	public ScrudVoiture getScrudVoiture() {
		return scrudVoiture;
	}

	public void setScrudVoiture(ScrudVoiture scrudVoiture) {
		this.scrudVoiture = scrudVoiture;
	}
	
	public List<Voiture> getVoitures() {
		return voitures;
	}

	public void setVoitures(List<Voiture> voitures) {
		this.voitures = voitures;
	}
}