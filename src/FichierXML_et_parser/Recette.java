package FichierXML_et_parser;

import java.util.ArrayList;

public class Recette extends Composant{
	private ArrayList<Composant> ingredients;
	private ArrayList<Double> comptIngredients;
	private ArrayList<Composant> produits;
	private ArrayList<Double> comptProduits;
	private Usine producteur;
	private String usine;
	private double temps;
	
	public Recette(String id, String name, Usine producteur, double temps, String usine) {
		super(id, name);
		this.producteur = producteur;
		this.temps = temps;
		this.ingredients = new ArrayList<Composant>();
		this.produits = new ArrayList<Composant>();
		this.comptIngredients = new ArrayList<Double>();
		this.comptProduits = new ArrayList<Double>();
		this.usine = usine;
	}
	
	public void ajoutProduit(Composant p, Double nb) {
		this.produits.add(p);
		this.comptProduits.add(nb);
	}
	
	public void ajoutIngredient(Composant i, Double nb) {
		this.ingredients.add(i);
		this.comptIngredients.add(nb);
	}
	
	public ArrayList<Composant> getIngredients() {
		return ingredients;
	}
	
	public void setIngredients(ArrayList<Composant> ingredients) {
		this.ingredients = ingredients;
	}
	
	public ArrayList<Composant> getProduits() {
		return produits;
	}
	
	public void setProduits(ArrayList<Composant> produits) {
		this.produits = produits;
	}
	
	public Usine getProducteur() {
		return producteur;
	}
	
	public void setProducteur(Usine producteur) {
		this.producteur = producteur;
	}

	public ArrayList<Double> getComptIngredients() {
		return comptIngredients;
	}

	public ArrayList<Double> getComptProduits() {
		return comptProduits;
	}

	public double getTemps() {
		return temps;
	}

	public void setTemps(double temps) {
		this.temps = temps;
	}

	public String getUsise() {
		return usine;
	}
	public String toString(){
        String s = "id : " + this.getId() + " | name : " + this.getNom() + "\n" ;
        for (int i = 0 ; i < ingredients.size() ; i++ ){
            s += ingredients.get(i).toString() + " : x" + comptIngredients.get(i) ;  
        }
        return s ; 
    }	
}
