package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "users")
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    // 다른 필드들 (예: name, email 등)
}