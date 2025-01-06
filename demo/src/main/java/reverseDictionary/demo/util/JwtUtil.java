package reverseDictionary.demo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

// Utility class for generating and validating JWT tokens.
@Component
public class JwtUtil {

    private final String SECRET_KEY = "secret"; // Secret key for JWT signing.

    // Generate JWT token for a user.
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Set the username in the token.
                .setIssuedAt(new Date()) // Token issued time.
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Valid for 10 hours.
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Sign with HS256.
                .compact();
    }

    // Validate the token and extract username.
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}

