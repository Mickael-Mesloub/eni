package fr.eni.demo.configuration.security;

import fr.eni.demo.security.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Doit être active dès qu'il y a une requête
//Doit devenir un bean pour spring
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // Injection de la couche BLL pour gérer le token
    private JwtService jwtService;
    // Injection de la couche BLL pour gérer les données de la DB
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
// vérifier le jeton JWT
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
// vérifier qu’il y a une donnée dans l’entête qui correspond à Authorization
// l’entête contient Bearer <jeton> SINON erreur
// Sinon laissé le comportement suivre son cours
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);// 7 correspond à Bearer
// Vérification de l'utilisateur
        final String userEmail = jwtService.extractUserName(jwt);// Extraire du jeton JWT
// Validation des données par rapport à la DB
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
// Check in DB
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
// Validation du jeton JWT
            if (jwtService.isTokenValid(jwt, userDetails)) {
// Gestion du contexte de sécurité de l’utilisateur
//Création d'un nouveau jeton avec les informations et les rôles de l'utilisateur
                UsernamePasswordAuthenticationToken authToken = new
                        UsernamePasswordAuthenticationToken(userEmail, null,
                        userDetails.getAuthorities());
//Transmettre les détails de la demande d’origine
                authToken.setDetails(new
                        WebAuthenticationDetailsSource().buildDetails(request));
//Mise à jour du contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}