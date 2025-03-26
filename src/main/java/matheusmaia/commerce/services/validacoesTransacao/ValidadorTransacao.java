package matheusmaia.commerce.services.validacoesTransacao;

import matheusmaia.commerce.domain.Venda.TransacaoDTO;

public interface ValidadorTransacao {

    void validar(TransacaoDTO dados);
}
