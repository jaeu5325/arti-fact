package ajou.artifact.arti_fact.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gallery")
public class Gallery {

    @Id
    @Column(name = "gallery_id")
    private String galleryId;

    private String name;
    private String address;

    @Column(name = "open_time")
    private String openTime;

    @Column(name = "closed_time")
    private String closedTime;

    private int fee;

    public Gallery() {}
}
