package com.cognizant.spring_learn.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    // Handles GET requests to the /authenticate endpoint.
    // It expects an "Authorization" header containing basic authentication credentials.
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Start: authenticate()");
        LOGGER.debug("Auth Header: {}", authHeader);

        // Extract the username from the Authorization header.
        String user = getUser(authHeader);

        // Generate a JWT for the authenticated user.
        String token = generateJwt(user);

        // Create a map to hold the token and return it as a JSON response.
        Map<String, String> result = new HashMap<>();
        result.put("token", token);

        LOGGER.info("End: authenticate()");
        return result;
    }

    // Helper method to extract the username from the Base64 encoded Authorization header.
    private String getUser(String authHeader) {
        LOGGER.info("Start: getUser()");
        LOGGER.debug("Auth Header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            LOGGER.error("Invalid Authorization header format.");
            // You might throw a custom exception or return a default/error value
            throw new IllegalArgumentException("Invalid Authorization header format.");
        }

        String encodedCredentials = authHeader.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String decodedString = new String(decodedBytes); // Format: "username:password"

        if (!decodedString.contains(":")) {
            LOGGER.error("Decoded credentials do not contain a colon for username:password separation.");
            throw new IllegalArgumentException("Invalid credentials format.");
        }

        String user = decodedString.split(":")[0];

        LOGGER.debug("User: {}", user);
        LOGGER.info("End: getUser()");
        return user;
    }

    // Helper method to generate a JWT.
    private String generateJwt(String user) {
        LOGGER.info("Start: generateJwt()");

        // Create a JWT builder.
        JwtBuilder builder = Jwts.builder();
        // Set the subject (username) of the token.
        builder.setSubject(user);
        // Set the token issue time as current time.
        builder.setIssuedAt(new Date());
        // Set the token expiry as 20 minutes from now (1200000 milliseconds).
        builder.setExpiration(new Date((new Date()).getTime() + 1200000));
        // Sign the token with a secret key using HS256 algorithm.
        // "secretkey" should be a strong, securely stored key in a real application.
        builder.signWith(SignatureAlgorithm.HS256, "secretkey");

        // Compact the JWT into its final string format.
        String token = builder.compact();
        LOGGER.debug("Generated JWT: {}", token);
        LOGGER.info("End: generateJwt()");
        return token;
    }
}