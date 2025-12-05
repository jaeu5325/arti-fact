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
import java.util.Optional;
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
    public LikedDto.ToggleResponse toggleLikeStatus(LikedDto.Create request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));

        Art art = artRepository.findById(request.getArtId())
                .orElseThrow(() -> new EntityNotFoundException("Art not found with id: " + request.getArtId()));

        Optional<Liked> existingLiked = likedRepository.findByUser_UserIdAndArt_ArtId(request.getUserId(), request.getArtId());

        if (existingLiked.isPresent()) {
            // '좋아요'가 이미 존재하면 삭제
            likedRepository.delete(existingLiked.get());
            return new LikedDto.ToggleResponse(false, null); // isLiked: false
        } else {
            // '좋아요'가 없으면 새로 생성
            Liked newLiked = new Liked(user, art);
            Liked savedLiked = likedRepository.save(newLiked);
            return new LikedDto.ToggleResponse(true, LikedDto.Response.from(savedLiked)); // isLiked: true
        }
    }

    @Transactional
    public void removeLikedItem(String likedId) {
        if (!likedRepository.existsById(likedId)) {
            throw new EntityNotFoundException("Liked item not found with id: " + likedId);
        }
        likedRepository.deleteById(likedId);
    }
}
