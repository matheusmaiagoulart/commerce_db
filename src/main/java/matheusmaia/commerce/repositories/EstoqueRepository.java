package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID>{

    Optional<Estoque> findById(UUID id);

    @Query("""
            select e from Estoque e where e.id = :id
            """)
    Estoque findEstoqueById(UUID id);

    Collection<Estoque> findAllByAtivoTrue();
}
