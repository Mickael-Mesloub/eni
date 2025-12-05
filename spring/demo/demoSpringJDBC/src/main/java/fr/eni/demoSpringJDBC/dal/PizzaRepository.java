package fr.eni.demoSpringJDBC.dal;

import fr.eni.demoSpringJDBC.bo.Pizza;

import java.util.List;

public interface PizzaRepository {
    List<Pizza> findAllPizzas();
    Pizza findPizzaById(int id);
    Pizza savePizza(Pizza pizza);
    void deletePizzaById(int id);
    Pizza updatePizza(Pizza pizza);
}
