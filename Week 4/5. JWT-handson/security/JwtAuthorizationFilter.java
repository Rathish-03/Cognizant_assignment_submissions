package com.cognizant.spring_learn.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

// This filter extends BasicAuthenticationFilter to intercept requests and validate JWTs.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    // Constructor that takes the AuthenticationManager.
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        LOGGER.info("Start: JwtAuthorizationFilter constructor");
        LOGGER.debug("AuthenticationManager: {}", authenticationManager);
    }

    // This method is called for every incoming request.
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        LOGGER.info("Start: doFilterInternal()");

        // Get the Authorization header from the request.
        String header = req.getHeader("Authorization");
        LOGGER.debug("Authorization Header: {}", header);

        // If the header is null or does not start with "Bearer ",
        // it means there's no JWT or it's not in the expected format.
        // In this case, we let the request continue down the filter chain.
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        // Attempt to get authentication from the JWT.
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        // Set the authentication in the Spring Security context.
        // This makes the user authenticated for the current request.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue processing the request down the filter chain.
        chain.doFilter(req, res);
        LOGGER.info("End: doFilterInternal()");
    }

    // Helper method to parse the JWT and create an Authentication object.
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        LOGGER.info("Start: getAuthentication()");
        String token = request.getHeader("Authorization");

        if (token != null) {
            try {
                // Parse the token. Remove "Bearer " prefix.
                Jws<Claims> jws = Jwts.parser()
                        .setSigningKey("secretkey") // Use the same secret key used for signing.
                        .parseClaimsJws(token.replace("Bearer ", ""));

                // Extract the subject (username) from the token's body.
                String user = jws.getBody().getSubject();
                LOGGER.debug("User from JWT: {}", user);

                // If a user is found, create an authenticated token.
                // We pass null for credentials as the token itself is the credential.
                // The roles are empty here; in a real app, you'd extract roles from the JWT.
                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                }
            } catch (JwtException ex) {
                // Log the exception if the token is invalid (e.g., expired, tampered).
                LOGGER.error("JWT validation failed: {}", ex.getMessage());
                return null; // Return null if token is invalid.
            }
        }
        LOGGER.info("End: getAuthentication()");
        return null; // Return null if no token is present or valid.
    }
}