package matheusmaia.commerce.domain.Produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record editarProdutoDTO(

        String nome_produto,
        @Future(message = "A data de validade precisa ser maior que a Data de hoje")
        LocalDate validade,
        @DecimalMin(value = "0.01", message = "O preço precisa ser maior que 0!")
        BigDecimal preco,
        Boolean ativo) {
}
