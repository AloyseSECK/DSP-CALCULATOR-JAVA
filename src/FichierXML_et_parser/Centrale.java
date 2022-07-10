package FichierXML_et_parser;

public class Centrale extends Usine {
	private double valueCapProd;
	
	public Centrale(String id, String nom, double vitesse, String type, double value) {
		super(id, nom, vitesse, 0, 0, type);
		this.valueCapProd = value;
	}

	public double getValueCapProd() {
		return valueCapProd;
	}

}
