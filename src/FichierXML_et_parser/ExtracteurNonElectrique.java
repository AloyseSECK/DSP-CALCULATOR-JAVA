package FichierXML_et_parser;

public class ExtracteurNonElectrique extends Extracteur {
	
	public ExtracteurNonElectrique(String id, String nom, double v) {
		super(id, nom, 1, 0, 0, null, v);
	}
	public String toString()
	{
		return ("id : " + getId() +  "\n name : " + getNom() +  "\n vitesse : " + getVitesse() +  "\n usage : " + getUsage() +  "\n drain : " + getDrain() 
		+  "\n Type() : " + getType()) ; 
	}
}
