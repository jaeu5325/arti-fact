package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.entity.User;
import ajou.artifact.arti_fact.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public User registerUser(User user) {
        return usersRepository.save(user);
    }

    public boolean existsById(Long userId) {
        return usersRepository.existsById(userId);
    }

    public boolean existsByEmail(String email) {
        return usersRepository.findByEmail(email).isPresent();
    }

    // 로그인 (이메일과 비밀번호로)
    public User login(String email, String password) {
        Optional<User> userOpt = usersRepository.findByEmail(email);
        return userOpt
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

    // userId로 사용자 조회 
    public User getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    // 이메일로 사용자 조회
    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email).orElse(null);
    }

    // 회원정보 수정
    public User updateUser(Long userId, String name, String password, LocalDate birthDate) {
        User user = usersRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        if (name != null) user.setName(name);
        if (password != null) user.setPassword(password);
        if (birthDate != null) user.setBirthDate(birthDate);

        return usersRepository.save(user);
    }

}


