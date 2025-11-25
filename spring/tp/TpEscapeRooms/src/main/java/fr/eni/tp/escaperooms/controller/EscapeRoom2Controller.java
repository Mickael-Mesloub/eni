package fr.eni.tp.escaperooms.controller;

import fr.eni.tp.escaperooms.bll.RoomService;
import org.springframework.stereotype.Component;

@Component("room2")
public class EscapeRoom2Controller {
	private RoomService roomService;

	public EscapeRoom2Controller(RoomService roomService) {
		this.roomService = roomService;
	}

	public void entreeSalle2() {
		System.out.println(roomService.entreeSalle());
	}

}
