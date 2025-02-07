package matheusmaia.commerce.domain.Estoque;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastrarEstoqueDTO(

        @NotNull
        String idProduto,
        @NotNull
        int quantidade,
        @NotNull
        String nomeProduto,

        LocalDate validade

) {
}
