package ihm;

import model.Patient;
import java.time.LocalDate;


/**
 * *********************** NE PAS MODIFIER CETTE CLASSE ****************************
 * @author ENI
 *
 */
public class TestPatient {

	public static void main(String[] args) {
		System.out.println("__________________________ Patient _______________________________");
		Patient hillary = new Patient("Vambuce", "Hillary", "0123456789", 'F', 287060244010154L,
				LocalDate.of(1987, 6, 2), "Allergie aux arachides");
		Patient ines = new Patient("Perret", "Inès", "0698745123", 'F', 245021067034521L, LocalDate.of(1945, 2, 10),
				null);
		Patient adhemar = new Patient("Pamamobe", "Adhémar", "0753428619", 'M', 192112192020142L,
				LocalDate.of(1992, 11, 21), null);
		
		hillary.afficher();
		System.out.println("------------------------------------------------------------------");
		ines.afficher();
		System.out.println("------------------------------------------------------------------");
		adhemar.afficher();
	}
}
