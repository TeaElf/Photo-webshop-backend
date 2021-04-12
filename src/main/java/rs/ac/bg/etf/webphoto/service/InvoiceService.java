package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.dto.InvoiceDto;

public interface InvoiceService {

    Page<InvoiceDto> findAll(Predicate predicate, Pageable pageable);

    // bice iz tokena
    InvoiceDto findByUserId(Long userId);
}
