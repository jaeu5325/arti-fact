package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.entity.Gallery;
import ajou.artifact.arti_fact.service.GalleryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/api/galleries")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("/search")
    public ResponseEntity<?> searchGalleries(@RequestParam String keyword) {

        if (keyword == null || keyword.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("{\"message\": \"keyword가 필요합니다.\"}");
        }

        List<GalleryDto.GalleryResponse> results = galleryService.searchGalleries(keyword);

        return ResponseEntity.ok(
                Map.of(
                        "message", "갤러리 검색 결과 반환",
                        "keyword", keyword,
                        "data", results
                )
        );
    }
}
