package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, String> {

    List<Gallery> findByNameContainingOrAddressContaining(String name, String address);
}
