package fr.eni.demo.controller;

import fr.eni.demo.bo.Adresse;
import fr.eni.demo.bo.Civilite;
import fr.eni.demo.bo.Employe;
import fr.eni.demo.bo.accompagnement.ChargeRee;
import fr.eni.demo.bo.formation.Formateur;
import fr.eni.demo.dal.CiviliteRepository;
import fr.eni.demo.dal.EmployeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestDatas {
	@Autowired
	private CiviliteRepository civiliteRepository;

	@Autowired
	private EmployeRepository employeRepository;

	@Test
	void test01_Civilite() {	
		final Civilite m = civiliteRepository.save(Civilite
				.builder()
				.cle("M")
				.libelle("Monsieur")
				.build());
		final Civilite mme = civiliteRepository.save(Civilite
				.builder()
				.cle("Mme")
				.libelle("Madame")
				.build());
		final Civilite mx = civiliteRepository.save(Civilite
				.builder()
				.cle("Mx")
				.libelle("Mix")
				.build());

		assertThat(m).isNotNull();
		assertThat(mme).isNotNull();
		assertThat(mx).isNotNull();
	}
	
	@Test
	void test02_InjectEmploye() {	
		final List<Civilite> listeCivilite = civiliteRepository.findAll();
		assertThat(listeCivilite).isNotNull();
		assertThat(listeCivilite).isNotEmpty();
		assertThat(listeCivilite.size()).isEqualTo(3);
		final Civilite m = listeCivilite.get(0);
		final Civilite mme = listeCivilite.get(1);
		final Civilite mx = listeCivilite.get(2);	
		
		final List<Employe> listeEmployes = new ArrayList<>();
	
		listeEmployes.add(Formateur
				.builder()
				.nom("BAILLE")
				.prenom("Anne-Lise")
				.email("abaille@campus-eni.fr")
				.immatriculation("ENI_ECOLE_12398")
				.numDom("02XXXXXXXX")
				.civilite(mme)
				.adresse(Adresse
						.builder()
						.rue("15 rue de Paris")
						.codePostal("35000")
						.ville("Rennes")
						.build())
				.build());
		
		listeEmployes.add(Formateur
				.builder()
				.nom("DELACHESNAIS")
				.prenom("Frédéric")
				.email("fdelachesnais@campus-eni.fr")
				.immatriculation("ENI_ECOLE_14398")
				.numDom("02XXXXXXXX")
				.numPort("06XXXXXXXX")
				.civilite(mx)
				.adresse(Adresse
						.builder()
						.rue("151 rue de Paris")
						.codePostal("29000")
						.ville("Quimper")
						.build())
				.build());
	

		listeEmployes.add(Employe
				.builder()
				.nom("DAUTAIS")
				.prenom("Servane")
				.email("sdautais@campus-eni.fr")
				.immatriculation("ENI_ECOLE_09398")
				.numDom("02XXXXXXXX")
				.numPort("07XXXXXXXX")
				.civilite(mme)
				.adresse(Adresse
						.builder()
						.rue("1 rue de Paradis")
						.codePostal("44000")
						.ville("Nantes")
						.build())
				.build());

		listeEmployes.add(Formateur
				.builder()
				.nom("GOBIN")
				.prenom("Stéphane")
				.email("sgobin@campus-eni.fr")
				.immatriculation("ENI_Ecole_011112")
				.numDom("0288XXXXXX")
				.civilite(m)
				.adresse(Adresse
						.builder()
						.rue("198 rue de Rennes")
						.codePostal("44000")
						.ville("Nantes")
						.build())
				.build());

		listeEmployes.add(Employe
				.builder()
				.nom("NICOLAS")
				.prenom("Cédric")
				.email("cnicolas@campus-eni.fr")
				.immatriculation("ENI_ECOLE_10100")
				.numDom("02XXXXXXXX")
				.numPort("06XXXXXXXX")
				.civilite(m)
				.adresse(Adresse
						.builder()
						.rue("12 rue de Chartres")
						.codePostal("35000")
						.ville("Rennes")
						.build())
				.build());

		listeEmployes.add(ChargeRee
				.builder()
				.nom("PAGEOT")
				.prenom("Emma")
				.email("epageot@campus-eni.fr")
				.immatriculation("ENI_ECOLE_13398")
				.numDom("02XXXXXXXX")
				.numPort("06XXXXXXXX")
				.numeroBureau("04XXXXXXXX")
				.civilite(mme)
				.adresse(Adresse
						.builder()
						.rue("172 rue du Soleil")
						.codePostal("44000")
						.ville("Nantes")
						.build())
				.build());

		listeEmployes.forEach(emp ->{
			final Employe empDB = employeRepository.save(emp);
			assertThat(empDB).isNotNull();
			assertThat(empDB.getId()).isGreaterThan(0);
			assertThat(empDB.getAdresse()).isNotNull();
			assertThat(empDB.getAdresse().getId()).isGreaterThan(0);
		});
	}
}
