package FichierXML_et_parser;

import java.util.Comparator;

public class ComposantComparator implements Comparator<Composant> {
	@Override
	public int compare(Composant o1, Composant o2) {
        return o1.getNom().compareTo(o2.getNom());
    }
}