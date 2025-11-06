package ihm;

import java.time.LocalDate;
import java.time.LocalTime;

import model.*;

/**
 * *********************** NE PAS MODIFIER CETTE CLASSE ****************************
 * @author ENI
 *
 */
public class TestSpecialite {

	private static Adresse sh = new Adresse("ZAC du Moulin Neuf", 2, "B", "rue Benjamin Franklin", 44800,
			"Saint Herblain");
	private static Adresse nio = new Adresse(19, null, "avenue Léo Lagrange", 79000, "Niort");
	private static Patient adhemar = new Patient("Pamamobe", "Adhémar", "0753428619", 'M', 192112192020142l,
			LocalDate.of(1992, 11, 21), null, nio);

	public static void main(String[] args) {
		MedecinSpecialiste celine = new MedecinSpecialiste("OCENSEMAIME", "Céline", "0748159263", sh, "ORL", 52);
		new Creneau(LocalTime.of(14, 0), 20, celine);
		new Creneau(LocalTime.of(14, 20), 20, celine);
		new Creneau(LocalTime.of(14, 40), 20, celine);
		new Creneau(LocalTime.of(15, 0), 20, celine);
		Creneau c = new Creneau(LocalTime.of(15, 20), 20, celine);
		new Creneau(LocalTime.of(15, 40), 20, celine);
		new Creneau(LocalTime.of(16, 0), 20, celine);
		new Creneau(LocalTime.of(16, 20), 20, celine);
		new Creneau(LocalTime.of(16, 40), 20, celine);
		new Creneau(LocalTime.of(17, 0), 20, celine);
		System.out.println("__________________________ Spécialistes __________________________");
		System.out.println(celine.toString());
		RendezVous rdvSpe = new RendezVous(c, adhemar, LocalDate.of(2020, 6, 17));
		System.out.println(rdvSpe.toString());
	}
}
