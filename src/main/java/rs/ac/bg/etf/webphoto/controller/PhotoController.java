package rs.ac.bg.etf.webphoto.controller;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.Photo;
import rs.ac.bg.etf.webphoto.model.dto.PhotoRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoResponseDto;
import rs.ac.bg.etf.webphoto.repository.PhotoRepository;
import rs.ac.bg.etf.webphoto.service.PhotoService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/photos")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping
    public Page<PhotoResponseDto> findAll(@QuerydslPredicate(root = Photo.class, bindings = PhotoRepository.class) Predicate predicate,
                                          Pageable pageable) {
        return photoService.findAll(predicate, pageable);
    }

    @GetMapping("/{id}")
    public PhotoResponseDto findById(@PathVariable Long id) {
        return photoService.findById(id);
    }

    @PostMapping
    public PhotoResponseDto save(@RequestBody PhotoRequestDto photoRequestDto) {
        return photoService.save(photoRequestDto);
    }

    @PutMapping("/{id}")
    public PhotoResponseDto update(@PathVariable Long id, @RequestBody PhotoRequestDto photoRequestDto) {
        return photoService.update(id, photoRequestDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return photoService.delete(id);
    }

}
