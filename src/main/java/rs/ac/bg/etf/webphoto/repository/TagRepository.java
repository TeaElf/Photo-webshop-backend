package rs.ac.bg.etf.webphoto.repository;

import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import rs.ac.bg.etf.webphoto.model.QTag;
import rs.ac.bg.etf.webphoto.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>, QuerydslPredicateExecutor<Tag>, QuerydslBinderCustomizer<QTag> {

    @Override
    default void customize(QuerydslBindings bindings, QTag root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
