package FichierXML_et_parser;

public class RessourceCarburant extends Ressource implements Carburant {
	private Categorie categorie;
	private double valeur;
	public RessourceCarburant(String id, String nom, Categorie categorie, int val) {
		super(id, nom);
		this.categorie = categorie;
		this.valeur = val;
	}
	
	public double getValeur() {
		return valeur;
	}
	public String toString (){
		return "id : " + getId() +  "\n name : " + getNom() +  "\n categorie : " + getCategorie() +  "\n valeur : " + valeur; 
	}
	@Override
	public Categorie getCategorie() {
		// TODO Auto-generated method stub
		return categorie;
	}
}
