package matheusmaia.commerce.domain.Transacao;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Table(name = "transacoes")
@Entity(name = "Transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_produto")
    private UUID idProduto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_hora")
    private OffsetDateTime dataHora;

    @Column(name = "id_usuario")
    private Long idUsuario;


    public Transacao(TransacaoDTO dados){
        this.idProduto = dados.idProduto();
        this.quantidade = dados.quantidade();
        this.idUsuario = dados.idUsuario();
        this.valor = dados.valor();
        this.dataHora = dados.dataHora();
    }
    public Transacao(){

    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
