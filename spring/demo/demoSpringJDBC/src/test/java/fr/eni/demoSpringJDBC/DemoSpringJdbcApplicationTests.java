/*
package fr.eni.demoSpringJDBC;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.demoSpringJDBC.bo.Pizza;
import fr.eni.demoSpringJDBC.dal.PizzaRepository;
import fr.eni.demoSpringJDBC.exception.PizzaNotFound;



@SpringBootTest
class DemoSpringJdbcApplicationTests {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	

	@Test
	@DisplayName("Test de l'ajout de pizza")
	void testCreatePizza() {
		//AAA
		//Arrange = Préparation du test
		Pizza pizza = new Pizza(4, "Marggherita", 12.0f);
		
		//Act = Appeler la méthode que l'on veut tester
		Pizza pizzaResultat = pizzaRepository.createPizza(pizza);
		
		//Assert
		assertNotNull(pizzaResultat);
	}
	

	@Test
	@DisplayName("Vérifie que l'on récupère toutes les pizzas (findAllPizzas)")
	void testFindAllPizzas() {
		List<Pizza> pizzas = pizzaRepository.findAllPizzas();
		System.out.println(pizzas);
	}

	@Test
	@DisplayName("Vérifie qu'une pizza est retournée quand on fournit un id connu (Cas positif)")
	void testFindPizzaByIdCasIdExistant() {
		Pizza pizza=null;
		try {
			pizza = pizzaRepository.findPizzaById(1);
		} catch (PizzaNotFound e) {
			//
			e.printStackTrace();
		}
		System.out.println(pizza);
	}


	@Test
	@DisplayName("Vérifie qu'une erreur est retournée quand on fournit un id inconnu (Cas négatif)")
	void testFindPizzaByIdCasIdInconnu() {
		Pizza pizza=null;
		try {
			pizza = pizzaRepository.findPizzaById(99);
			
			//Fail - Echec du test
			fail();
		} catch (PizzaNotFound e) {
			//Test réussi !!!
			
		}		
	}
	
	

	
}
*/
