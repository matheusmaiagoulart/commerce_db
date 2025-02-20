package matheusmaia.commerce.domain.Usuario;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
@Table(name = "usuarios")
@Entity(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    private String login;
    private String senha;
    private Boolean ativo;


    public Usuario(String login, String senha)
    {
        this.ativo = true;
    }

}
