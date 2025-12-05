package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "LIKED")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Liked {

    @Id
    @Column(name = "Liked_ID")
    private String likedId;

    // User와의 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_ID", nullable = false)
    private User user;

    // Art와의 관계 (N:1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Art_ID", nullable = false)
    private Art art;

    // User와 Art를 받는 생성자
    public Liked(User user, Art art) {
        this.likedId = UUID.randomUUID().toString();
        this.user = user;
        this.art = art;
    }
}