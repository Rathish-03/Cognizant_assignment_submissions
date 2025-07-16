package com.cognizant.spring_learn.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

// Marks this class as a source of bean definitions and enables Spring Security's web security support.
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(encoder.encode("pwd")).roles("USER").build(),
                User.withUsername("admin").password(encoder.encode("pwd")).roles("ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();  // ✅ Only ONE build() call
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}



/*
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    // Defines in-memory users with their roles and encoded passwords.
    // This is for demonstration; in a real application, users would be loaded from a database.
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("pwd")).roles("ADMIN")
                .and()
                .withUser("user").password(passwordEncoder().encode("pwd")).roles("USER");

        return authenticationManagerBuilder.build();
    }

    // Provides a BCryptPasswordEncoder bean for password encoding.
    @Bean
    public PasswordEncoder passwordEncoder() {
        LOGGER.info("Start: passwordEncoder()");
        return new BCryptPasswordEncoder();
    }

    // Configures HTTP security, including CSRF protection, session management, and authorization rules.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        LOGGER.info("Start: securityFilterChain()");

        // Disable CSRF protection as JWTs are stateless and don't rely on sessions/cookies.
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                // Configure authorization rules for different URL patterns.
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                // Allow access to /authenticate for both USER and ADMIN roles.
                                .requestMatchers("/authenticate").hasAnyRole("USER", "ADMIN")
                                // All other requests require authentication.
                                .anyRequest().authenticated()
                )
                // Set session management to stateless, meaning no session will be created or used by Spring Security.
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Add the custom JWT authorization filter before the standard UsernamePasswordAuthenticationFilter.
                // This filter will intercept requests and validate JWTs.
                .addFilterBefore(new JwtAuthorizationFilter(authenticationManager(httpSecurity)),
                        UsernamePasswordAuthenticationFilter.class);

        LOGGER.info("End: securityFilterChain()");
        return httpSecurity.build();
    }
}*/