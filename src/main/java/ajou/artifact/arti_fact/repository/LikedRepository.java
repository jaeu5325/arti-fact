package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Liked;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface LikedRepository extends JpaRepository<Liked, Long> {
    List<Liked> findByUser_UserId(Long userId);
    Optional<Liked> findByUser_UserIdAndArt_ArtId(Long userId, String artId);
    void deleteByUser_UserIdAndArt_ArtId(Long userId, String artId);
}
