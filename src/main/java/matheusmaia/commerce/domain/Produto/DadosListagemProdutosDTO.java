package matheusmaia.commerce.domain.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record DadosListagemProdutosDTO(UUID id, String nome_produto, LocalDate validade, BigDecimal preco) {


        public DadosListagemProdutosDTO(Produto produto){
            this(produto.getId(), produto.getNomeProduto(), produto.getValidade(), produto.getPreco());
        }
}
