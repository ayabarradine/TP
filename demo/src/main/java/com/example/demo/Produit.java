package com.example.demo;
import javax.persistence.*;
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    public Produit(Long id) {
		super();
		this.id = id;
	}

	private String nomprod;
    private float prix;
    private Integer numserie;
    
    @Embedded
    private Createur createur;
    
    

    public Produit() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomprod() {
        return nomprod;
    }

    public void setNomProd(String nomprod) {
        this.nomprod = nomprod;
    }
    public float prix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
    
    public Integer numserie() {
        return  numserie;
    }

    public void setNumserie(Integer numserie) {
        this.numserie = numserie;
    }

    public Createur getCrateur() {
        return createur;
    }

    public void setCreateur(Createur createur) {
        this.createur = createur;
    }
}
