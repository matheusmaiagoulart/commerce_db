package matheusmaia.commerce.domain.Estoque;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Table(name = "estoque")
@Entity(name = "Estoque")
public class Estoque {

    @Id
    private String idProduto;

    private int quantidade;
    private String nomeProduto;
    private LocalDate validade;


    public Estoque(CadastrarEstoqueDTO dados){
        this.idProduto = dados.idProduto();
        this.quantidade = dados.quantidade();
        this.nomeProduto = dados.nomeProduto();
        this.validade = dados.validade(); //passar o valor da validade na classe service a partir do id do produto
    }
}
