package ajou.artifact.arti_fact.dto;

import ajou.artifact.arti_fact.entity.Art;
import lombok.*;

public class ArtDto {

    // 미술품 상세/목록 응답 DTO
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ArtResponse {
        private String artId;
        private String name;      
        private String imageUrl;  
        
        private String artistId;
        private String artistName;
        
        private String galleryId;
        private String galleryName;

        private Boolean display;
        private String genre;
        private String theme;
        private Integer age;       
    }

    // Response 클래스 (ArtResponse와 동일하지만 from 메서드를 위한 별칭)
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String artId;
        private String name;      
        private String imageUrl;  
        
        private String artistId;
        private String artistName;
        
        private String galleryId;
        private String galleryName;

        private Boolean display;
        private String genre;
        private String theme;
        private Integer age;

        public static Response from(Art art) {
            return Response.builder()
                    .artId(art.getArtId())
                    .name(art.getName())
                    .imageUrl(art.getImageUrl())
                    .artistId(art.getArtist() != null ? art.getArtist().getArtistId() : null)
                    .artistName(art.getArtist() != null ? art.getArtist().getName() : null)
                    .galleryId(art.getGallery() != null ? art.getGallery().getGalleryId() : null)
                    .galleryName(art.getGallery() != null ? art.getGallery().getName() : null)
                    .display(art.getDisplay())
                    .genre(art.getGenre())
                    .theme(art.getTheme())
                    .age(art.getAge())
                    .build();
        }
    }
    
}