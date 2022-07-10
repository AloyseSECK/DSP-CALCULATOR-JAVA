package FichierXML_et_parser;


public class Composant implements Comparable<Composant>{
	private String id;
	private String nom;
	public Composant(String id, String nom ){
		this.id = id;
		this.nom = nom;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String toString (){
		return "id : " + id +  "\n name : " + nom ; 
	}
	@Override
	public int compareTo(Composant o) {
		return this.getNom().compareTo(o.getNom());
	}
	
}
