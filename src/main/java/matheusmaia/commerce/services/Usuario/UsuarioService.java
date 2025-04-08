package matheusmaia.commerce.services.Usuario;


import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.DadosAutenticacaoDTO;
import matheusmaia.commerce.domain.Usuario.Usuario;
import matheusmaia.commerce.infra.security.TokenDadosJWT;
import matheusmaia.commerce.infra.security.TokenService;
import matheusmaia.commerce.repositories.UserRepository;
import matheusmaia.commerce.services.Usuario.ValidaUsuario.ValidadorUsuario;
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

@Service
public class UsuarioService {


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

    //Cadastrar Usuário
    @Transactional
    public ResponseEntity cadastrarUsuario(CadastrarUsuarioDTO dados){

        //validacao das classes que implementaram a interface ValidadorUsuario
        validadores.forEach(validadorUsuario -> validadorUsuario.validaUsuarioCadastro(dados));

        String login = dados.login();
        String senha = dados.senha();


        //Criptografia da Senha
        String senhaCriptografada = passwordEncoder.encode(senha);

        //Salvando usuario ja validado e tratado
        var usuario = new Usuario(dados);
        usuario.setLogin(login);
        usuario.setSenha(senhaCriptografada);

        userRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("O usuário foi criado com sucesso!");
    }

    @Transactional(readOnly = true)
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
    }
}
