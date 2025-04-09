package matheusmaia.commerce.services.Transacao.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Usuario.UsuarioException;
import matheusmaia.commerce.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoUsuario implements ValidadorTransacao{
    private static final Logger log = LoggerFactory.getLogger(ValidacaoUsuario.class);
    @Autowired
    private UserRepository userRepository;
    @Override
    public void validar(TransacaoDTO dados) {
        log.info("/Transacao - Buscando usuário na Base de Dados");

        var usuario = userRepository.findById(dados.idUsuario())
                .orElseThrow(() -> {
                    log.info("/Transacao - Usuário não encontrado na base de dados! Exception");
                    return new UsuarioException("Usuário não encontrado na base de dados! Transação não pode ser realizada!");
                });

        log.info("/Transacao - Usuário encontrado!");
    ;}
}
