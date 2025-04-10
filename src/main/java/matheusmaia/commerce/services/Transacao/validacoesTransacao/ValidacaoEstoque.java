package matheusmaia.commerce.services.Transacao.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Estoque.QuantidadeInsuficienteException;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoEstoque implements ValidadorTransacao
{
    private static final Logger log = LoggerFactory.getLogger(ValidacaoEstoque.class);
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Override
    public void validar(TransacaoDTO dados) {
        log.info("/Transacao - Iniciando validação do Estoque!");

    var estoque = estoqueRepository.findById(dados.idProduto())
            .orElseThrow(() ->
            {
                log.info("/Transacao - Estoque do produto não foi encontrado!");
                return new ProdutoException("Produto não encontrado em estoque!");
            });

    if(estoque.getQuantidade() < dados.quantidade()){
        log.info("/Transacao - Quantidade insuficiente em estoque para realizar a venda! Disponível: " + estoque.getQuantidade() + " Solicitado: " + dados.quantidade());
        throw new QuantidadeInsuficienteException("Estoque Insuficiente! Disponível: " + estoque.getQuantidade() + " Solicitado: " + dados.quantidade());
    }
    log.info("/Transacao - Estoque disponível!");
    }
}
