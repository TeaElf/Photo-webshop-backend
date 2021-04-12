package rs.ac.bg.etf.webphoto.utils;

import org.mapstruct.Mapper;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.dto.InvoiceDto;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceDto invoiceToInvoiceDto(Invoice invoice);

    Invoice invoiceDtoToInvoice(InvoiceDto invoiceDto);
}
