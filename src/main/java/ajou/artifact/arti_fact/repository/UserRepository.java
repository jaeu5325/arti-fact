package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);
}