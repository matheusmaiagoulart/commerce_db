package matheusmaia.commerce.domain.Estoque;


import java.time.LocalDate;
import java.util.UUID;

public record DadosListagemEstoqueDTO(UUID id_produto, Integer quantidade, LocalDate validade, String nome_produto) {

    public DadosListagemEstoqueDTO(Estoque estoque){
        this(estoque.getIdProduto(), estoque.getQuantidade(), estoque.getValidade(), estoque.getNomeProduto());

    }
}
