package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Venda.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
