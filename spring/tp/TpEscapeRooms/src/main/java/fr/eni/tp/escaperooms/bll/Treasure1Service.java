package fr.eni.tp.escaperooms.bll;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("trap")
@Service
public class Treasure1Service implements TreasureService {

	public String ouvrir() {
		return "Perdu, vous êtes tombé dans un piège !";
	}
	
}
