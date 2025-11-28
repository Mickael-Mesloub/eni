package fr.eni.demo.demospring.dal;

import fr.eni.demo.demospring.bo.Formateur;

import java.util.List;

public interface FormateurDAO {
	void create(Formateur formateur);

	Formateur read(String emailFormateur);

	void update(Formateur formateur);

	List<Formateur> findAll();
}
