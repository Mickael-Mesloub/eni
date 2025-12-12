package fr.eni.tp.filmotheque.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
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
                        .defaultSuccessUrl("/accueil", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
