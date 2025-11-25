package fr.eni.tp.escaperooms.controller;

import fr.eni.tp.escaperooms.bll.TreasureService;
import org.springframework.stereotype.Component;

@Component
public class TreasureRoomController {
	private TreasureService tresorService;

    public TreasureRoomController(TreasureService tresorService) {
        this.tresorService = tresorService;
    }

    public String fin() {
		return tresorService.ouvrir();
	}

	public void setTresorService(TreasureService tresorService) {
		this.tresorService = tresorService;
	}
}
