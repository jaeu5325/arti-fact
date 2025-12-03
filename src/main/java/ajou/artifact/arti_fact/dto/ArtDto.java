package ajou.artifact.arti_fact.dto;

import ajou.artifact.arti_fact.entity.Art;
import lombok.Getter;
import lombok.Setter;
 
public class ArtDto {
 
    @Getter
    @Setter
    public static class Response {
        private Long artId;
        private String name;
        private String age;
        private String genre;
        private String theme;
        private Boolean display;
        private String artistName;
        private String galleryName;
 
        public static Response from(Art art) {
            Response response = new Response();
            response.artId = art.getId();
            response.name = art.getName();
            response.age = art.getAge();
            response.genre = art.getGenre();
            response.theme = art.getTheme();
            response.display = art.getDisplay();
            if (art.getArtist() != null) {
                response.artistName = art.getArtist().getName();
            }
            if (art.getGallery() != null) {
                response.galleryName = art.getGallery().getName();
            }
            return response;
        }
    }
}
