package Model;

import Manager.Global;

public class user extends Global {

	private int numporte;
	private String nom;
	private String traitement;
	private int stock;
	private int IdStock;


	public user(String email, String mdp) {

		this.numporte = numporte;
		this.nom = nom;
		this.traitement = traitement;
		this.stock = stock;
		this.IdStock = IdStock;




	}

	public int getIdStock() {
		return IdStock;
	}





	public void setIdStock(int idStock) {
		IdStock = idStock;
	}




	public int getStock() {
		return stock;
	}





	public void setStock(int stock) {
		this.stock = stock;
	}





	public int getNumporte() {
		return numporte;
	}





	public void setNumporte(int numporte) {
		this.numporte = numporte;
	}





	public String getNom() {
		return nom;
	}





	public void setNom(String nom) {
		this.nom = nom;
	}





	public String getTraitement() {
		return traitement;
	}





	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public int getIdUser() {
		// TODO Auto-generated method stub
		return 0;
	}





}
