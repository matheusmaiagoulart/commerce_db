package matheusmaia.commerce.repositories;

import matheusmaia.commerce.domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    @Query("""
    select u
    from Usuario u
    where
    u.login = :login
    """)
    Usuario exitsByLogin(@Param("login") String login);

    UserDetails findByLogin(String login);

}
