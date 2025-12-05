package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.dto.UserDto;
import ajou.artifact.arti_fact.entity.User;
import ajou.artifact.arti_fact.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "회원 관련 API")
public class UsersController {

    private final UsersService usersService;

    // 회원가입
    @Operation(summary = "회원가입 API")
    @PostMapping
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody UserDto.SignUpRequest request
    ) {
        try {
            // 이메일 중복 체크
            if (usersService.existsByEmail(request.getEmail())) {
                return ResponseEntity.badRequest().body(
                        "{\"message\": \"이미 존재하는 사용자입니다.\"}"
                );
            }

            // User 엔터티 생성 및 저장
            User created = usersService.registerUser(request);

            return ResponseEntity.status(201).body(
                    new UserDto.UserResponse(
                            created.getUserId(),
                            created.getEmail(),
                            created.getName(),
                            created.getBirthDate()
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    "{\"message\": \"" + e.getMessage() + "\"}"
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    "{\"message\": \"회원가입 중 오류가 발생했습니다: " + e.getMessage() + "\"}"
            );
        }
    }

    // 로그인
    @Operation(summary = "로그인 API")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDto.LoginRequest request) {

        User user = usersService.login(request.getEmail(), request.getPassword());

        if (user == null) {
            return ResponseEntity.ok("{\"message\": \"로그인 실패\"}");
        }

        return ResponseEntity.ok("{\"message\": \"로그인 성공\", \"userId\": \"" + user.getUserId() + "\"}");
    }

    // 마이페이지 조회
    @Operation(summary = "마이페이지 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable Long userId) {

        User user = usersService.getUserById(userId);

        if (user == null) {
            return ResponseEntity.ok("{\"message\": \"사용자를 찾을 수 없습니다.\"}");
        }

        return ResponseEntity.ok(
                new UserDto.UserResponse(
                        user.getUserId(),
                        user.getEmail(),
                        user.getName(),
                        user.getBirthDate()
                )
        );
    }

    // 회원 정보 수정
    @Operation(summary = "회원 정보 수정")
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserDto.SignUpRequest request
    ) {

        User updated = usersService.updateUser(
                userId,
                request.getName(),
                request.getPassword(),
                request.getBirthDate()
        );

        if (updated == null) {
            return ResponseEntity.ok("{\"message\": \"사용자를 찾을 수 없습니다.\"}");
        }

        return ResponseEntity.ok("{\"message\": \"회원정보 수정 성공\"}");
    }
}
