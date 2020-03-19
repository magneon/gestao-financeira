package br.com.softcube.expensemanagement.services.security;

import br.com.softcube.expensemanagement.models.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.*;

@Service
public class TokenService {

    @Value("${expenses-management.secret}")
    private String secret;

    public String generateTokenFor(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        LocalDate today = LocalDate.now();
        LocalDate expiration = today.plus(1, ChronoUnit.DAYS);

        return Jwts.builder()
                .setIssuer("expenses-management-api")
                .setSubject(user.getId().toString())
                .setIssuedAt(Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(expiration.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {

        if (token == null) {
            return false;
        } else {
            try {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

                return true;
            } catch (ExpiredJwtException e) {
                e.printStackTrace();
            } catch (UnsupportedJwtException e) {
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                e.printStackTrace();
            } catch (SignatureException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public Long getUserId(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

        return Long.parseLong(claims.getBody().getSubject());
    }
}
