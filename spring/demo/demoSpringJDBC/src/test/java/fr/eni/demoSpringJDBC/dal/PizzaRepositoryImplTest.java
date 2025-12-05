package fr.eni.demoSpringJDBC.dal;

import fr.eni.demoSpringJDBC.bo.Pizza;
import fr.eni.demoSpringJDBC.exception.PizzaNotFound;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PizzaRepositoryImplTest {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("before all tests");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("after all tests");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("before each test");
        jdbcTemplate.update("delete from pizza");
        jdbcTemplate.update("insert into pizza (id, nom, prix) values (1, 'CALZONE', 13.60)");
        jdbcTemplate.update("insert into pizza (id, nom, prix) values (2, 'DELLA NONA', 14.90)");
        jdbcTemplate.update("insert into pizza (id, nom, prix) values (3, 'VULCANO', 14.10)");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("after each test");
    }

    @Test
    @DisplayName("test ajout pizza cas droit")
    public void testAjoutPizzaCasDroit() {
        //AAA
        //Arrange : Préparation du test
        Pizza pizza = new Pizza(5, "reine2", 12.0f);

        //Act : Appel de la méthode à tester
        Pizza newPizza = pizzaRepository.savePizza(pizza);

        //Assert : Vérification du résultat du Act
        assertNotNull(pizza);
        Pizza piz = pizzaRepository.findPizzaById(pizza.getId());
        assertEquals(pizza, piz);

    }

    @Test
    @DisplayName("test delete pizza")void testDeletePizza() {
        //AAA
        // Arrange : preparation du test
        Pizza pizza = new Pizza(99, "un nom de pizza", 12.0f);
        pizzaRepository.savePizza(pizza);

        // Act : appel de la methode à tester
        pizzaRepository.deletePizzaById(pizza.getId());

        // Assert : verification du resultat
        assertThrows(PizzaNotFound.class, ()->pizzaRepository.findPizzaById(pizza.getId()));

    }


    @Test
    @DisplayName("test findAllPizzas cas plusieurs pizzas existent") //Optionnel
    public void testFindAllPizzasCasPlusieursPizzas() {
        //AAA
        //Arrange : Préparation du test

        //Act : Appel de la méthode à tester
        List<Pizza> pizzas = pizzaRepository.findAllPizzas();

        //Assert : Vérification du résultat du Act
        assertNotNull(pizzas);
        assertEquals(3, pizzas.size());
    }

    // @Test
    //@DisplayName("test findAllPizzas cas plusieurs aucune pizza")
    public void testFindAllPizzasCasAucunePizza() {
        //AAA
        //Arrange : Préparation du test
        jdbcTemplate.update("delete from pizza");

        //Act : Appel de la méthode à tester
        List<Pizza> pizzas = pizzaRepository.findAllPizzas();

        //Assert : Vérification du résultat du Act
        assertNotNull(pizzas);
        assertEquals(0, pizzas.size());

    }

    @Test
    @DisplayName("test findPizzaById cas l'id existe")
    public void testFindPizzaCasIdExiste() {
        //AAA
        //Arrange : Préparation du test
        int id = 2;

        //Act : Appel de la méthode à tester
        Pizza pizza = pizzaRepository.findPizzaById(id);

        //Assert : Vérification du résultat du Act
        assertNotNull(pizza);
        assertEquals(id,  pizza.getId());
        assertEquals("DELLA NONA", pizza.getNom());

    }

    @Test
    @DisplayName("test findPizzaById cas l'id n'existe pas")
    public void testFindPizzaCasIdNExistePas() {
        //AAA
        //Arrange : Préparation du test
        int id = 999;

        //Act : Appel de la méthode à tester
        /*
        try{
            pizzaRepository.findPizzaById(id);
            fail();
        }catch(PizzaNotFound ex){
            //success
        }*/
        //Act ET Assert
        assertThrows(PizzaNotFound.class, ()->pizzaRepository.findPizzaById(id));


    }

}