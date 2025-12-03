package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.entity.Gallery;
import ajou.artifact.arti_fact.service.GalleryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/galleries")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping("/search")
    public void searchGalleries(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        response.setContentType("application/json; charset=UTF-8");

        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"keyword가 필요합니다.\"}");
            return;
        }

        List<Gallery> results = galleryService.searchGalleries(keyword);

        // JSON 응답 작성
        StringBuilder json = new StringBuilder();
        json.append("{\"message\": \"갤러리 검색 결과 반환\", ")
            .append("\"keyword\": \"").append(keyword).append("\", ")
            .append("\"data\": [");

        for (int i = 0; i < results.size(); i++) {
            Gallery g = results.get(i);

            json.append(String.format(
                    "{\"galleryId\":\"%s\", \"name\":\"%s\", \"address\":\"%s\"}",
                    g.getGalleryId(),
                    g.getName(),
                    g.getAddress()
            ));

            if (i < results.size() - 1) json.append(",");
        }

        json.append("]}");

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(json.toString());
    }
}
