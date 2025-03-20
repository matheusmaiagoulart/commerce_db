package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Produto.Produto;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

}
