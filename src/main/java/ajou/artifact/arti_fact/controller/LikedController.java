package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.dto.LikedDto;
import ajou.artifact.arti_fact.service.LikedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/liked")
@RequiredArgsConstructor
@Tag(name = "Liked", description = "관심 목록 관련 API")
public class LikedController {

    private final LikedService likedService;

    @GetMapping("/{userId}")
    @Operation(summary = "사용자별 관심 목록 조회", description = "특정 사용자의 관심 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "관심 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")
    })
    public ResponseEntity<List<LikedDto.Response>> getLikedListByUser(
            @Parameter(description = "사용자 ID", required = true) @PathVariable Long userId) {
        return ResponseEntity.ok(likedService.getLikedListByUser(userId));
    }

    @PostMapping
    @Operation(summary = "관심 목록에 작품 추가", description = "관심 목록에 새로운 작품을 추가합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "관심 목록 추가 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    public ResponseEntity<LikedDto.Response> addLikedItem(
            @Parameter(description = "관심 목록 추가 요청", required = true) @RequestBody LikedDto.Create request) {
        return ResponseEntity.ok(likedService.addLikedItem(request));
    }

    @DeleteMapping("/{likedId}")
    @Operation(summary = "관심 목록 삭제", description = "관심 목록에서 특정 항목을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "관심 목록 삭제 성공"),
            @ApiResponse(responseCode = "404", description = "관심 목록을 찾을 수 없음")
    })
    public ResponseEntity<Void> removeLikedItem(
            @Parameter(description = "관심 목록 ID", required = true) @PathVariable Long likedId) {
        likedService.removeLikedItem(likedId);
        return ResponseEntity.ok().build();
    }
}