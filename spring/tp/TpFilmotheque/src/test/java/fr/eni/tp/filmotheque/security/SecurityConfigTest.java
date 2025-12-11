package fr.eni.tp.filmotheque.security;

import fr.eni.tp.filmotheque.controller.FilmController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityConfigTest {
    private Logger logger = LoggerFactory.getLogger(SecurityConfigTest.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {
        logger.info(passwordEncoder.encode("azerty"));
    }
}
