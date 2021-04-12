package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
import rs.ac.bg.etf.webphoto.model.Category;
import rs.ac.bg.etf.webphoto.model.dto.CategoryDto;
import rs.ac.bg.etf.webphoto.repository.CategoryRepository;
import rs.ac.bg.etf.webphoto.service.CategoryService;
import rs.ac.bg.etf.webphoto.utils.CategoryMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    @Override
    public Page<CategoryDto> findAll(Predicate predicate, Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(predicate, pageable);
        List<CategoryDto> response = categories.stream().map(category -> categoryMapper.categoryToCategoryDto(category)).collect(Collectors.toList());
        return new PageImpl<>(response, pageable, categories.getTotalElements());
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryMapper.categoryToCategoryDto(findOne(id));
    }

    @Override
    public Category findOne(Long id) {
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryMapper.categoryDtoToCategory(categoryDto);
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        Category category = findOne(categoryDto.getId());
        category.setName(categoryDto.getName());
        return categoryMapper.categoryToCategoryDto(categoryRepository.save(category));
    }
}
