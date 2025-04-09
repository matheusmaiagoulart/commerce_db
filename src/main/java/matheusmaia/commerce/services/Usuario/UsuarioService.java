package matheusmaia.commerce.services.Usuario;


import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.DadosAutenticacaoDTO;
import matheusmaia.commerce.domain.Usuario.Usuario;
import matheusmaia.commerce.infra.security.TokenDadosJWT;
import matheusmaia.commerce.infra.security.TokenService;
import matheusmaia.commerce.repositories.UserRepository;
import matheusmaia.commerce.services.Usuario.ValidaUsuario.ValidadorUsuario;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UsuarioService {


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(UsuarioService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private List<ValidadorUsuario> validadores;





    @Transactional
    public ResponseEntity cadastrarUsuario(CadastrarUsuarioDTO dados){

        log.info("Iniciando a validação para cadastrar usuário");

        //validacao das classes que implementaram a interface ValidadorUsuario
        validadores.forEach(validadorUsuario -> validadorUsuario.validaUsuarioCadastro(dados));

        String login = dados.login();
        String senha = dados.senha();

        String senhaCriptografada = passwordEncoder.encode(senha);

        var usuario = new Usuario(dados);
        usuario.setLogin(login);
        usuario.setSenha(senhaCriptografada);

        userRepository.save(usuario);

        log.info("Usuário cadastrado com sucesso!");

        return ResponseEntity.status(HttpStatus.CREATED).body("O usuário foi criado com sucesso!");
    }

    @Transactional(readOnly = true)
    public ResponseEntity autenticarUsuario(DadosAutenticacaoDTO dados){

        try {
            log.info("Iniciando a autenticação do usuário!");
            var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var auth = authenticationManager.authenticate(usernamePassword);
            var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());
            log.info("Usuário autenticado!");
            return ResponseEntity.ok(new TokenDadosJWT(tokenJWT));
        }
        catch (BadCredentialsException e){
            log.info("Usuário não está autorizado!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
