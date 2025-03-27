package matheusmaia.commerce.domain.Transacao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
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
