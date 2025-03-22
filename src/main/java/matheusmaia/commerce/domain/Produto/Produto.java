package matheusmaia.commerce.domain.Produto;

import lombok.Setter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import matheusmaia.commerce.domain.Estoque.Estoque;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Setter
@NoArgsConstructor
@Getter
@Table(name = "produtos")
@Entity(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome_produto")
    private String nomeProduto;
    @Column(name = "validade")
    private LocalDate validade;
    @Column(name = "preco")
    private BigDecimal preco;

    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Estoque estoque;

    public Produto(CadastrarProdutoDTO dados){
        this.nomeProduto = dados.nomeProduto();
        this.validade = dados.validade();
        this.preco = dados.preco();
    }
}
