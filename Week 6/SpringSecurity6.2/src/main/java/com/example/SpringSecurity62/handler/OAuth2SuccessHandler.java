package com.example.SpringSecurity62.handler;

import java.io.IOException;
import java.util.Optional;

import com.example.SpringSecurity62.entity.User;
import com.example.SpringSecurity62.exceptions.ResourceNotFoundException;
import com.example.SpringSecurity62.service.JwtService;
import com.example.SpringSecurity62.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.crypto.spec.OAEPParameterSpec;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {



        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();

               // Get email
        String email = oAuth2User.getAttribute("email");


        Optional<User> userTobeSaved  = userService.findByEmail(email);

        User user;
        if(userTobeSaved.isEmpty()) {
            User newUser = User
                    .builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(email)
                    .build();

             user = userService.save(newUser);
        }else{
              user = userTobeSaved.get();
        }
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshTokenToken(user);

        Cookie cookie= new Cookie("refreshToken",refreshToken);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        String frontEndUrl = "http://localhost:8080/home.html?token="+accessToken;

        getRedirectStrategy().sendRedirect(request,response,frontEndUrl);

    }
}