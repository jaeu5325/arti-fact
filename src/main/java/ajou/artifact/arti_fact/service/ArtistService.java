package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.entity.Artist;
import ajou.artifact.arti_fact.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistRepository artistRepository;

    public List<Artist> searchArtists(String keyword) {
        return artistRepository.findByNameContainingOrThemeContainingOrNationalityContaining(
                keyword, keyword, keyword
        );
    }
}

