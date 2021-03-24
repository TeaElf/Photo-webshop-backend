package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.model.Tag;
import rs.ac.bg.etf.webphoto.model.dto.TagDto;
import rs.ac.bg.etf.webphoto.repository.TagRepository;
import rs.ac.bg.etf.webphoto.service.TagService;
import rs.ac.bg.etf.webphoto.utils.TagMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    @Override
    public Page<TagDto> findAll(Predicate predicate, Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(predicate, pageable);
        List<TagDto> responseDtoList = tags.stream().map(tag -> tagMapper.tagToTagDto(tag)).collect(Collectors.toList());
        return new PageImpl<>(responseDtoList, pageable, tags.getTotalElements());
    }

    @Override
    public TagDto findById(Long id) {
        //add exception
        return tagMapper.tagToTagDto(tagRepository.findById(id).get());
    }

    @Override
    public TagDto save(TagDto tagDto) {
        Tag tag = tagMapper.tagDtoToTag(tagDto);
        return tagMapper.tagToTagDto(tagRepository.save(tag));
    }

    @Override
    public TagDto update(TagDto tagDto) {
        Tag tag = tagRepository.findById(tagDto.getId()).get();
        tag.setName(tagDto.getName());
        tag.setPhotos(tagDto.getPhotos());
        return tagMapper.tagToTagDto(tagRepository.save(tag));
    }
}
