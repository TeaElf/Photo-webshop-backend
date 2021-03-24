package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.dto.CategoryDto;


public interface CategoryService {

    Page<CategoryDto> findAll(Predicate predicate, Pageable pageable);

    CategoryDto findById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

}
