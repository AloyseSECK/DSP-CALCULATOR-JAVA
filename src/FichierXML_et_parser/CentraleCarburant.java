
package FichierXML_et_parser;

public class CentraleCarburant extends Centrale {
	private Categorie categorie;
	
	public CentraleCarburant(String id, String nom, Double vitesse, String type, Double v, Categorie c) {
		super(id, nom, vitesse, type, v);
	}
	public Categorie getCategorie() {
		return categorie;
	}
	
	public void consommerCarburant(Carburant carburant) throws ErreurDesCompodants{
		if(this.categorie != carburant.getCategorie())
			throw new ErreurDesCompodants("La cat�g"+carburant.getCategorie());
	}
}
