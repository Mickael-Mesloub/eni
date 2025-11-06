package ihm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.*;

public class TestAttendu {

	//instanciation des adresses
	private static Adresse sh = new Adresse("ZAC du Moulin Neuf", 2, "B", "rue Benjamin Franklin", 44800, "Saint Herblain");
	
	//instanciation d'un medecin generaliste
	private static Personne melanie = new MedecinGeneraliste("Malalaniche", "Mélanie", "02.28.03.17.28", sh);
	
	//instanciation des medecins specialistes
	private static Medecin edmond = new MedecinSpecialiste("Bosapin", "Edmond", "02.28.03.17.24", sh);
	private static Medecin benoit = new MedecinSpecialiste("De Cajou", "Benoit", "02.28.03.17.23", sh);
	private static Medecin anne = new MedecinSpecialiste("Aimone", "Anne", "02.28.03.17.26", sh);
	private static Medecin sylvan = new MedecinSpecialiste("Tourne", "Sylvan", "02.28.03.17.21", sh);
	private static Medecin remy = new MedecinSpecialiste("Sion", "Remy", "02.28.03.17.25", sh);

	
	public static void main(String[] args) {
		System.out.println("__________________________ Médecins géneralistes ______________________________");
		System.out.println(melanie);
		MedecinGeneraliste melanieM = (MedecinGeneraliste) melanie;
		System.out.println("====== appel aux methodes ======");
		melanieM.examiner();
		melanieM.diagnostiquer();
		melanieM.conseiller();
		
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
		remyS.setSpecialite("GYNECOLOGIE OBSTETRIQUE");
		remyS.setTarif(55);
		System.out.println(remyS);

		System.out.println("_________ trier la liste des Médecins spécialistes par tarif croissant ___________");
		Comparator<MedecinSpecialiste> medecinComparator = new MedecinComparator();
		List<MedecinSpecialiste> liste = new ArrayList<MedecinSpecialiste>();
		liste.add(edmondB);
		liste.add(benoitD);
		liste.add(anneA);
		liste.add(sylvanT);
		liste.add(remyS);
		
		System.out.println("==== affichage de la collection dans l'ordre d'insertion ====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();
		
		Collections.sort(liste, medecinComparator);
		System.out.println("==== affichage de la collection triee dans l'ordre croissant des spécialités ====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();
		
		/* 
		 * Que faudrait-il rajouter afin de trier notre collection dans l'ordre decroissant des spécialités 
		 */
		Collections.sort(liste, medecinComparator.reversed());
		System.out.println("==== affichage de la collection triee dans l'ordre decroissant des spécialités ====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();

	}
}
