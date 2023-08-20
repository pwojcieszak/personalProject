package com.pwojcieszak.frontService.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthConverter jwtAuthConverter;
    @Value("${spring.security.login-page}")
    private String loginPage;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .anyRequest().permitAll()
                )
//                .oauth2Login(configurer -> configurer
//                        .loginPage("http://localhost:8181/realms/personal-project-realm/protocol/openid-connect/auth")
//                )
                .httpBasic(configurer -> configurer
                        .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint(loginPage))
                );

        http
                .oauth2ResourceServer(server -> server
                                .jwt(jwtConfigurer -> jwtConfigurer
                                        .jwtAuthenticationConverter(jwtAuthConverter)
                                )
                );


        http
                .sessionManagement(customizer -> customizer
                        .sessionCreationPolicy(STATELESS)
                );

        return http.build();
    }
}
