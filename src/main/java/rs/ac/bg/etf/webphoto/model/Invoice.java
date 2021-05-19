package rs.ac.bg.etf.webphoto.model;

import lombok.Data;
import rs.ac.bg.etf.webphoto.model.enums.InvoiceStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //change to enum
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    private LocalDateTime dateOfCreation;

    private String orderId;

    private Double amount;

    private Double tax;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceItem> invoiceItems = new ArrayList<>();
}
