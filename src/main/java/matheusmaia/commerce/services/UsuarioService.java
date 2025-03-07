package matheusmaia.commerce.services;


import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.DadosAutenticacaoDTO;
import matheusmaia.commerce.domain.Usuario.Usuario;
import matheusmaia.commerce.infra.security.TokenDadosJWT;
import matheusmaia.commerce.infra.security.TokenService;
import matheusmaia.commerce.repositories.UserRepository;
import org.slf4j.Logger;
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

@Service
public class UsuarioService {
    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    //Cadastrar Usuário
    @Transactional
    public ResponseEntity cadastrarUsuario(CadastrarUsuarioDTO dados){
        if(this.userRepository.findByLogin(dados.login()) != null) {return ResponseEntity.badRequest().body("Usuário já cadastrado no sistema!");}

        log.info("Chegou na Service");
        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        Usuario usuario1 = new Usuario(dados);
        userRepository.save(usuario1);

    return ResponseEntity.ok().build();
    }

    public ResponseEntity autenticarUsuario(DadosAutenticacaoDTO dados){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
            var auth = authenticationManager.authenticate(usernamePassword);
            var tokenJWT = tokenService.gerarToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new TokenDadosJWT(tokenJWT));
        }
        catch (BadCredentialsException e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }
//          catch (Exception e){
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro durante a autenticação");
//        }
    }
}
