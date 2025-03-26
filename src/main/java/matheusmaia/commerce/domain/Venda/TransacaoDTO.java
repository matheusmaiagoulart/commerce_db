package matheusmaia.commerce.domain.Venda;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public record TransacaoDTO(

        @NotNull
        UUID idProduto,

        @NotNull
        @Min(1)
        Integer quantidade,

        @NotNull
        Long idUsuario,

        BigDecimal valor,

        OffsetDateTime dataHora

) {

//        public TransacaoDTO(String idProduto, Integer quantidade, Long idUsuario, BigDecimal valor, OffsetDateTime dataHora) {
//                this(UUID.fromString(idProduto), quantidade, idUsuario, valor, dataHora);
//        }
}
