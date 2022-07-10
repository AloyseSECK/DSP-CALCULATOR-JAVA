package FichierXML_et_parser;

public class Usine extends Batiment {
	private double vitesse;
	private double usage;
	private double drain;
	private String type;
	public Usine(String id, String nom) {
		super(id, nom);
	}
	public Usine(String id, String nom, double vitesse, double usage, double drain, String type) {
		this(id, nom);
		this.type = type;
		this.vitesse = vitesse;
		this.usage = usage;
		this.drain = drain;
	}
	public void testeRepos(double d, double u) throws ErreurDesCompodants {
		if(d<u) throw new ErreurDesCompodants("Le bâtiment consomme plus 'au repos' qu’il ne le fait 'au travail'.");
	}
	public double getVitesse() {
		return vitesse;
	}

	public double getUsage() {
		return usage;
	}

	public double getDrain() {
		return drain;
	}
	public String getType() {
		return type;
	}
	public String toString(){
		return "id : " + getId() +  "\n name : " + getNom() +  "\n vitesse : " + vitesse +  "\n usage : " + usage +  "\n drain : " + drain +  "\n type : " + type ; 
	}

}
