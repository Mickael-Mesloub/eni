package fr.eni.demospring;

import fr.eni.demospring.controller.FormateurController;
import fr.eni.demospring.controller.WelcomeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoSpringApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoSpringApplication.class, args);

        // par défaut, le nom du bean = le nom de la classe en camelCase
        WelcomeController controller = (WelcomeController) ctx.getBean("welcomeControllerBean");
        controller.welcome();

        // On peut aussi directement appeler la class plutôt que passer un nom de bean en paramètre
        WelcomeController controller2 = ctx.getBean(WelcomeController.class);
        controller2.welcome();

        // En vérifiant ce qu'on obtient en loggant nos beans
        // on remarque qu'ils ont la même adresse mémoire
            // -> ce sont donc des Singleton par défaut
        System.out.println(controller);
        System.out.println(controller2);

        FormateurController formateurController = ctx.getBean(FormateurController.class);
        formateurController.afficherFormateurs();
    }
}
