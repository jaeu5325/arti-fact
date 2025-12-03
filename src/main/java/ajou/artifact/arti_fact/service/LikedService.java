package ajou.artifact.arti_fact.service;

import ajou.artifact.arti_fact.dto.LikedDto;
import ajou.artifact.arti_fact.entity.Art;
import ajou.artifact.arti_fact.entity.Liked;
import ajou.artifact.arti_fact.entity.User;
import ajou.artifact.arti_fact.repository.ArtRepository;
import ajou.artifact.arti_fact.repository.LikedRepository;
import ajou.artifact.arti_fact.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikedService {

    private final LikedRepository likedRepository;
    private final UserRepository userRepository;
    private final ArtRepository artRepository;

    public List<LikedDto.Response> getLikedListByUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        return likedRepository.findByUser_UserId(userId).stream()
                .map(LikedDto.Response::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public LikedDto.Response addLikedItem(LikedDto.Create request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));

        Art art = artRepository.findById(request.getArtId())
                .orElseThrow(() -> new EntityNotFoundException("Art not found with id: " + request.getArtId()));

        // 이미 '좋아요'를 눌렀는지 확인
        if (likedRepository.findByUser_UserIdAndArt_ArtId(request.getUserId(), request.getArtId()).isPresent()) {
            throw new IllegalStateException("This art is already in the liked list.");
        }

        Liked newLiked = new Liked(user, art);
        Liked savedLiked = likedRepository.save(newLiked);

        return LikedDto.Response.from(savedLiked);
    }

    @Transactional
    public void removeLikedItem(Long likedId) {
        if (!likedRepository.existsById(likedId)) {
            throw new EntityNotFoundException("Liked item not found with id: " + likedId);
        }
        likedRepository.deleteById(likedId);
    }
}
