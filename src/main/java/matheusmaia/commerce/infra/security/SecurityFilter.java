package matheusmaia.commerce.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matheusmaia.commerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //como se fosse uma classe generica só pro spring carregar ela
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {

        var tokenJWT = recuperarToken(request); //pega o token do cabeçalho

        if (tokenJWT != null) { //se o cabecalho com o token vier preenchido, ele pega e faz a validacao
            var subject = tokenService.getSubject(tokenJWT); //método que faz a validação do token
            var usuario = repository.findByLogin(subject); //procura no banco se o usuario esta cadastrado la

            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); //forcando autenticacao

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("logado na requisicao");
        }

        filterChain.doFilter(request, response); //pega o request e o response e encaminha pro proximo filtro, para seguir o fluxo

    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");//pega o token no cabeçalho

        if(authorizationHeader != null){
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }

}
