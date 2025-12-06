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

    public List<GalleryDto.GalleryResponse> searchGalleries(String keyword) {

        List<Gallery> list = galleryRepository
                .findByNameContainingIgnoreCaseOrAddressContainingIgnoreCase(keyword, keyword);

        return list.stream()
                .map(g -> GalleryDto.GalleryResponse.builder()
                        .galleryId(g.getGalleryId())
                        .name(g.getName())
                        .address(g.getAddress())
                        .openTime(g.getOpenTime())
                        .closedTime(g.getClosedTime())
                        .fee(g.getFee())
                        .phone(g.getPhone())
                        .build())
                .toList();
    }
}
