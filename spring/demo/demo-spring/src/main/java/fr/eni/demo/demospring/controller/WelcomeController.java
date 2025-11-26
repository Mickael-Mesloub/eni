package fr.eni.demo.demospring.controller;

import org.springframework.stereotype.Component;

// On peut redéfinir le nom du bean comme ceci :
@Component("welcomeControllerBean")
public class WelcomeController {

    public void welcome(){
        System.out.println("Welcome here!");
    }
}
