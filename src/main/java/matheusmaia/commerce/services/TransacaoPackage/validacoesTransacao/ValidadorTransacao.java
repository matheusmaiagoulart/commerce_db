package matheusmaia.commerce.services.TransacaoPackage.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;

public interface ValidadorTransacao {

    void validar(TransacaoDTO dados);
}
