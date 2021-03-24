package rs.ac.bg.etf.webphoto.utils;

import org.mapstruct.Mapper;
import rs.ac.bg.etf.webphoto.model.Category;
import rs.ac.bg.etf.webphoto.model.dto.CategoryDto;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto categoryToCategoryDto(Category category);

    Category categoryDtoToCategory(CategoryDto categoryDto);
}
