package rs.ac.bg.etf.webphoto.controller;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.Category;
import rs.ac.bg.etf.webphoto.model.dto.CategoryDto;
import rs.ac.bg.etf.webphoto.repository.CategoryRepository;
import rs.ac.bg.etf.webphoto.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Page<CategoryDto> findAll(@QuerydslPredicate(root = Category.class, bindings = CategoryRepository.class) Predicate predicate,
                                     Pageable pageable) {
        return categoryService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public CategoryDto findById(@RequestParam Long id) {
        return categoryService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CategoryDto save(@RequestBody CategoryDto categoryDto) {
        return categoryService.save(categoryDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        return categoryService.update(categoryDto);
    }
}
