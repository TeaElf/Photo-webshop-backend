package rs.ac.bg.etf.webphoto.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.MultiValueBinding;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import rs.ac.bg.etf.webphoto.model.Photo;
import rs.ac.bg.etf.webphoto.model.QPhoto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Long>, QuerydslPredicateExecutor<Photo>, QuerydslBinderCustomizer<QPhoto> {

    List<Photo> findByIdIn(List<Long> ids);

    @Override
    default void customize(QuerydslBindings bindings, QPhoto root) {
//        bindings.bind(String.class)
//                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

//        StringPath[] multiPropertySearchPaths = new StringPath[] {root.title, root.category.name, root.description};
//
///**
// * Binds prop1, prop2 and prop3 in OR clause
// * This binding will activate when one of the given properties are searched in query params
// */
//        bindings.bind(multiPropertySearchPaths).all(new MultiValueBinding<StringPath, String>() {
//            @Override
//            public Optional<Predicate> bind(StringPath path, Collection<? extends String> values) {
//                BooleanBuilder predicate = new BooleanBuilder();
//                // Bind paths present in array multiPropertySearchPaths with incoming values
//                for (StringPath propertyPath : multiPropertySearchPaths) {
//                    values.forEach(value -> predicate.or(propertyPath.containsIgnoreCase(value)));
//                }
//                return Optional.of(predicate);
//            }
//        });

//        bindings.bind(root.title)
//                .first((path, value) -> path.containsIgnoreCase(value).or(root.description.containsIgnoreCase(value)).or(root.category.name.containsIgnoreCase(value)));


        bindings.bind(String.class).all(new MultiValueBinding<StringPath, String>() {
            @Override
            public Optional<Predicate> bind(StringPath path, Collection<? extends String> values) {
                boolean first = true;
                BooleanBuilder orPredicate = new BooleanBuilder();
                values.forEach(value -> orPredicate.or(path.containsIgnoreCase(value)));

                if (first) {
                    first = false;
                    return Optional.of(orPredicate);
                } else {
                    return Optional.empty();
                }
            }
        });

    }

}
