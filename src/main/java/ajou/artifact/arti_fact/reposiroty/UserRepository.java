package ajou.artifact.arti_fact.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ajou.artifact.arti_fact.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    
}
