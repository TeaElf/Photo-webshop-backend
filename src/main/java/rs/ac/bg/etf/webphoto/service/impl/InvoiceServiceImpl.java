package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.InvoiceItem;
import rs.ac.bg.etf.webphoto.model.User;
import rs.ac.bg.etf.webphoto.model.dto.CartItemDto;
import rs.ac.bg.etf.webphoto.model.dto.InvoiceDto;
import rs.ac.bg.etf.webphoto.model.enums.InvoiceStatus;
import rs.ac.bg.etf.webphoto.repository.InvoiceItemRepository;
import rs.ac.bg.etf.webphoto.repository.InvoiceRepository;
import rs.ac.bg.etf.webphoto.service.InvoiceService;
import rs.ac.bg.etf.webphoto.service.PhotoDetailsService;
import rs.ac.bg.etf.webphoto.service.UserService;
import rs.ac.bg.etf.webphoto.utils.InvoiceMapper;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final Double TAX_VALUE = 0.2;

    private final UserService userService;

    private final InvoiceMapper invoiceMapper;

    private final InvoiceRepository invoiceRepository;

    private final PhotoDetailsService photoDetailsService;

    private final InvoiceItemRepository invoiceItemRepository;

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

    @Override
    public Invoice findByOrderId(String orderId) {
        return invoiceRepository.findByOrderId(orderId).orElseThrow();
    }

    @Override
    public Invoice createInvoice(List<CartItemDto> items, Principal principal) {
        Invoice invoice = new Invoice();
        User user = userService.findByUsername(principal.getName());
        invoice.setUser(user);
        invoice.setDateOfCreation(LocalDateTime.now());
        invoice.setStatus(InvoiceStatus.IN_PROGRESS);
        invoiceRepository.save(invoice);

        double amount = 0;
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        for (CartItemDto cartItem : items) {
            InvoiceItem item = new InvoiceItem();
            var photo = photoDetailsService.findOne(cartItem.getPhotoDetailsId());
            item.setInvoice(invoice);
            item.setPhotoDetails(photo);
            item.setPrice(photo.getPrice());
            amount += photo.getPrice();
            invoiceItems.add(item);
        }

        invoiceItemRepository.saveAll(invoiceItems);

        double taxValue = amount * TAX_VALUE;
        double amountWithoutTax = amount - taxValue;
        invoice.setTax(taxValue);
        invoice.setAmount(amountWithoutTax);
        invoiceRepository.save(invoice);

        invoice.setInvoiceItems(invoiceItems);
        return invoice;
    }

    @Override
    public void update(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
