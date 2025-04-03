package matheusmaia.commerce.services.TransacaoPackage.validacoesTransacao;

import matheusmaia.commerce.domain.Transacao.TransacaoDTO;
import matheusmaia.commerce.infra.Exceptions.Estoque.QuantidadeInsuficienteException;
import matheusmaia.commerce.infra.Exceptions.Produto.ProdutoNaoEncontradoException;
import matheusmaia.commerce.repositories.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoEstoque implements ValidadorTransacao
{
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Override
    public void validar(TransacaoDTO dados) {
        System.out.println("Validando estoque");

    var estoque = estoqueRepository.findById(dados.idProduto())
            .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado no estoque!"));

    if(estoque.getQuantidade() < dados.quantidade()){
        throw new QuantidadeInsuficienteException("Estoque Insuficiente! Disponível: " + estoque.getQuantidade() + " Solicitado: " + dados.quantidade());
    }

    }
}
