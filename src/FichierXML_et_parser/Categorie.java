package FichierXML_et_parser;

public enum Categorie {
	chemical, nuclear, antimatter;
	
	public static Categorie getCategorie(String c) throws ErreurDesCompodants {
		switch (c) {
		case "antimatter": {
			return antimatter;
		}
		case "chemical": {
			return chemical;
		}case "nuclear": {
			return nuclear;
		}default:
			throw new ErreurDesCompodants("La catégorie n'est pas attendues : " + c);
		}
	}
}
