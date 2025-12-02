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
public class Users {

    @Id
    @Column(name = "user_id")
    private String userId;

    private String name;

    private String pw;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // 기본 생성자
    public Users() {}
}
