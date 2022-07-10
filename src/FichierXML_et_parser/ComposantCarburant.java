package FichierXML_et_parser;

public class ComposantCarburant extends Composant implements Carburant{
	private Categorie categorie;
	private double valeur;
	
	public ComposantCarburant(String id, String nom, Categorie categorie, double val) {
		super(id, nom);
		this.categorie = categorie;
		this.valeur = val;
	}
	
	public double getValue() {
		return valeur;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	
	public String toString (){
		return "id : " + getId() +  "\n name : " + getNom() +  "\n categorie : " + getCategorie() +  "\n valeur : " + valeur; 
	}  
}
