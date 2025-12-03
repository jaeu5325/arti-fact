package ajou.artifact.arti_fact.repository;

import ajou.artifact.arti_fact.entity.Art;
import ajou.artifact.arti_fact.entity.Artist;
import ajou.artifact.arti_fact.entity.Gallery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ArtSpecification {

    public static Specification<Art> search(String name, String genre, String theme, String artistName, String galleryName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
            }
            if (genre != null && !genre.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("genre"), "%" + genre + "%"));
            }
            if (theme != null && !theme.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("theme"), "%" + theme + "%"));
            }
            if (artistName != null && !artistName.isEmpty()) {
                Join<Art, Artist> artistJoin = root.join("artist");
                predicates.add(criteriaBuilder.like(artistJoin.get("name"), "%" + artistName + "%"));
            }
            if (galleryName != null && !galleryName.isEmpty()) {
                Join<Art, Gallery> galleryJoin = root.join("gallery");
                predicates.add(criteriaBuilder.like(galleryJoin.get("name"), "%" + galleryName + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

