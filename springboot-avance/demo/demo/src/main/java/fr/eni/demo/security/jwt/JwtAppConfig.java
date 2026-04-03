package fr.eni.demo.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class JwtAppConfig {
    /**
     * Authentification de l'utilisateur depuis la base de données
     */
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Bean
    UserDetailsService userDetailsService() {
        return username -> userInfoRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    /**
     * Implémente comment récupérer un UserDetails
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());
        return authProvider;
    }
    /**
     * DaoAuthenticationProvider recherche le UserDetails à partir du UserDetailsService
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
