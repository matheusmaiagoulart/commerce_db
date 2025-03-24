package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID>{

    Optional<Estoque> findById(UUID id);
    Collection<Estoque> findAllByAtivoTrue();
}
