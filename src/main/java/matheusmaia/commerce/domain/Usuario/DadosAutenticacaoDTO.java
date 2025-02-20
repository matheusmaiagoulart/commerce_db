package matheusmaia.commerce.domain.Usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAutenticacaoDTO(@NotNull String login, @NotNull String senha) {
}
