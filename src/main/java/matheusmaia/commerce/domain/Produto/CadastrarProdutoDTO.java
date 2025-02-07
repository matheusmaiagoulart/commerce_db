package matheusmaia.commerce.domain.Produto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastrarProdutoDTO(

        @NotNull
        String nomeProduto,

        @NotNull
        @Min(1)
        BigDecimal preco,
        @NotNull
        LocalDate validade) {
}
