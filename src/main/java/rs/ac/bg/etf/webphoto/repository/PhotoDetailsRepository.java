package rs.ac.bg.etf.webphoto.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.QPhotoDetails;

import java.util.List;

public interface PhotoDetailsRepository extends JpaRepository<PhotoDetails, Long>, QuerydslPredicateExecutor<PhotoDetails>, QuerydslBinderCustomizer<QPhotoDetails> {

    List<PhotoDetails> findByPhoto_Id(Long photoId);

    @Transactional
    void deleteByIdIn(List<Long> ids);

    @Override
    default void customize(QuerydslBindings bindings, QPhotoDetails root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

}
