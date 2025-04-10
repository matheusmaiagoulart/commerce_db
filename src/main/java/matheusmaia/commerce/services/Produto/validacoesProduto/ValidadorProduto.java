package matheusmaia.commerce.services.Produto.validacoesProduto;

import matheusmaia.commerce.domain.Produto.CadastrarProdutoDTO;

public interface ValidadorProduto {
    void validar(CadastrarProdutoDTO dados);
}
