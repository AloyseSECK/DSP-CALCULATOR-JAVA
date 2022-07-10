package FichierXML_et_parser;

public class ExtracteurElectrique extends Extracteur {

	public ExtracteurElectrique(String id, String nom, double usage, double drain, String type, double v) {
		super(id, nom, 1, usage, drain, type, v);
	}
	public String toString()
	{
		return ("id : " + getId() +  "\n name : " + getNom() +  "\n vitesse : " + getVitesse() +  "\n usage : " + getUsage() +  "\n drain : " + getDrain() 
		+  "\n Type() : " + getType() +  "\n Vittessemining : " + getVitesseMinning() ) ; 
	}
	

}
