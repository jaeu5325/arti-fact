package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.entity.Artist;
import ajou.artifact.arti_fact.service.ArtistService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/search")
    public void searchArtists(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        response.setContentType("application/json; charset=UTF-8");

        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"keyword가 필요합니다.\"}");
            return;
        }

        List<Artist> results = artistService.searchArtists(keyword);

        // 검색 결과 JSON 직접 생성
        StringBuilder json = new StringBuilder();
        json.append("{\"message\": \"검색 성공\", \"count\": ")
            .append(results.size())
            .append(", \"data\": [");

        for (int i = 0; i < results.size(); i++) {
            Artist a = results.get(i);

            json.append(String.format(
                    "{\"artistId\":\"%s\", \"name\":\"%s\", \"theme\":\"%s\", \"nationality\":\"%s\"}",
                    a.getArtistId(),
                    a.getName(),
                    a.getTheme(),
                    a.getNationality()
            ));

            if (i < results.size() - 1) json.append(",");
        }

        json.append("]}");

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(json.toString());
    }
}
