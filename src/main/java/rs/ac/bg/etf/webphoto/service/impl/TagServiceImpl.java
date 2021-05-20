package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
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
        return tagMapper.tagToTagDto(tagRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Tag> findOrCreate(List<String> names) {
        List<Tag> response = names.stream().map(name -> {
            if (tagRepository.existsByName(name)) {
                return tagRepository.findByName(name);
            } else {
                Tag tag = new Tag();
                tag.setName(name);
                return tagRepository.save(tag);
            }
        }).collect(Collectors.toList());
        return response;
    }

    @Override
    public TagDto save(TagDto tagDto) {
        Tag tag = tagMapper.tagDtoToTag(tagDto);
        return tagMapper.tagToTagDto(tagRepository.save(tag));
    }

    @Override
    public List<TagDto> saveAll(List<TagDto> tagDtoList) {
        List<Tag> tags = tagDtoList.stream().map(tag -> tagMapper.tagDtoToTag(tag)).collect(Collectors.toList());
        tagRepository.saveAll(tags);
        List<TagDto> response = tags.stream().map(tag -> tagMapper.tagToTagDto(tag)).collect(Collectors.toList());
        return response;
    }

    @Override
    public TagDto update(Long id, TagDto tagDto) {
        Tag tag = tagRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        tag.setName(tagDto.getName());
        return tagMapper.tagToTagDto(tagRepository.save(tag));
    }
}
