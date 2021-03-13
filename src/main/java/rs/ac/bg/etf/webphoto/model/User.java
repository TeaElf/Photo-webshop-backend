package rs.ac.bg.etf.webphoto.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String email;

    private String country;

    private LocalDateTime dateOfCreation;

    private String avatar;

    @OneToMany(mappedBy = "user")
    private List<Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices = new ArrayList<>();


}
