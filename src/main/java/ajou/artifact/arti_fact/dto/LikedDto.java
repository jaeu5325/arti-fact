package ajou.artifact.arti_fact.dto;

import ajou.artifact.arti_fact.entity.Liked;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class LikedDto {

    @Getter
    @Setter
    @Schema(description = "관심 목록 추가 요청 DTO")
    public static class Create {
        @Schema(description = "사용자 ID", example = "1")
        private Long userId;
        @Schema(description = "미술품 ID", example = "1")
        private Long artId;
    }

    @Getter
    @Setter
    @Schema(description = "사용자별 관심 목록 응답 DTO")
    public static class Response {
        @Schema(description = "관심 목록 ID", example = "1")
        private Long id;
        @Schema(description = "미술품 ID", example = "1")
        private Long artId;
        @Schema(description = "미술품 이름", example = "모나리자")
        private String artName;
        @Schema(description = "미술품 이미지 URL", example = "https://example.com/monalisa.jpg")
        private String artImageUrl;

        public static Response from(Liked liked) {
            Response response = new Response();
            response.setId(liked.getId());
            response.setArtId(liked.getArt().getId());
            response.setArtName(liked.getArt().getName());
            // Assuming Art entity has a field for image URL.
            // response.setArtImageUrl(liked.getArt().getImageUrl());
            return response;
        }
    }
}