package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
}
