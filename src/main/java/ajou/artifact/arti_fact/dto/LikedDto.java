package ajou.artifact.arti_fact.dto;

import ajou.artifact.arti_fact.entity.Liked;
import lombok.*;

public class LikedDto {

    // 좋아요 추가 요청 시 사용
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Create {
        private Long userId;
        private String artId;
    }

    // 좋아요 추가/삭제 요청 시 사용
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String artId; // 어떤 작품을 좋아요 할 것인지
    }

    // 관심 목록 조회 시 사용
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private String likedId;      // 좋아요 취소(삭제)를 위해 필요한 PK
        
        private String artId;     
        private String artName;    
        private String imageUrl;  
        private String artistName; 
        private String galleryName;

        public static Response from(Liked liked) {
            return Response.builder()
                    .likedId(liked.getLikedId())
                    .artId(liked.getArt().getArtId())
                    .artName(liked.getArt().getName())
                    .imageUrl(liked.getArt().getImageUrl())
                    .artistName(liked.getArt().getArtist() != null ? liked.getArt().getArtist().getName() : null)
                    .galleryName(liked.getArt().getGallery() != null ? liked.getArt().getGallery().getName() : null)
                    .build();
        }
    }

    // 좋아요 토글 응답 시 사용
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ToggleResponse {
        private boolean isLiked;
        private Response likedItem;
    }
}