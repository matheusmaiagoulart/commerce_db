package matheusmaia.commerce.services.TransacaoPackage.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoProduto implements ValidadorTransacao{
    @Autowired
    private ProdutoRepository produtoRepository;
    @Override
    public void validar(TransacaoDTO dados) {
        //Verifica a existencia do Produto no banco
        var produto = produtoRepository.findById(dados.idProduto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado na base de dados! Transação não pode ser realizada!"));

        System.out.println("Produto validado" + produto.getNomeProduto());
    }
}
