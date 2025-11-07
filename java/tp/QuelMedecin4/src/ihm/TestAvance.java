package ihm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.*;

public class TestAvance {

	//instanciation des adresses
	private static Adresse sh = new Adresse("ZAC du Moulin Neuf", 2, "B", "rue Benjamin Franklin", 44800, "Saint Herblain");
	private static Adresse nantes = new Adresse(null, 20, null, "rue de la Paix", 44000, "Nantes");
	private static Adresse brest = new Adresse(null, 22, null, "rue de la Mer", 29000, "Brest");
	private static Adresse nio = new Adresse(19, null, "avenue Léo Lagrange", 79000, "Niort");
	private static Adresse lr = new Adresse(null, 18, "B", "rue des Fleurs", 17000, "La Rochelle");
	//private static Adresse p = new Adresse(null, 1, null, "rue des Fraises", 75000, "Paris");
	
	//instanciation d'un medecin generaliste
	private static Medecin melanie = new MedecinGeneraliste("Malalaniche", "Mélanie", "02.28.03.17.28", sh);
	
	//instanciation des medecins specialistes
	private static Medecin edmond = new MedecinSpecialiste("Bosapin", "Edmond", "02.28.03.17.24", sh, "CARDIOLOGIE", 52);
	private static Medecin benoit = new MedecinSpecialiste("De Cajou", "Benoit", "02.28.03.17.23", sh, "ANGIOLOGIE", 57);
	private static Medecin anne = new MedecinSpecialiste("Aimone", "Anne", "02.28.03.17.26", sh, "STOMATOLOGIE", 55);
	private static Medecin sylvan = new MedecinSpecialiste("Tourne", "Sylvan", "02.28.03.17.21", sh, "PEDIATRIE", 52);
	private static Medecin remy = new MedecinSpecialiste("Sion", "Remy", "02.28.03.17.25", sh, "GYNECOLOGIE OBSTETRIQUE", 55);

	//instanciation des patients
	private static Personne jean = new Patient("Dupond", "Jean", "0753428619",'M', 192112192020142l,
			LocalDate.of(1992, 11, 21), null, nio);
	private static Personne paul = new Patient("Dupond", "Paul", "0753428619", 'M', 192112192020142l,
			LocalDate.of(1992, 11, 21), null, nantes);
	private static Personne franck = new Patient("Duboulon", "Franck", "0753428619", 'M', 192112192020142l,
			LocalDate.of(1992, 11, 21), null, brest);
	private static Personne adhemar = new Patient("Pamamobe", "Adhémar", "0753428619", 'M', 192112192020142l,
			LocalDate.of(1992, 11, 21), null, lr);

	
	public static void main(String[] args) {
		System.out.println("__________________________ Médecins géneralistes ______________________________");
		System.out.println(melanie);
		System.out.println("========= test comportements =======");
		melanie.examiner();
		melanie.diagnostiquer();
		melanie.orienter();
		
		System.out.println("__________________________ Médecins spécialistes ______________________________");
		MedecinSpecialiste edmondB = (MedecinSpecialiste) edmond;
		edmondB.setSpecialite("CARDIOLOGIE");
		edmondB.setTarif(52);
		System.out.println(edmondB);
		System.out.println("========= test comportements =======");
		edmond.examiner();
		edmond.diagnostiquer();
		edmond.orienter();
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

		System.out.println("___ liste des Médecins spécialistes ____");
		List<MedecinSpecialiste> liste = new ArrayList<MedecinSpecialiste>();
		liste.add(edmondB);
		liste.add(benoitD);
		liste.add(anneA);
		liste.add(sylvanT);
		liste.add(remyS);
		
		System.out.println("====== affichage de la collection dans l'ordre d'insertion =====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();
		
		Collections.sort(liste, Personne.comparatorNomPrenomCroissant);
		System.out.println("====== affichage de la collection triee dans l'ordre croissant sur le nom et prenom =====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();
		
		Collections.sort(liste, Personne.comparatorNomPrenomDecroissant);
		System.out.println("===== affichage de la collection triee dans l'ordre decroissant sur le nom et prenom =====");
		for (MedecinSpecialiste ms : liste) {
			System.out.print(liste.indexOf(ms)+" - "+ ms);
		}
		System.out.println();


		System.out.println("__________________________ Patients ______________________________");
		List<Patient> patients = new ArrayList<Patient>();
		patients.add((Patient) adhemar);
		patients.add((Patient) paul);
		patients.add((Patient) jean);
		patients.add((Patient) franck);
		
		System.out.println("====== affichage de la collection dans l'ordre d'insertion =====");
		for (Patient p : patients) {
			System.out.print(patients.indexOf(p)+" - "+ p);
		}
		System.out.println();
		
		Collections.sort(patients, Personne.comparatorNomPrenomCroissant);
		System.out.println("====== affichage de la collection triee dans l'ordre croissant sur le nom et prenom =====");
		for (Patient p : patients) {
			System.out.print(patients.indexOf(p)+" - "+ p);
		}
		System.out.println();
		
		Collections.sort(patients, Personne.comparatorNomPrenomDecroissant);
		System.out.println("===== affichage de la collection triee dans l'ordre decroissant sur le nom et prenom =====");
		for (Patient p : patients) {
			System.out.print(patients.indexOf(p)+" - "+ p);
		}
		System.out.println();
		
	}
}
