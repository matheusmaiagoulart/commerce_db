package matheusmaia.commerce.controller;

import jakarta.validation.Valid;
import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.DadosAutenticacaoDTO;
import matheusmaia.commerce.services.UsuarioService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/user")
public class UsuarioController {


        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private AuthenticationManager authenticationManager;


        @PostMapping("/cadastrarUsuario")
        public ResponseEntity cadastrarUsuario(@Valid @RequestBody CadastrarUsuarioDTO dados){
            usuarioService.cadastrarUsuario(dados);
            return ResponseEntity.ok().build();
        }


        @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid DadosAutenticacaoDTO dados){
            var auth = this.usuarioService.autenticarUsuario(dados);
            return ResponseEntity.ok(auth.getBody());
        }
}

