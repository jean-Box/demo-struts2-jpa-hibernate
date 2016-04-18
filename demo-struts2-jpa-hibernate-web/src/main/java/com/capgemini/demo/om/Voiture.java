package com.capgemini.demo.om;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Voiture {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8724901437112309152L;
	
	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Marque marque;
	
	@Column(nullable = false)
	private String modele;
	
	@Column(nullable = false)
	private String reference;
	
	@Column(name = "NOMBRE_PORTE")
	private Integer nombrePortes;
	
	@Column(name = "ENERGIE")
	private String energie; 
	
	@Column(name = "PUISSANCE_FISCALE")
	private Integer puissanceFiscale;
	

	public Voiture() {
	}

	public Voiture(Integer id,
				   Marque marque, 
				   String modele, 
				   Integer nombrePortes, 
				   String energie, 
				   Integer puissanceFiscale) {
		this.id = id;
		this.marque = marque;
		this.modele = modele;
		this.nombrePortes = nombrePortes;
		this.energie = energie;
		this.puissanceFiscale = puissanceFiscale;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public Integer getNombrePortes() {
		return nombrePortes;
	}

	public void setNombrePortes(Integer nombrePortes) {
		this.nombrePortes = nombrePortes;
	}

	public String getEnergie() {
		return energie;
	}

	public void setEnergie(String energie) {
		this.energie = energie;
	}

	public Integer getPuissanceFiscale() {
		return puissanceFiscale;
	}

	public void setPuissanceFiscale(Integer puissanceFiscale) {
		this.puissanceFiscale = puissanceFiscale;
	}
}
