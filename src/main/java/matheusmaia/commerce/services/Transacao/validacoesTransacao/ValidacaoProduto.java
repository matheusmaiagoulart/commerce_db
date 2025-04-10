package matheusmaia.commerce.services.Transacao.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoException;
import matheusmaia.commerce.repositories.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoProduto implements ValidadorTransacao{
    private static final Logger log = LoggerFactory.getLogger(ValidacaoProduto.class);
    @Autowired
    private ProdutoRepository produtoRepository;
    @Override
    public void validar(TransacaoDTO dados) {
        log.info("/Transacao - Iniciando validação da existência do produto");
        var produto = produtoRepository.findById(dados.idProduto())
                .orElseThrow(() -> {
                    log.info("/Transacao - Produto não encontrado na base de dados! Exception");
                        return new ProdutoException("Produto não encontrado na base de dados! Transação não pode ser realizada!");
                        });
        log.info("/Transacao - Produto encontrado!");
    }
}
