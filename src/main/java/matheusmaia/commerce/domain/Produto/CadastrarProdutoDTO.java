package matheusmaia.commerce.domain.Produto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CadastrarProdutoDTO(

        String nomeProduto,
        LocalDate validade,
        BigDecimal preco
) {
}
