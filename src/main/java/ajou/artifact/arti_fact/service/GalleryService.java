package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.entity.Gallery;
import ajou.artifact.arti_fact.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public List<Gallery> searchGalleries(String keyword) {
        return galleryRepository.findByNameContainingOrAddressContaining(keyword, keyword);
    }
}
