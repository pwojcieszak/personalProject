package com.pwojcieszak.frontService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService() {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetailsManager userDetailsManager =
//                new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build());
//        userDetailsManager.createUser(
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build());
//        return new InMemoryUserDetailsManager();
//    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();

        List<UserDetails> listOfUsers = List.of(user, admin);
        return new InMemoryUserDetailsManager(listOfUsers);
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
