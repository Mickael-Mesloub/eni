package fr.eni.tp.escaperooms.bll;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("trap")
@Service
public class Room2Service implements RoomService {

	@Override
	public String entreeSalle() {
		return "Perdu, vous etes dans la mauvaise salle !";
	}

}
