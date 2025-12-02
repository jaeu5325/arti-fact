package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, String> {
    List<Artist> findByNameContainingOrThemeContainingOrNationalityContaining(
            String name, String theme, String nationality
    );
}
