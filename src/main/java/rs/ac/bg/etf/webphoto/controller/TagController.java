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
@RequestMapping(value = "/tags")
public class TagController {

    private final TagService tagService;

    @GetMapping
    public Page<TagDto> findAll(@QuerydslPredicate(root = Tag.class, bindings = TagRepository.class) Predicate predicate,
                                Pageable pageable) {
        return tagService.findAll(predicate, pageable);
    }

    @GetMapping("/{id}")
    public TagDto findById(@PathVariable Long id) {
        return tagService.findById(id);
    }

    @GetMapping(value = "/findOrCreate")
    public List<Tag> findOrCreate(@RequestParam List<String> tags) {
        return tagService.findOrCreate(tags);
    }

    @PostMapping
    public TagDto save(@RequestBody TagDto tagDto) {
        return tagService.save(tagDto);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public List<TagDto> saveAll(@RequestBody List<TagDto> tagDto) {
        return tagService.saveAll(tagDto);
    }

    @PutMapping("/{id}")
    public TagDto update(@PathVariable Long id, @RequestBody TagDto tagDto) {
        return tagService.update(id, tagDto);
    }

}
