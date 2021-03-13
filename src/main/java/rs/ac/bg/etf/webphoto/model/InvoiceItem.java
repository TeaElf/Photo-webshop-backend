package rs.ac.bg.etf.webphoto.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoice_item")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "photo_details_id")
    private PhotoDetails photoDetails;
}
