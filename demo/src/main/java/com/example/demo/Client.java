package com.example.demo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	private String prenom;
	private String nom;
	private int age;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private List<Produit> produits;
	
	public Client() {
		super();
	}
	
	public Client(String prenom, String nom, int age) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.age = age;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produit> getProduit() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits= produits;
}
}