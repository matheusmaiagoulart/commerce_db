package matheusmaia.commerce.services.Produto.validacoesProduto;

import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
public class ValidacaoPreco implements ValidadorProduto{

    private static final Logger log = LoggerFactory.getLogger(ValidacaoPreco.class);

    @Override
    public void validar(CadastrarProdutoDTO dados) {

        log.info("/Produto - Inciciando validação do Preço");

        if (dados.preco() == null || (dados.preco().compareTo(BigDecimal.ZERO) < 0)) {
            log.info("O preço precisa ser maior que 0!");
            throw new ProdutoException("O preço precisa ser maior que 0!");
        }

        log.info("/Produto - Preço OK!");
    }
}

