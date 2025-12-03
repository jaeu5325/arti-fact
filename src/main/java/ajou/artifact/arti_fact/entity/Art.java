package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "art")
@Getter
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "art_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "genre")
    private String genre;

    @Column(name = "theme")
    private String theme;

    @Column(name = "display")
    private Boolean display;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id")
    private Artist artist; // Artist 엔티티가 있다고 가정합니다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_id")
    private Gallery gallery; // Gallery 엔티티가 있다고 가정합니다.

    // 기본 생성자
    public Art() {}
}

// Artist.java와 Gallery.java 파일도 필요합니다.
// 예시:
// @Entity @Getter public class Artist { @Id private Long id; private String name; }
// @Entity @Getter public class Gallery { @Id private Long id; private String name; }