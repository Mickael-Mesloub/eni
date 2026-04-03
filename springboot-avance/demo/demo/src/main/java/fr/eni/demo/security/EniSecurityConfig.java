package fr.eni.demo.security;

import jakarta.servlet.Filter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class EniSecurityConfig {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private Filter jwtAuthenticationFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth
// Permettre l'accès à l'URL racine à tout le monde
                    .requestMatchers("/eniecole").permitAll()
                    .requestMatchers("/eniecole/auth/**").permitAll()
// Permettre aux rôles EMPLOYE et ADMIN de manipuler les URLs en GET
                    .requestMatchers(HttpMethod.GET, "/eniecole/employes/**").hasAnyRole("EMPLOYE", "ADMIN")
// Restreindre la manipulation des méthodes POST, PUT, PATCH et DELETE au rôle
// ADMIN
                    .requestMatchers(HttpMethod.POST, "/eniecole/employes").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/eniecole/employes").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PATCH, "/eniecole/employes").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/eniecole/employes").hasRole("ADMIN")
// Toutes autres url et méthodes HTTP ne sont pas permises
                    .anyRequest().denyAll();
        });
// Désactivé Cross Site Request Forgery
// Non préconisé pour les API REST en stateless. Sauf pour POST, PUT, PATCH et
// DELETE
        http.csrf(csrf -> {
            csrf.disable();
        });
//Connexion de l'utilisateur
        http.authenticationProvider(authenticationProvider);
//Activer le filtre JWT et l'authentication de l'utilisateur
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
// Session Stateless
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        return http.build();
    }
}