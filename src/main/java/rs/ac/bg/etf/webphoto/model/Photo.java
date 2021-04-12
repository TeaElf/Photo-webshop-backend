package rs.ac.bg.etf.webphoto.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String path;

    private String description;

    private String orientation;

    // count koliko puta nesto kupljeno
    private Long timesBought = 0L;

    // za home page da bi se izvuklo koje su najnovije fotke
    private LocalDateTime dateOfCreation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "photo")
    private List<PhotoDetails> photoDetails = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "photo_tag", joinColumns = {@JoinColumn(name = "photo_id")}, inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags = new ArrayList<>();
}
