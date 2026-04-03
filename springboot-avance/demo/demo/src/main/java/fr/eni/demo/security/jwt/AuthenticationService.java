package fr.eni.demo.security.jwt;

import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class AuthenticationService {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPseudo(), request.getPassword()));
        String jwtToken = jwtService.generateToken(request.getPseudo());
        AuthenticationResponse authResponse = new AuthenticationResponse();
        authResponse.setToken(jwtToken);
        return authResponse;
    }
}