package tn.esprit.fundsphere.Services.UserService;


import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.fundsphere.Entities.TransactionManagement.Portefeuille;
import tn.esprit.fundsphere.Entities.UserManagment.AuthenticationResponse;
import tn.esprit.fundsphere.Entities.UserManagment.Token;
import tn.esprit.fundsphere.Entities.UserManagment.User;
import tn.esprit.fundsphere.Entities.UserManagment.VerificaitonRequest;

import tn.esprit.fundsphere.Exceptions.UsernameAlreadyTakenException;
import tn.esprit.fundsphere.Repositories.UserRepository.TokenRepository;
import tn.esprit.fundsphere.Repositories.UserRepository.UserRepository;
import tn.esprit.fundsphere.Services.TransactionService.EmailService;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;

    private final TwoFactorsAuthenticationService twoFactorsAuthenticationService;
    private final UserRepository userRepository;

    @Autowired
    private EmailService emailService;
    public AuthenticationResponse register(User request) {
        if (repository.existsByUsername(request.getUsername()))
        {
            throw new UsernameAlreadyTakenException("Username is already taken. Please choose a different username.");
        }

          else {
            Portefeuille portefeuille = request.getPortefeuille();
            if (portefeuille == null) {
                portefeuille = new Portefeuille();
                request.setPortefeuille(portefeuille); // Associez le portefeuille à l'utilisateur
            }

            portefeuille.setTotalValue(1000);
            portefeuille.setLiquidity(1000);

            var user =User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .username(request.getUsername())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .mfaEnabled(request.isMfaEnabled())
                    .assure(request.isAssure())
                    .portefeuille(request.getPortefeuille())
                    .build();

            if (request.isMfaEnabled())
            {
                user.setSecret(twoFactorsAuthenticationService.generateNewSecret());
            }
            repository.save(user);

            // Send a confirmation email to the user
            String subject = "Welcome to Our Service!";
            String text = "Yay! You are registered, " + user.getFirstname() + "! Welcome to our service.";
            emailService.sendSimpleMessage(user.getEmail(), subject, text);

            return AuthenticationResponse.builder()
                    .build();
        }

    }
    public boolean isUserExists(String username) {
        // Check if a user with the given username exists in the database
        return repository.existsByUsername(username);
    }


    public AuthenticationResponse authentication(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        if (user.isMfaEnabled())
        {

            return  AuthenticationResponse.builder()
                    .accessToken("")
                    .refreshToken("refreshToken")
                    .mfaEnabled(true)
                    .build();
        }

        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllTokenByUser(user);

        saveToken(token,user);
        return  AuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .mfaEnabled(false)
                .build();
    }

    private void revokeAllTokenByUser(User user) {
        List<Token> validTokenListByUser =tokenRepository.findAllByTokenByUser(user.getIdUser());

        if(!validTokenListByUser.isEmpty()){

            validTokenListByUser.forEach(t->{
                t.setLoggedOut(true);
            });

        }

        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveToken(String token, User user) {
        Token token1 = new Token();
        token1.setToken(token);
        token1.setLoggedOut(false);
        token1.setUser(user);
        tokenRepository.save(token1);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return;
        }

        String refreshToken = authHeader.substring(7);
        String username = jwtService.extractUsername(refreshToken);

        if (username != null ) {

            var userDetails = this.repository.findByUsername(username).orElseThrow();

            if (jwtService.isValid(refreshToken, userDetails)) {

                var accessToken = jwtService.generateToken(userDetails);
                revokeAllTokenByUser(userDetails);
                saveToken(accessToken,userDetails);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    public AuthenticationResponse verifyCode(@NotNull VerificaitonRequest verificaitonRequest) {
        User user =repository.findByUsername(verificaitonRequest.getUsername())
                .orElseThrow(()-> new EntityNotFoundException(String.format("user not found %S",verificaitonRequest.getUsername())));

        if (twoFactorsAuthenticationService.isOtpNotValid(user.getSecret(), verificaitonRequest.getCode()))
        {
            throw new BadCredentialsException("Code is not correct");
        }
        var jwt =jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .accessToken(jwt)
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}


