package com.pwojcieszak.frontService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetailsManager userDetailsManager =
                new InMemoryUserDetailsManager();
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("user")
                        .roles("USER")
                        .build());
        userDetailsManager.createUser(
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("admin")
                        .roles("ADMIN")
                        .build());
        return userDetailsManager;
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(customizer -> customizer
                        .requestMatchers("/front/skills/**")
                        .hasRole("ADMIN")
                        .anyRequest().permitAll()
                );
        http
                .formLogin(customizer -> customizer
                        .defaultSuccessUrl("/")
                )
                .httpBasic(Customizer.withDefaults())
                .logout(customizer -> customizer
                        .logoutSuccessUrl("/")
                );


        return http.build();
    }
}
