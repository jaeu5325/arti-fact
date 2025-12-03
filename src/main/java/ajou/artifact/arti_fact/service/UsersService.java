package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.entity.User;
import ajou.artifact.arti_fact.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public boolean existsById(Long userId) {
        return userRepository.existsById(userId);
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // 로그인 (이메일과 비밀번호로)
    public User login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

    // userId로 사용자 조회 
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // 이메일로 사용자 조회
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // 회원정보 수정
    public User updateUser(Long userId, String name, String password, LocalDate birthDate) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        if (name != null) user.setName(name);
        if (password != null) user.setPassword(password);
        if (birthDate != null) user.setBirthDate(birthDate);

        return userRepository.save(user);
    }

}


