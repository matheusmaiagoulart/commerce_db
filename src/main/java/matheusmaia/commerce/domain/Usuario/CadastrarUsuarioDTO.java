package matheusmaia.commerce.domain.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastrarUsuarioDTO(
        @NotNull
        @Size(min = 5)
        String login,

        @NotNull
        @Size(min = 4)
        String senha) {
}
