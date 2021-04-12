package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.dto.InvoiceDto;
import rs.ac.bg.etf.webphoto.repository.InvoiceRepository;
import rs.ac.bg.etf.webphoto.service.InvoiceService;
import rs.ac.bg.etf.webphoto.utils.InvoiceMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    public final InvoiceRepository invoiceRepository;

    public final InvoiceMapper invoiceMapper;

    @Override
    public Page<InvoiceDto> findAll(Predicate predicate, Pageable pageable) {
        Page<Invoice> invoices = invoiceRepository.findAll(predicate, pageable);
        List<InvoiceDto> response = invoices.stream().map(invoice -> invoiceMapper.invoiceToInvoiceDto(invoice)).collect(Collectors.toList());
        return new PageImpl<>(response, pageable, invoices.getTotalElements());
    }

    @Override
    public InvoiceDto findByUserId(Long userId) {
        return invoiceMapper.invoiceToInvoiceDto(invoiceRepository.findByUser_Id(userId).orElseThrow(ResourceNotFoundException::new));
    }
}
