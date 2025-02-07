package matheusmaia.commerce.domain.Venda;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CadastrarVendaDTO(

        @NotNull
        Long idProduto,

        @NotNull
        @Min(1)
        int quantidade,

        @NotNull
        Long idUsuario

) {
}
