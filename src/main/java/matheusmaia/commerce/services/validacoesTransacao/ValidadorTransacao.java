package matheusmaia.commerce.services.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;

public interface ValidadorTransacao {

    void validar(TransacaoDTO dados);
}
