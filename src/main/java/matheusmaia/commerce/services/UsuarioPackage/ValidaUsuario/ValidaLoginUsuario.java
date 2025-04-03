package matheusmaia.commerce.services.UsuarioPackage.ValidaUsuario;

import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.domain.Usuario.Usuario;
import matheusmaia.commerce.infra.Exceptions.Usuario.UsuarioException;
import matheusmaia.commerce.repositories.UserRepository;
import matheusmaia.commerce.utils.TratamentoDeDados;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidaLoginUsuario implements ValidadorUsuario {

    @Autowired
    private TratamentoDeDados tratamentoDeDados;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validaUsuarioCadastro(CadastrarUsuarioDTO dados) {

        final int TAMANHO_MINIMO_LOGIN = 5;

        String login = tratamentoDeDados.tratamentoDado(dados.login(), "Login");

        if (login.length() <= TAMANHO_MINIMO_LOGIN) {
            throw new UsuarioException("Seu campo Login precisa ter pelo menos " + TAMANHO_MINIMO_LOGIN + " caracteres");
        }

        Usuario userAlreadyExists = userRepository.exitsByLogin(dados.login());

        if(userAlreadyExists != null) {
            throw new UsuarioException("Usuário já cadastrado no sistema! Use outro nome de Login!");
        }

    }
}
