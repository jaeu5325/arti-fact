package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.entity.Users;
import ajou.artifact.arti_fact.repository.UsersRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public Users registerUser(Users user) {
        return usersRepository.save(user);
    }

    public boolean existsById(String userId) {
        return usersRepository.existsById(userId);
    }

    // 로그인
    public Users login(String userId, String pw) {
        return usersRepository.findById(userId)
                .filter(user -> user.getPw().equals(pw))
                .orElse(null);
    }

    // 사용자 조회 (마이페이지)
    public Users getUserById(String userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    // 회원정보 수정
    public Users updateUser(String userId, String name, String pw, LocalDate birthDate) {

        Users user = usersRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        if (name != null) user.setName(name);
        if (pw != null) user.setPw(pw);
        if (birthDate != null) user.setBirthDate(birthDate);

        return usersRepository.save(user);
    }

}


