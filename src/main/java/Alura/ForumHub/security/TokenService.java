package Alura.ForumHub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import Alura.ForumHub.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String gerarToken(Usuario usuario) {
        return JWT.create()
            .withSubject(usuario.getEmail())
            .withExpiresAt(Date.from(Instant.now().plus(expiration, ChronoUnit.MILLIS)))
            .sign(Algorithm.HMAC256(secret));
    }
}
