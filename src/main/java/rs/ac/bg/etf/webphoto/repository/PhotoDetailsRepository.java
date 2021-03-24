package rs.ac.bg.etf.webphoto.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.QPhotoDetails;

public interface PhotoDetailsRepository extends JpaRepository<PhotoDetails, Long>, QuerydslPredicateExecutor<PhotoDetails>, QuerydslBinderCustomizer<QPhotoDetails> {

    @Override
    default void customize(QuerydslBindings bindings, QPhotoDetails root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

}