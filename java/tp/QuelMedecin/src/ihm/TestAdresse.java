package ihm;

import model.Adresse;

/**
 * *********************** NE PAS MODIFIER CETTE CLASSE ****************************
 * @author ENI
 *
 */
public class TestAdresse {
	public static void main(String[] args) {
		System.out.println("__________________________ Adresses ______________________________");
		Adresse sh = new Adresse("ZAC du Moulin Neuf", 2, "B", "rue Benjamin Franklin", 44800, "Saint Herblain");
		Adresse nio = new Adresse(19, null, "avenue Léo Lagrange", 79000, "Niort");
		Adresse comp = new Adresse(4, null, "Rue des Glatiniers", 2100, "Saint-Quentin");

		sh.afficher();
		System.out.println("------------------------------------------------------------------");
		nio.afficher();
		System.out.println("------------------------------------------------------------------");
		comp.afficher();
	}

}
