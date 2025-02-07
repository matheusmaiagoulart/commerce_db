package matheusmaia.commerce.domain.Usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String senha;
    private Boolean ativo;


    public Usuario(CadastrarUsuarioDTO dados)
    {
        this.login = dados.login();
        this.senha = dados.senha();
        this.ativo = true;
    }

}
