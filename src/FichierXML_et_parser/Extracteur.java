package FichierXML_et_parser;

public class Extracteur extends Usine {
	private double vitesseMinning;
	public Extracteur(String id, String nom, double vitesse, double usage, double drain, String type, double v) {
		super(id, nom, vitesse, usage, drain, type);
		this.vitesseMinning = v;
	}
	public double getVitesseMinning() {
		return vitesseMinning;
	}
	public String toString(){
		return ("id : " + getId() +  "\n name : " + getNom() +  "\n vitesse : " + getVitesse() +  "\n usage : " + getUsage() +  "\n drain : " + getDrain() 
		+  "\n Type() : " + getType() +  "\n Vittessemining : " + vitesseMinning ) ; 
	}
}
