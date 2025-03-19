package matheusmaia.commerce.services;


import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.DadosAutenticacaoDTO;
import matheusmaia.commerce.domain.Usuario.Usuario;
import matheusmaia.commerce.infra.security.TokenDadosJWT;
import matheusmaia.commerce.infra.security.TokenService;
import matheusmaia.commerce.repositories.UserRepository;
import matheusmaia.commerce.utils.TratamentoDeDados;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.events.Event;

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
    @Autowired
    private TratamentoDeDados tratamentoDeDados;

    //Cadastrar Usuário
    @Transactional
    public ResponseEntity cadastrarUsuario(CadastrarUsuarioDTO dados){

        //Tratamento e validação dos dados de entrada
        String login = tratamentoDeDados.tratamentoLogin(dados.login());
        String senha = tratamentoDeDados.tratamentoSenha(dados.senha());


        Usuario usuarioAlreadyExists = userRepository.exitsByLogin(dados.login());

        if(usuarioAlreadyExists != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado no sistema! Use outro nome de Login!");
        }


        String senhaCriptografada = passwordEncoder.encode(senha); //Criptografia da Senha

        //Salvando usuario ja validado e tratado
        var usuario = new Usuario(dados);
        usuario.setLogin(login);
        usuario.setSenha(senhaCriptografada);

        userRepository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body("O usuário foi criado com sucesso!");
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
    }
}
