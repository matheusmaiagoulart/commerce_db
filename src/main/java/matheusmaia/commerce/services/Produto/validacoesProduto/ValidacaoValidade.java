package matheusmaia.commerce.services.Produto.validacoesProduto;

import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
@Component
public class ValidacaoValidade implements ValidadorProduto{
    private static final Logger log = LoggerFactory.getLogger(ValidacaoValidade.class);

    @Override
    public void validar(CadastrarProdutoDTO dados) {
        log.info("/Produto - Inciciando validação da Data de Validade");

        if (dados.validade().isBefore(LocalDate.now())) {
            log.info("/Produto - A data de validade precisa ser maior que a Data de hoje! Exception");
            throw new ProdutoException("A data de validade precisa ser maior que a Data de hoje");
        }
        log.info("/Produto - Data de validade OK!");
    }
}
