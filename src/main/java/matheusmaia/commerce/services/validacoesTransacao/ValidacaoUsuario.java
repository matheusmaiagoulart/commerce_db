package matheusmaia.commerce.services.validacoesTransacao;

import matheusmaia.commerce.domain.Venda.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Usuario.UsuarioNaoEncontradoException;
import matheusmaia.commerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuario implements ValidadorTransacao{
    @Autowired
    private UserRepository userRepository;
    @Override
    public void validar(TransacaoDTO dados) {
        System.out.println("Validando usuário");
        var usuario = userRepository.findById(dados.idUsuario())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado na base de dados! Transação não pode ser realizada!"));

    System.out.println("Usuário validado");
    }
}
