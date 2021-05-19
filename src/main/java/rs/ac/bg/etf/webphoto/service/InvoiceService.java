package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.dto.CartItemDto;
import rs.ac.bg.etf.webphoto.model.dto.InvoiceDto;

import java.security.Principal;
import java.util.List;

public interface InvoiceService {

    Page<InvoiceDto> findAll(Predicate predicate, Pageable pageable);

    // bice iz tokena
    InvoiceDto findByUserId(Long userId);

    Invoice findByOrderId(String orderId);

    Invoice createInvoice(List<CartItemDto> items, Principal principal);

    void update(Invoice invoice);
}
