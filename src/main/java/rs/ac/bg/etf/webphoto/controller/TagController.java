package rs.ac.bg.etf.webphoto.controller;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.Tag;
import rs.ac.bg.etf.webphoto.model.dto.TagDto;
import rs.ac.bg.etf.webphoto.repository.TagRepository;
import rs.ac.bg.etf.webphoto.service.TagService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tag")
public class TagController {

    private final TagService tagService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Page<TagDto> findAll(@QuerydslPredicate(root = Tag.class, bindings = TagRepository.class) Predicate predicate,
                                Pageable pageable) {
        return tagService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public TagDto findById(@RequestParam Long id) {
        return tagService.findById(id);
    }

    @RequestMapping(value = "/findOrCreate", method = RequestMethod.GET)
    public List<Tag> findOrCreate(@RequestParam List<String> tags) {
        return tagService.findOrCreate(tags);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public TagDto save(@RequestBody TagDto tagDto) {
        return tagService.save(tagDto);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public List<TagDto> saveAll(@RequestBody List<TagDto> tagDto) {
        return tagService.saveAll(tagDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public TagDto update(@RequestBody TagDto tagDto) {
        return tagService.update(tagDto);
    }

}
