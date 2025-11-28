package fr.eni.demo.demospring.bll;

import fr.eni.demo.demospring.bo.Formateur;

import java.util.List;

public interface FormateurService {
	void add(String nom, String prenom, String email);

	List<Formateur> getFormateurs();
	
	Formateur findByEmail(String emailFormateur);
	
	void update(Formateur formateur);
	
	void updateCoursFormateur(String emailFormateur, long idCours);
}
