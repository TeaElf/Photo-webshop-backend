package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;
import rs.ac.bg.etf.webphoto.model.InvoiceItem;
import rs.ac.bg.etf.webphoto.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {

    private Long id;

    private String status;

    private LocalDateTime dateOfCreation;

//    private User user;

    private List<InvoiceItem> invoiceItems;

}
