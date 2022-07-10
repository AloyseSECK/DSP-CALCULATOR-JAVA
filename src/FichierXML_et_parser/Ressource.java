package FichierXML_et_parser;

import java.util.ArrayList;

public class Ressource extends Composant {
	private ArrayList<Extracteur> extracteurs;
	private int nbExtracteur;
	public Ressource(String id, String nom) {
		super(id, nom);
		this.extracteurs = new ArrayList<Extracteur>();
		this.nbExtracteur = 0;
	}
	public void ajouterExtracteur(Extracteur e) {
		this.extracteurs.add(e);
		this.nbExtracteur++;
	}
	public ArrayList<Extracteur> getExtracteurs() {
		return extracteurs;
	}
	public int getNbExtracteur() {
		return nbExtracteur;
	}
	public String toString()
    {
        String s = "id : " + getId() + " | name : " + getNom() + "\n" + "Liste des extracteurs : \n" ;
        for (int i = 0 ; i < extracteurs.size() ; i++ ){
            s += extracteurs.get(i).toString() ;  
        }
        return s ; 
        
    }	
}
