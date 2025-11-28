package fr.eni.demo.demospring.dal;
import fr.eni.demo.demospring.bo.Cours;

import java.util.List;

public interface CoursDAO {
	
	Cours read(long id);

	List<Cours> findAll();
	
	void insertCoursFormateur(long idCours, String emailFormateur);
}
