package matheusmaia.commerce.services.Transacao.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Usuario.UsuarioException;
import matheusmaia.commerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuario implements ValidadorTransacao{
    @Autowired
    private UserRepository userRepository;
    @Override
    public void validar(TransacaoDTO dados) {

        //Validacao Existencia do Usuario
        var usuario = userRepository.findById(dados.idUsuario())
                .orElseThrow(() -> new UsuarioException("Usuário não encontrado na base de dados! Transação não pode ser realizada!"));

    }
}
