package matheusmaia.commerce.domain.Produto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "ativo")
    private Boolean ativo;

    @JsonManagedReference
    @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private Estoque estoque;

    public Produto(CadastrarProdutoDTO dados){
        this.nomeProduto = dados.nomeProduto();
        this.validade = dados.validade();
        this.preco = dados.preco();
        this.ativo = true;
    }

    public Produto(){

    }

    public UUID getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
