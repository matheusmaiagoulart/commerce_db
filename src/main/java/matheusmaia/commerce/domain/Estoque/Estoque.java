package matheusmaia.commerce.domain.Estoque;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import matheusmaia.commerce.domain.Produto.Produto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "estoque")
@Entity(name = "Estoque")
public class Estoque {

    @Id
    @Column(name = "id_produto")
    private UUID idProduto;

    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "nome_produto")
    private String nomeProduto;

    @Column(name = "validade")
    private LocalDate validade;

    @Column(name = "preco")
    private BigDecimal preco;

    @OneToOne
    @JsonIgnore  // Ignora a serialização do produto para evitar o loop
    @JoinColumn(name = "id_produto", nullable = false, unique = true)
    private Produto produto;

    @Column(name = "ativo")
    private Boolean ativo;

    public Estoque(CadastrarEstoqueDTO dados, Produto produto){
        this.idProduto = produto.getId();
        this.quantidade = dados.quantidade();
        this.validade = produto.getValidade();
        this.nomeProduto = produto.getNomeProduto();
        this.preco = produto.getPreco();
        this.ativo = true;

    }
}
