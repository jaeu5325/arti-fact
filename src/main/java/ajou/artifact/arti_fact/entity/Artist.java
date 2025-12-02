package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @Column(name = "artist_id")
    private String artistId;

    private String name;
    private String theme;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "dead_date")
    private String deadDate;

    private String nationality;

    private String info;

    public Artist() {}
}
