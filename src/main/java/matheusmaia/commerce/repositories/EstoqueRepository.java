package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Estoque.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID>{
}
