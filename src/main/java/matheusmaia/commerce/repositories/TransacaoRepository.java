package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Transacao.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query("""
            SELECT t
            FROM Transacao t
            WHERE t.dataHora > :dataHoraIntervalo
            """)
    List<Transacao> findByHora(OffsetDateTime dataHoraIntervalo);
}
