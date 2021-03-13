package rs.ac.bg.etf.webphoto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.etf.webphoto.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
