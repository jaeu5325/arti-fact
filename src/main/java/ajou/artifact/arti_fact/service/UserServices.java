package ajou.artifact.arti_fact.service;

import org.springframework.stereotype.Service;
import ajou.artifact.arti_fact.reposiroty.UserRepository;
import ajou.artifact.arti_fact.entity.Users;

@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users getUserById(String userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
