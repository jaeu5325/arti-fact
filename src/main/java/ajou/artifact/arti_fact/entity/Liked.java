package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "liked")
public class Liked {

    @Id
    @Column(name = "liked_id")
    private String likedId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "art_id")
    private Art art;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Liked() {}
}
