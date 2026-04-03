package fr.eni.cave.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class EniSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/caveavin/bouteilles").permitAll()
                        .requestMatchers(HttpMethod.POST, "/caveavin/bouteilles").hasRole("OWNER")
                        .requestMatchers(HttpMethod.GET, "/caveavin/paniers/{id}").permitAll()
                        // TODO : add matchers
                        //.anyRequest().denyAll()

                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    UserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.setUsersByUsernameQuery("SELECT login, password, 1 FROM Cav_user WHERE login = ?");
        users.setAuthoritiesByUsernameQuery("SELECT login, authority FROM Cav_user WHERE login = ?");
        return users;
    }
}