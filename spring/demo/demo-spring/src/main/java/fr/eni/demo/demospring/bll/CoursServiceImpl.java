package fr.eni.demo.demospring.bll;

import java.util.List;

import fr.eni.demo.demospring.bo.Cours;
import fr.eni.demo.demospring.dal.CoursDAO;
import org.springframework.stereotype.Service;


@Service
public class CoursServiceImpl implements CoursService {
	private CoursDAO coursDAO;

	public CoursServiceImpl(CoursDAO coursDAO) {
		this.coursDAO = coursDAO;
	}

	@Override
	public List<Cours> getCours() {
		return coursDAO.findAll();
	}

	@Override
	public Cours findById(long id) {
		return coursDAO.read(id);
	}
}
