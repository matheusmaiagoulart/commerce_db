package matheusmaia.commerce.controller;

import jakarta.validation.Valid;
import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.DadosAutenticacaoDTO;
import matheusmaia.commerce.services.Usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UsuarioController {


        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private AuthenticationManager authenticationManager;

        //Cadastrar Usuario
        @PostMapping("/cadastrarUsuario")
        public ResponseEntity cadastrarUsuario(@Valid @RequestBody CadastrarUsuarioDTO dados)
        {
            return usuarioService.cadastrarUsuario(dados);
        }

        //Login
        @PostMapping("/login")
        public ResponseEntity login(@RequestBody @Valid DadosAutenticacaoDTO dados)
        {

            return this.usuarioService.autenticarUsuario(dados);
        }
}

