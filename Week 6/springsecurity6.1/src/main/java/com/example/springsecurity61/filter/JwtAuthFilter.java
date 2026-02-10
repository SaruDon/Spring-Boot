package com.example.springsecurity61.filter;

import com.example.springsecurity61.entity.User;
import com.example.springsecurity61.repository.UserRepository;
import com.example.springsecurity61.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");

        if(requestTokenHeader==null || !requestTokenHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = requestTokenHeader.substring(7);

        Long userId = jwtService.getUserIdFromToken(token);

        if(userId==null && SecurityContextHolder.getContext().getAuthentication()==null){
            User user  = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found with id"+userId));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null,null
            );

            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(
                    usernamePasswordAuthenticationToken
            );
        }

        filterChain.doFilter(request,response);
    }
}
