package com.sarvesh.SecurityApp.SecurityApplication.handlers;

import com.sarvesh.SecurityApp.SecurityApplication.entity.User;
import com.sarvesh.SecurityApp.SecurityApplication.service.JwtService;
import com.sarvesh.SecurityApp.SecurityApplication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Value("${deploy.env:development}")
    private String deployEnv;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        try {
            log.info("OAuth2 success handler triggered");

            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();

            // Get email based on OAuth2 provider
            String email = getEmailFromOAuth2User(oAuth2User, token.getAuthorizedClientRegistrationId());

            log.info("OAuth2 authentication successful for email: {}", email);

            if (email == null || email.isEmpty()) {
                log.error("Email not found in OAuth2 response");
                response.sendRedirect("/login?error=email_not_found");
                return;
            }

            User user = userService.getUserByEmail(email);

            if (user == null) {
                // Create new user
                User newUser = User.builder()
                        .name(getNameFromOAuth2User(oAuth2User))
                        .email(email)
                        .build();

                user = userService.save(newUser);
                log.info("Created new user: {}", email);
            } else {
                log.info("Existing user found: {}", email);
            }

            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = jwtService.generateRefreshToken(user);

            // Set refresh token cookie
            Cookie cookie = new Cookie("refreshToken", refreshToken);
            cookie.setHttpOnly(true);
            cookie.setSecure("production".equals(deployEnv));
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            response.addCookie(cookie);

            // Redirect with access token
            String frontEndUrl = "http://localhost:8080/home.html?token=" + accessToken;

            log.info("Redirecting to: {}", frontEndUrl);

            // Use the redirect strategy for proper redirection
            getRedirectStrategy().sendRedirect(request, response, frontEndUrl);

        } catch (Exception e) {
            log.error("OAuth2 authentication failed", e);
            response.sendRedirect("/login?error=processing_failed");
        }
    }

    private String getEmailFromOAuth2User(DefaultOAuth2User oAuth2User, String registrationId) {
        // Handle different OAuth2 providers
        switch (registrationId.toLowerCase()) {
            case "google":
                return oAuth2User.getAttribute("email");
            case "github":
                return oAuth2User.getAttribute("email");
            case "facebook":
                return oAuth2User.getAttribute("email");
            default:
                return oAuth2User.getAttribute("email");
        }
    }

    private String getNameFromOAuth2User(DefaultOAuth2User oAuth2User) {
        String name = oAuth2User.getAttribute("name");
        if (name != null) {
            return name;
        }

        // Fallback for different providers
        String firstName = oAuth2User.getAttribute("given_name");
        String lastName = oAuth2User.getAttribute("family_name");

        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        }

        return oAuth2User.getAttribute("login"); // GitHub fallback
    }
}               