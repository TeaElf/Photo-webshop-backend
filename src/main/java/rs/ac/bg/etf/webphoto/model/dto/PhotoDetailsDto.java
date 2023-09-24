package rs.ac.bg.etf.webphoto.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PhotoDetailsDto {

    private Long id;

    private String size;

    private Double price;

    private String path;

    private Long photoId;

//    private List<InvoiceItem> invoiceItems = new ArrayList<>(); //obrisati - kroz invoice photo


}
