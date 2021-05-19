package rs.ac.bg.etf.webphoto.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import rs.ac.bg.etf.webphoto.model.Invoice;
import rs.ac.bg.etf.webphoto.model.QInvoice;

import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>, QuerydslPredicateExecutor<Invoice>, QuerydslBinderCustomizer<QInvoice> {

    Optional<Invoice> findByUser_Id(Long userId);

    Optional<Invoice> findByOrderId(String orderId);

    @Override
    default void customize(QuerydslBindings bindings, QInvoice root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

}
