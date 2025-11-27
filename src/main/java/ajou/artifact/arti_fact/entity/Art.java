package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "art")
public class Art {

    @Id
    @Column(name = "art_id")
    private String artId;

    private String name;
    private String age;
    private String genre;
    private String theme;
    private Boolean display;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    public Art() {}
}
