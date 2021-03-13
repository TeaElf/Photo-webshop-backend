package rs.ac.bg.etf.webphoto.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "photo_details")
public class PhotoDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String size;

    private Double price;

    private String path;

    @ManyToOne(optional = false)
    @JoinColumn(name = "photo_id", nullable = false)
    private Photo photo;

    @OneToMany(mappedBy = "photoDetails")
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

}
