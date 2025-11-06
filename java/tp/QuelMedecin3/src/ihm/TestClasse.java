package ihm;

import java.time.LocalDate;
import java.time.LocalTime;

import model.*;

/**
 * *********************** NE PAS MODIFIER CETTE CLASSE ****************************
 * @author ENI
 *
 */
public class TestClasse {

	private static Adresse sh = new Adresse("ZAC du Moulin Neuf", 2, "B", "rue Benjamin Franklin", 44800,
			"Saint Herblain");
	private static Medecin melanie = new MedecinGeneraliste("Malalaniche", "Mélanie", "02.28.03.17.28", sh);
	private static Medecin celine = new MedecinSpecialiste("OCENSEMAIME", "Céline", "0748159263", sh, "CARDIOLOGIE", 52);

	private static Adresse nio = new Adresse(19, null, "avenue Léo Lagrange", 79000, "Niort");
	private static Patient adhemar = new Patient("Pamamobe", "Adhémar", "0753428619", 'M', 192112192020142l,
			LocalDate.of(1992, 11, 21), null, nio);

	public static void main(String[] args) {
		FichierMedical fichier;

		System.out.println("__________________________ Médecins Generalistes ______________________________");
		Creneau c1 = new Creneau(LocalTime.of(9, 0), 15, melanie);
		new Creneau(LocalTime.of(9, 15), 15, melanie);
		new Creneau(LocalTime.of(9, 30), 15, melanie);
		new Creneau(LocalTime.of(9, 45), 15, melanie);
		new Creneau(LocalTime.of(10, 30), 15, melanie);
		new Creneau(LocalTime.of(10, 45), 15, melanie);
		new Creneau(LocalTime.of(11, 15), 15, melanie);
		new Creneau(LocalTime.of(11, 30), 15, melanie);
		new Creneau(LocalTime.of(11, 45), 15, melanie);
		new Creneau(LocalTime.of(14, 0), 30, melanie);
		new Creneau(LocalTime.of(14, 30), 30, melanie);
		new Creneau(LocalTime.of(15, 0), 30, melanie);
		new Creneau(LocalTime.of(15, 30), 30, melanie);
		new Creneau(LocalTime.of(16, 0), 30, melanie);
		new Creneau(LocalTime.of(16, 30), 30, melanie);
		System.out.println(melanie.toString());
		System.out.println("__________________________ RDV avec Médecins Generalistes ______________________________");
		RendezVous rdvGen = new RendezVous(c1, adhemar, LocalDate.of(2023, 2, 17));
		System.out.println(rdvGen.toString());
		System.out.println("__________________________ Fiche medical avec Médecins Generalistes ______________________________");
		fichier = new FichierMedical(78, 178, "14/7", 94, 65, "probleme d'hypertension", "Perindropil 2mg (le matin au petit déjeuner) - Pantoprazole 20mg (le soir pendant le repas)", LocalDate.of(2023, 2, 17), adhemar, melanie);
		System.out.println(fichier.toString());
		
		System.out.println("__________________________ Medecins Spécialistes __________________________");
		new Creneau(LocalTime.of(14, 0), 20, celine);
		new Creneau(LocalTime.of(14, 20), 20, celine);
		new Creneau(LocalTime.of(14, 40), 20, celine);
		new Creneau(LocalTime.of(15, 0), 20, celine);
		Creneau c2 = new Creneau(LocalTime.of(15, 20), 20, celine);
		new Creneau(LocalTime.of(15, 40), 20, celine);
		new Creneau(LocalTime.of(16, 0), 20, celine);
		new Creneau(LocalTime.of(16, 20), 20, celine);
		new Creneau(LocalTime.of(16, 40), 20, celine);
		new Creneau(LocalTime.of(17, 0), 20, celine);
		System.out.println(celine.toString());
		System.out.println("__________________________ RDV avec Medecins Spécialistes __________________________");
		RendezVous rdvSpe = new RendezVous(c2, adhemar, LocalDate.of(2023, 3, 5));
		System.out.println(rdvSpe.toString());
		System.out.println("__________________________ Fiche medical avec Médecins Generalistes ______________________________");
		fichier = new FichierMedical(78, 178, "10/7", 95, 135, "probleme d'arythmie cardiaque", "Xarelto 20 mg (le soir au diner) - Bisoprolol 2.5mg (le matin au déjeuner)", LocalDate.of(2023, 3, 5), adhemar, celine);
		System.out.println(fichier.toString());
	}
}
