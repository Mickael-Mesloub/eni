package fr.eni.demo.demospring.bll;
import fr.eni.demo.demospring.bo.Cours;

import java.util.List;

public interface CoursService {
	List<Cours> getCours();

	Cours findById(long id);
}
