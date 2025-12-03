package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL의 Auto Increment
    @Column(name = "User_ID")
    private Long userId;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "PW", nullable = false)
    private String password;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "BirthDate")
    private LocalDate birthDate;

    // User(1) -> Liked(N) -> Art(1)
    // cascade = CascadeType.ALL: 회원이 탈퇴하면 좋아요 목록도 같이 삭제됨
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Liked> likedList = new ArrayList<>();
}