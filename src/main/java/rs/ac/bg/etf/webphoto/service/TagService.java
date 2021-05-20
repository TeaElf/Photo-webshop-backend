package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.Tag;
import rs.ac.bg.etf.webphoto.model.dto.TagDto;

import java.util.List;

public interface TagService {

    Page<TagDto> findAll(Predicate predicate, Pageable pageable);

    TagDto findById(Long id);

    List<Tag> findOrCreate(List<String> names);

    TagDto save(TagDto tagDto);

    List<TagDto> saveAll(List<TagDto> tagDtoList);

    TagDto update(Long id, TagDto tagDto);
}
