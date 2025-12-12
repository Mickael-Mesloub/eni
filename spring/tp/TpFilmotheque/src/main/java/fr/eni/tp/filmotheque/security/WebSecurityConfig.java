package fr.eni.tp.filmotheque.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/accueil", "/films", "/films/detail", "/login", "/css/*", "/images/*").permitAll()
                        .requestMatchers("/films/create").hasRole("ADMIN")
                        .requestMatchers("/films/delete").hasRole("ADMIN")
                        .requestMatchers("/genres/create").hasRole("ADMIN")
                        .requestMatchers("/genres/delete").hasRole("ADMIN")
                        .requestMatchers("/genres/update").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/accueil", false)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) ->logout
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/accueil")
                        .permitAll());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //  Permet de tester l'app avec des mots de passe en clair
//        return NoOpPasswordEncoder.getInstance();
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
