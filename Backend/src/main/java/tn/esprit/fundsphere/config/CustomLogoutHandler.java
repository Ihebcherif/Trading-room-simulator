package tn.esprit.fundsphere.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import tn.esprit.fundsphere.Entities.UserManagment.Token;
import tn.esprit.fundsphere.Repositories.UserRepository.TokenRepository;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return;
        }

        String token = authHeader.substring(7);
        Token storedToken =tokenRepository.findByToken(token).orElse(null);

        if(token != null){
            storedToken.setLoggedOut(true);
            tokenRepository.save(storedToken);
        }

    }
}
