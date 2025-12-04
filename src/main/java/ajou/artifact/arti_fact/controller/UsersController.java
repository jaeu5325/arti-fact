package ajou.artifact.arti_fact.controller;

import ajou.artifact.arti_fact.entity.User;
import ajou.artifact.arti_fact.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public void registerUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String pw = request.getParameter("pw");
        String birthDateStr = request.getParameter("birthDate");

        // LocalDate 변환
        LocalDate birthDate = null;
        if (birthDateStr != null && !birthDateStr.isEmpty()) {
            birthDate = LocalDate.parse(birthDateStr);
        }

        response.setContentType("application/json; charset=UTF-8");

        // 필수값 체크
        if (userId == null || name == null || pw == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"필수 데이터 누락\"}");
            return;
        }

        // Entity 생성
        User newUser = User.builder()
                .email(userId)  // userId를 email로 사용
                .name(name)
                .password(pw)
                .birthDate(birthDate)
                .build();

        // 중복 회원 체크 (이메일로)
        if (usersService.existsByEmail(newUser.getEmail())) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"이미 존재하는 사용자입니다.\"}");
            return;
        }

        // Service를 통해 DB에 저장
        User savedUser = usersService.registerUser(newUser);

        // 성공 응답
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().write(
                String.format("{\"message\": \"회원가입 성공\", \"userId\": \"%d\"}", savedUser.getUserId())
        );
    }

    // 로그인
    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter("email");
        String pw = request.getParameter("pw");

        response.setContentType("application/json; charset=UTF-8");

        // 필드 누락 처리
        if (email == null || pw == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"message\": \"필수 데이터 누락\"}");
            return;
        }

        // 서비스에서 로그인 검증 (이메일과 비밀번호로)
        User user = usersService.login(email, pw);

        if (user == null) {            
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"로그인 실패\"}");
            return;
        }

        // 로그인 성공
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(
                String.format("{\"message\": \"로그인 성공\", \"userId\": \"%d\"}", user.getUserId())
        );
    }

    // 마이페이지
    @GetMapping("/{userId}")
    public void getUserInfo(@PathVariable Long userId,
                            HttpServletResponse response) throws IOException {

        response.setContentType("application/json; charset=UTF-8");

        User user = usersService.getUserById(userId);

        if (user == null) {        
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"사용자를 찾을 수 없습니다.\"}");
            return;
        }

        // 유저 정보 JSON 생성
        String userJson = String.format(
                "{\"message\": \"사용자 정보 조회 성공\", \"data\": {\"userId\": \"%d\", \"name\": \"%s\", \"email\": \"%s\", \"birthDate\": \"%s\"}}",
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getBirthDate() != null ? user.getBirthDate().toString() : "null"
        );

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(userJson);
    }

    // 회원정보 수정
    @PutMapping("/{userId}")
    public void updateUser(@PathVariable Long userId,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException {

        response.setContentType("application/json; charset=UTF-8");

        String name = request.getParameter("name");
        String pw = request.getParameter("pw");
        String birthDateStr = request.getParameter("birthDate");

        LocalDate birthDate = null;
        if (birthDateStr != null && !birthDateStr.isEmpty()) {
            birthDate = LocalDate.parse(birthDateStr);
        }

        User updated = usersService.updateUser(userId, name, pw, birthDate);

        if (updated == null) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"사용자를 찾을 수 없습니다.\"}");
            return;
        }

        String json = String.format(
                "{\"message\": \"회원정보 수정 성공\", \"userId\": \"%d\"}",
                updated.getUserId()
        );

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(json);
    }

}
