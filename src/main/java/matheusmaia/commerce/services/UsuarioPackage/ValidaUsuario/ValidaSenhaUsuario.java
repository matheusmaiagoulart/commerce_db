package matheusmaia.commerce.services.UsuarioPackage.ValidaUsuario;

import matheusmaia.commerce.domain.Usuario.CadastrarUsuarioDTO;
import matheusmaia.commerce.infra.Exceptions.Usuario.UsuarioException;
import matheusmaia.commerce.repositories.UserRepository;
import matheusmaia.commerce.utils.TratamentoDeDados;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidaSenhaUsuario implements ValidadorUsuario {

    @Autowired
    private TratamentoDeDados tratamentoDeDados;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validaUsuarioCadastro(CadastrarUsuarioDTO dados) {

        final int TAMANHO_MINIMO_SENHA = 5;

        String senha = tratamentoDeDados.tratamentoDado(dados.senha(), "Senha");


     if(senha.length() <= TAMANHO_MINIMO_SENHA){
        throw new UsuarioException("Seu campo Senha precisa ter pelo menos " + TAMANHO_MINIMO_SENHA + " caracteres");
    }
}
}