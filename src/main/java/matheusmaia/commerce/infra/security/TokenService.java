package matheusmaia.commerce.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import matheusmaia.commerce.domain.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("{api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario user){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("commerce")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
            return token;
        } catch(JWTCreationException exception){

            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

private Instant dataExpiracao() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));//2 horas para expierar e colocando o -3 hrs de acordo com o fuso horario do brasil
}

}
