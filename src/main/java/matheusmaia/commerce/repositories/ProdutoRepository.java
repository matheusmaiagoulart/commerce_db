package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Produto.Produto;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    

    Collection<Produto> findAllByAtivoTrue();

    Optional<Produto> findById(java.util.UUID id);
}
