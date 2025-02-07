package matheusmaia.commerce.domain.Venda;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "vendas")
@Entity(name = "Venda")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idProduto;
    private int quantidade;
    private LocalDateTime horarioVenda;
    private Long idUsuario;


    public Venda(CadastrarVendaDTO dados){
        this.idProduto = dados.idProduto();
        this.quantidade = dados.quantidade();
        this.idUsuario = dados.idUsuario();
        this.horarioVenda = LocalDateTime.now();
    }
}
