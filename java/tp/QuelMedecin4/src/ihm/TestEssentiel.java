package ihm;

import model.Adresse;
import model.Medecin;
import model.MedecinSpecialiste;
import model.Personne;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestEssentiel {

	//instanciation des adresses
	private static Adresse sh = new Adresse("ZAC du Moulin Neuf", 2, "B", "rue Benjamin Franklin", 44800, "Saint Herblain");

	//instanciation d'un medecin generaliste
	private static Personne melanie = new Medecin("Malalaniche", "Mélanie", "02.28.03.17.28", sh);
	
	//instanciation des medecins specialistes
	private static Personne edmond = new MedecinSpecialiste("Bosapin", "Edmond", "02.28.03.17.24", sh);
	private static Personne benoit = new MedecinSpecialiste("De Cajou", "Benoit", "02.28.03.17.23", sh);
	private static Personne anne = new MedecinSpecialiste("Aimone", "Anne", "02.28.03.17.26", sh);
	private static Personne sylvan = new MedecinSpecialiste("Tourne", "Sylvan", "02.28.03.17.21", sh);
	private static Personne remy = new MedecinSpecialiste("Sion", "Remy", "02.28.03.17.25", sh);

	
	public static void main(String[] args) {
		System.out.println("__________________________ Médecins géneralistes ______________________________");
		System.out.println(melanie);
		
		System.out.println("__________________________ Médecins spécialistes ______________________________");
		MedecinSpecialiste edmondB = (MedecinSpecialiste) edmond;
		edmondB.setSpecialite("CARDIOLOGIE");
		edmondB.setTarif(52);
		System.out.println(edmondB);
		MedecinSpecialiste benoitD = (MedecinSpecialiste) benoit;
		benoitD.setSpecialite("ANGIOLOGIE");
		benoitD.setTarif(57);
		System.out.println(benoitD);
		MedecinSpecialiste anneA = (MedecinSpecialiste) anne;
		anneA.setSpecialite("STOMATOLOGIE");
		anneA.setTarif(55);
		System.out.println(anneA);
		MedecinSpecialiste sylvanT = (MedecinSpecialiste) sylvan;
		sylvanT.setSpecialite("PEDIATRIE");
		sylvanT.setTarif(52);
		System.out.println(sylvanT);
		MedecinSpecialiste remyS = (MedecinSpecialiste) remy;
		remyS.setSpecialite("GYNECOLOGIE OBSTETRIQU");
		remyS.setTarif(55);
		System.out.println(remyS);

		System.out.println("_________ trier la liste des Médecins spécialistes  ___________");
		List<MedecinSpecialiste> liste = new ArrayList<MedecinSpecialiste>();
		liste.add(edmondB);
		liste.add(benoitD);
		liste.add(anneA);
		liste.add(sylvanT);
		liste.add(remyS);
		
		System.out.println("======== affichage de la collection dans l'ordre d'insertion =======");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();
		
		Collections.sort(liste);
		System.out.println("====== affichage de la collection triee dans l'ordre croissant des tarifs =====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();
		
		/* 
		 * Que faudrait-il rajouter afin de trier notre collection dans l'ordre decroissant des tarifs ?
		 */
		Collections.sort(liste);
		System.out.println("===== affichage de la collection triee dans l'ordre decroissant des tarifs =====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();

	}
}
