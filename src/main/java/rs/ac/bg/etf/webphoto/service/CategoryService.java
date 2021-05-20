package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.Category;
import rs.ac.bg.etf.webphoto.model.dto.CategoryDto;


public interface CategoryService {

    Page<CategoryDto> findAll(Predicate predicate, Pageable pageable);

    CategoryDto findById(Long id);

    Category findOne(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

}
