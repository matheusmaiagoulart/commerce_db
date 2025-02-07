package matheusmaia.commerce.domain.Produto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Table(name = "produtos")
@Entity(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nomeProduto;
    private LocalDate validade;
    private BigDecimal preco;

    public Produto(CadastrarProdutoDTO dados){
        this.nomeProduto = dados.nomeProduto();
        this.validade = dados.validade();
        this.preco = dados.preco();
    }
}
