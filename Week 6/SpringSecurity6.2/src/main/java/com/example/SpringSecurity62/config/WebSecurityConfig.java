    package com.example.SpringSecurity62.config;

    import com.example.SpringSecurity62.filter.JwtAuthFilter;
    import com.example.SpringSecurity62.handler.OAuth2SuccessHandler;
    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @RequiredArgsConstructor
    public class WebSecurityConfig {

        private final JwtAuthFilter jwtAuthFilter;
        private final OAuth2SuccessHandler oAuth2SuccessHandler;

        @Bean
        SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity){
            httpSecurity
                    .authorizeHttpRequests((auth)->{
                        auth.requestMatchers("/auth/**", "/home.html").permitAll();
                        auth.anyRequest().authenticated();
                    })
                    .csrf(csrf->csrf.disable())
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .oauth2Login(oauth2Config->
                            oauth2Config
                                    .successHandler(oAuth2SuccessHandler)
                                    .failureUrl("/login?error=true")
                    );

            return httpSecurity.build();
        }

        @Bean
        PasswordEncoder getPasswordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        AuthenticationManager authenticationManager(AuthenticationConfiguration configuration){
            return configuration.getAuthenticationManager();
        }

    }
