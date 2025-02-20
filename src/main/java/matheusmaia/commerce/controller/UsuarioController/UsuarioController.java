package matheusmaia.commerce.controller.UsuarioController;

import jakarta.validation.Valid;
import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.DadosAutenticacaoDTO;
import matheusmaia.commerce.domain.Usuario.Usuario;
import matheusmaia.commerce.services.UsuarioService.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/cadastrarUsuario")
    public ResponseEntity<Void> cadastrarUsuario(@Valid @RequestBody CadastrarUsuarioDTO dados){
        log.info("chegou na controller");
        usuarioService.cadastrarUsuario(dados);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacaoDTO dados){
        this.usuarioService.autenticarUsuario(dados.login(), dados.senha());
        return ResponseEntity.ok().build();
    }


}
