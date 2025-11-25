package fr.eni.tp.escaperooms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.eni.tp.escaperooms.controller.EscapeRoom1Controller;
import fr.eni.tp.escaperooms.controller.EscapeRoom2Controller;
import fr.eni.tp.escaperooms.controller.TreasureRoomController;

/**
 * @author ENI Ecole
 * @version NE PAS MODIFIER LE CODE DE CETTE CLASSE
 */
@SpringBootApplication
public class EscapeRoomsApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EscapeRoomsApplication.class, args);

		System.out.println("Entrée dans la salle 1 : ");
		EscapeRoom1Controller room1 = ctx.getBean(EscapeRoom1Controller.class);
		room1.entreeSalle1();

		System.out.println("Entrée dans la salle 2 : ");
		EscapeRoom2Controller room2 = (EscapeRoom2Controller) ctx.getBean("room2");
		room2.entreeSalle2();

		System.out.println("Entrée dans la salle du trésor : ");
		TreasureRoomController treasureRoom = ctx.getBean(TreasureRoomController.class);
		System.out.println(treasureRoom.fin());
	}

}
