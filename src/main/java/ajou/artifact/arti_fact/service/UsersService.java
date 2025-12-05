package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.dto.UserDto;
import ajou.artifact.arti_fact.entity.User;
import ajou.artifact.arti_fact.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    // 이메일 중복 체크
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // 회원가입
    @Transactional
    public User registerUser(UserDto.SignUpRequest req) {
        
        if (existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(req.getEmail())
                .name(req.getName())
                .password(req.getPassword())
                .birthDate(req.getBirthDate())
                .build();

        return userRepository.save(user);
    }

    // 로그인
    public User login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    // 마이페이지 조회
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // 회원정보 수정
    public User updateUser(Long userId, String name, String password, LocalDate birthDate) {

        return userRepository.findById(userId).map(user -> {
            if (name != null) user.setName(name);
            if (password != null) user.setPassword(password);
            if (birthDate != null) user.setBirthDate(birthDate);
            return userRepository.save(user);
        }).orElse(null);
    }
}