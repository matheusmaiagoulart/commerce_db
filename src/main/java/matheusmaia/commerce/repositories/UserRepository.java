package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Object findByLogin(String login);
}
