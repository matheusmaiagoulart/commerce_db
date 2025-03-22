package matheusmaia.commerce.domain.Estoque;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CadastrarEstoqueDTO(


        @NotNull(message = "O ID não pode ser Nullo!")
                @Min(value = 1, message = "A quantidade não pode ser menor que 1 Unidade!")
        int quantidade


) {
}
