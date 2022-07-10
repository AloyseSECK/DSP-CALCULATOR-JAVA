package FichierXML_et_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
//import java.io.InputStream;

//Merci a ce site pour l'aide : https://mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
//ainsi qu'a stackoverflow evidemment, comme toujours... !

public class MainTest {
	
	private static final String FILENAME = "C:\\Users\\abduk\\eclipse-workspace\\MiniProjet\\src\\FichierXML_et_parser\\data.xml";
	
	public static void main(String[] args){
//		System.out.println("Entrer un choix parmi 1, 2 ou 3");
//		System.out.println("1: Test simple : on parcourt le fichier, et on affiche le nom et id de chaque composant");
//		System.out.println("2: Test un peu plus avance, on parcourt les composants et on affiche, pour ceux qui sont de type ressource, le ou les extracteur permettant de les recuperer");
//        System.out.println("3: Test sur les recettes afin d'extraire, pour une recette, la liste des ingredients en entree et leur quantite ");
		
		Carburant carburant = new RessourceCarburant("qd", "sdf", Categorie.antimatter, 0);
		CentraleCarburant c = new CentraleCarburant(FILENAME, FILENAME, null, FILENAME, null, null);
		try {
			c.consommerCarburant(carburant);
		} catch (ErreurDesCompodants e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList< Composant> maList = new ArrayList<Composant>();
        test1(maList);
        //uploadRecettes(maList);
        // Affichage par orde alphabetique
        System.out.println(maList.size());
        for(Composant composant : maList) {
        	System.out.println(composant);
        	System.out.println();
        }
        
        Collections.sort(maList, new ComposantComparator());
        System.out.println(maList.size());
        for(Composant composant : maList) {
        	System.out.println(composant);
        	System.out.println();
        }
	}
	
	
	public static void test1(ArrayList<Composant> m) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			//On parcourt tous les composants du fichier
			NodeList list = doc.getElementsByTagName("items");
			//Pour chaque composant...
			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					//On regarde le nom et la categorie du composant, et on les affiche
					Element element = (Element) node;
					//Quand le tag lu est unique pour l'objet, on peut faire ainsi
					String category = element.getElementsByTagName("category").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String id = element.getElementsByTagName("id").item(0).getTextContent();
					System.out.println("category : "+category);
					System.out.println("id : "+id);
					System.out.println("name : "+name);
					
					// Si c'est une ressource
					if(category.equals("resource")){
						//Si l'objet est une ressource, on regarde qui permet de miner cette ressource
						//Quand l'objet peut contenir plusieurs fois le meme tag, on fait ainsi 
						NodeList l = element.getElementsByTagName("minedby");
						for (int i = 0; i < l.getLength(); i++){
							String minedby = l.item(i).getTextContent();
							System.out.println(" ----> "+minedby);
						}
						// verifie si c'est une ressource carburant ou pas
						Element carburant = (Element) element.getElementsByTagName("fuel").item(0);
						if(carburant != null) {
							String typeCategory = carburant.getElementsByTagName("category").item(0).getTextContent();
							double valeur = Double.parseDouble(carburant.getElementsByTagName("value").item(0).getTextContent());
							System.out.println(" ----> c'est un CARBURANT");
							System.out.println("type : "+typeCategory);
							System.out.println("valeur : "+valeur);
							try {
								m.add(new ComposantCarburant(id, name, Categorie.getCategorie(typeCategory), valeur));
							} catch (ErreurDesCompodants e) {
								e.printStackTrace();
							}
						}else{
							System.out.println(" ----> c'est une RESSOURCE SIMPLE");
							m.add(new Ressource(id, name));
						}
					}else if(category.equals("buildings")) { // si c'est un batiment
						Element extracteur = (Element) element.getElementsByTagName("mining").item(0);
						Element usine = (Element) element.getElementsByTagName("factory").item(0);
						
						if(usine != null) {
							if(extracteur != null) { // c'est un extracteur ELECTRIQUE
								System.out.println(" ----> c'est un EXTRACTEUR ELECTRIQUE");
								String typeUsine = usine.getElementsByTagName("type").item(0).getTextContent();
								double drain = Double.parseDouble(usine.getElementsByTagName("drain").item(0).getTextContent());
								double usage = Double.parseDouble(usine.getElementsByTagName("usage").item(0).getTextContent());
								double vitesseMinning = Double.parseDouble(extracteur.getElementsByTagName("speed").item(0).getTextContent());
								System.out.println("vitesseMinning : "+vitesseMinning);
								System.out.println("typeUsine : "+typeUsine);
								System.out.println("drain : "+drain);
								System.out.println("usage : "+usage);
								m.add(new ExtracteurElectrique(id, name, usage, drain, typeUsine, vitesseMinning));
							}
							else {// c'est une USINE ou Central
								Element categorieE = (Element) usine.getElementsByTagName("category").item(0);
								Element usageE = (Element) usine.getElementsByTagName("usage").item(0);
								Element drainE = (Element) usine.getElementsByTagName("drain").item(0);
								String typeUsine = usine.getElementsByTagName("type").item(0).getTextContent();
								
								System.out.println("typeUsine : "+typeUsine);
								if(categorieE != null) {// c'est un centrale à carburant
									System.out.println("c'est un CENTRALE à CARBURANT");
									String categorie = categorieE.getTextContent();
									double value = Double.parseDouble(usine.getElementsByTagName("value").item(0).getTextContent());
									Element vitesseE = (Element) usine.getElementsByTagName("speed").item(0);
									Double vitesseProduction = vitesseE != null ? Double.parseDouble(vitesseE.getTextContent()) : -1;
									System.out.println("categorie : "+categorie);
									System.out.println("vitesse : "+vitesseProduction);
									System.out.println("valeur : "+value);
									try {
										m.add(new CentraleCarburant(id, name, vitesseProduction, typeUsine, value, Categorie.getCategorie(categorie)));
									} catch (ErreurDesCompodants e) {
										e.printStackTrace();
									}
								}else if(drainE != null || usageE != null){
									System.out.println("c'est une USINE CLASSIQUE");
									double usage = usageE != null ? Double.parseDouble(usageE.getTextContent()) : -1;
									double drain = drainE != null ? Double.parseDouble(drainE.getTextContent()) : -1;
									Element vitesseE = (Element) usine.getElementsByTagName("speed").item(0);
									Double vitesseProduction = vitesseE != null ? Double.parseDouble(vitesseE.getTextContent()) : -1;
									System.out.println("drain : "+drain);
									System.out.println("usage : "+usage);
									System.out.println("vitesse : "+vitesseProduction);
									Usine u = new Usine(id, name, vitesseProduction, usage, drain, typeUsine);
									m.add(u);
								}else{
									System.out.println("c'est une CENTRALE à energie RENOUVELLABLE");
									double value = Double.parseDouble(usine.getElementsByTagName("value").item(0).getTextContent());
									System.out.println("valeur : "+value);
									m.add(new CentraleRenouveleable(id, name, typeUsine, value));
								}
								System.out.println();
								
							}							
						}
						else if(extracteur != null) { //c'est un EXTRACTEUR NON ELECTRIQUE
							System.out.println(" ----> c'est un EXTRACTEUR NOOOOOOOOON ELECTRIQUE");
							double vitesseMinning = Double.parseDouble(extracteur.getElementsByTagName("speed").item(0).getTextContent());
							System.out.println("vitesseMinning : "+vitesseMinning);
							m.add(new ExtracteurNonElectrique(id, name, vitesseMinning));
						}else System.out.println(" ----> c'est un BATIMENT simple");
					}
					
					// verifie si le composant est un carburant
					Element carburant = (Element) element.getElementsByTagName("fuel").item(0);
					if(category.equals("components") && carburant != null){
						System.out.println(" -----------------------> c'est un COMPOSANT CARBURANT");
						String typeCategory = carburant.getElementsByTagName("category").item(0).getTextContent();
						double valeur = Double.parseDouble(carburant.getElementsByTagName("value").item(0).getTextContent());
						System.out.println("type : "+typeCategory);
						System.out.println("valeur : "+valeur);
						try {
							m.add(new ComposantCarburant(id, name, Categorie.getCategorie(typeCategory), valeur));
						} catch (ErreurDesCompodants e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						System.out.println(" ----> c'est un composant simple");
						m.add(new Composant(id, name));
					}
					
				}
				//System.out.println();
			}
		}catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	//Test sur les recettes afin d'extraire, pour une recette, la liste des ingredients en entree et leur quantite 
	public static void uploadRecettes(ArrayList<Composant> m) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(FILENAME));
			//On parcourt tous les composants du fichier
			NodeList list = doc.getElementsByTagName("recipes");

			for (int temp = 0; temp < list.getLength(); temp++) {
				Node node = list.item(temp);
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					//On regarde le nom et la categorie du composant
					Element element = (Element) node;
					String id = element.getElementsByTagName("id").item(0).getTextContent();
					String name = element.getElementsByTagName("name").item(0).getTextContent();
					String producers = element.getElementsByTagName("producers").item(0).getTextContent();
					Double time = Double.parseDouble(element.getElementsByTagName("time").item(0).getTextContent());
					System.out.println("id : "+id);
					System.out.println("name : "+name);
					System.out.println("usine : "+producers);
					System.out.println("time : "+time);
					Recette recette = new Recette(id, name, null, temp, producers);
					//On recupere les ingredients in de la recette
					System.out.println("=> Ingredients :");
					Element input = (Element) element.getElementsByTagName("in").item(0);
					NodeList liste_ingredient = input.getElementsByTagName("*");
					for(int i=0; i<liste_ingredient.getLength(); i++){
						//On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
						Element e = (Element)liste_ingredient.item(i);
						String id_ingred = e.getNodeName();
						double qte = Double.parseDouble(input.getElementsByTagName(id_ingred).item(0).getTextContent());
				        System.out.println("  >> " + id_ingred + " x" + qte);
				        recette.ajoutIngredient(new Composant(id_ingred, null), qte);
					}
					
					System.out.println("=> Produits :");
					Element output = (Element) element.getElementsByTagName("out").item(0);
					if(output != null) {
					NodeList liste_produits = output.getElementsByTagName("*");
					for(int i=0; i<liste_produits.getLength(); i++){
						//On utilise la variable e pour recuperer le nom du tag (qui est le nom de l'item), et la variable input pour recuperer la quantite une fois qu'on connait le nom du tag
						Element e = (Element)liste_produits.item(i);
						String id_produit = e.getNodeName();
						double qte = Double.parseDouble(output.getElementsByTagName(id_produit).item(0).getTextContent());
				        System.out.println("  >> " + id_produit + " x" + qte);
				        recette.ajoutIngredient(new Composant(id_produit, null), qte);
					}}
					m.add(recette);
				}
				System.out.println();
			}
		}catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
}