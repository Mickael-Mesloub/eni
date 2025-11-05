package ihm;

import model.MedecinGeneraliste;
import model.Patient;
import service.Methode;

import java.time.LocalDate;

/**
 * *********************** NE PAS MODIFIER CETTE CLASSE ****************************
 * @author ENI
 *
 */
public class TestClasses {

	public static void main(String[] args) {
		
		Methode ctrl = Methode.getMethode();
		System.out.println("___________________________ Test téléphone Patients ________________________");
		Patient mac = new Patient("Vambuce", "mac'douglas", "0123456789", 'F', 287060244010154l,
				LocalDate.of(1987, 6, 2), "Allergie aux arachides");
		//mettre la 1ere lettre de chaque mot du prénom en majuscule
		mac.setPrenom(ctrl.premiereEnMajuscule(mac.getPrenom(), "[-' ]"));
		//formattage numero de telephone
		mac.setNumeroDeTelephone(ctrl.formatNumeroTelephone(mac.getNumeroDeTelephone()));
		mac.afficher();System.out.println();

		
		Patient ines = new Patient("Perret", "inès", "0698745123", 'F', 245021067034521l, LocalDate.of(1945, 2, 10),
				null);
		//mettre la 1ere lettre de chaque mot du prénom en majuscule
		ines.setPrenom(ctrl.premiereEnMajuscule(ines.getPrenom(), "[-' ]"));
		//formattage numero de telephone
		ines.setNumeroDeTelephone(ctrl.formatNumeroTelephone(ines.getNumeroDeTelephone()));
		ines.afficher();System.out.println();

		
		Patient sidney = new Patient("Pamamobe", "sidney-scott", "0753428619", 'M', 192112192020142l,
				LocalDate.of(1992, 11, 21), null);
		//mettre la 1ere lettre de chaque mot du prénom en majuscule
		sidney.setPrenom(ctrl.premiereEnMajuscule(sidney.getPrenom(), "[-' ]"));
		//formattage numero de telephone
		sidney.setNumeroDeTelephone(ctrl.formatNumeroTelephone(sidney.getNumeroDeTelephone()));
		sidney.afficher();System.out.println();

		
		Patient jessie = new Patient("Watson", "jessie james", "01254266788", 'M', 287060244010154l,
				LocalDate.of(1975, 8, 17), "Allergie au lactose");
		//mettre la 1ere lettre de chaque mot du prénom en majuscule
		jessie.setPrenom(ctrl.premiereEnMajuscule(jessie.getPrenom(), "[-' ]"));
		//formattage numero de telephone
		jessie.setNumeroDeTelephone(ctrl.formatNumeroTelephone(jessie.getNumeroDeTelephone()));
		jessie.afficher();System.out.println();
		
		System.out.println("___________________________ Test téléphone Médecins ________________________");
		MedecinGeneraliste melanie = new MedecinGeneraliste("Malalaniche", "Mélanie", "0228031728");
		//mettre la 1ere lettre de chaque mot du prénom en majuscule
		melanie.setPrenom(ctrl.premiereEnMajuscule(melanie.getPrenom(), "[-' ]"));
		//formattage numero de telephone
		melanie.setNumeroDeTelephone(ctrl.formatNumeroTelephone(melanie.getNumeroDeTelephone()));
		melanie.afficher();System.out.println();

		
		MedecinGeneraliste edmond = new MedecinGeneraliste("Bosapin", "Edmond", "217181920");
		//mettre la 1ere lettre de chaque mot du prénom en majuscule
		edmond.setPrenom(ctrl.premiereEnMajuscule(edmond.getPrenom(), "[-' ]"));
		//formattage numero de telephone
		edmond.setNumeroDeTelephone(ctrl.formatNumeroTelephone(edmond.getNumeroDeTelephone()));
		edmond.afficher();System.out.println();

		
		MedecinGeneraliste vikie = new MedecinGeneraliste("Madge", "vickie", "OO04050607");
		//mettre la 1ere lettre de chaque mot du prénom en majuscule
		vikie.setPrenom(ctrl.premiereEnMajuscule(vikie.getPrenom(), "[-' ]"));
		//formattage numero de telephone
		vikie.setNumeroDeTelephone(ctrl.formatNumeroTelephone(vikie.getNumeroDeTelephone()));
		vikie.afficher();
		
	}
}
