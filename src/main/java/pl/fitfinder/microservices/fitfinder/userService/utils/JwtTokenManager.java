package pl.fitfinder.microservices.fitfinder.userService.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import pl.fitfinder.microservices.fitfinder.userService.utils.enums.UserFields;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class JwtTokenManager {
    //TODO do zmiany bo z FitFinderKluczSekretny!@
    //SHA256 skrót z wyrazu
    private static final String KEY = "1f1fa8e2ce645fd9ca5e6c9599dc3cb5ef12eb5a1f8a2d9f9d6e464d7de52bab";
    //Klucz symetryczny HS256
    private static final SecretKey SECRETKEY = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));

    public static String getUserName(String token) {
        return getClaim(token, UserFields.USERNAME.getField()).toString();
    }

    public static String getUserId(String token) {
        return getClaim(token, UserFields.ID.getField()).toString();
    }

    public static Object getClaim(String token, String claim) {
        try {
            return Jwts.parser().verifyWith(SECRETKEY).build().parseSignedClaims(token).getPayload().get(claim);
        } catch (UnsupportedJwtException signatureError) {
            return signatureError.getMessage();
        }
    }
}
