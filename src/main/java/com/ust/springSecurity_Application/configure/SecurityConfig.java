package com.ust.springSecurity_Application.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/home").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .formLogin();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails userDetails = User.builder()
                .username("Aarzoo")
                .password(encoder.encode("Aarzoo12"))
                .roles("USER")
                .build();

    UserDetails adminDetails=User.builder()
                .username("Admin")
                .password(encoder.encode("Admin12"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails,adminDetails);
    }

}
