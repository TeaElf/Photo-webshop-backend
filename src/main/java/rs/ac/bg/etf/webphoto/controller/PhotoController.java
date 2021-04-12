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
@RequestMapping(value = "/photo")
public class PhotoController {

    private final PhotoService photoService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Page<PhotoResponseDto> findAll(@QuerydslPredicate(root = Photo.class, bindings = PhotoRepository.class) Predicate predicate,
                                          Pageable pageable) {
        return photoService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public PhotoResponseDto findById(@RequestParam Long id) {
        return photoService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PhotoResponseDto save(@RequestBody PhotoRequestDto photoRequestDto) {
        return photoService.save(photoRequestDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public PhotoResponseDto update(@RequestBody PhotoRequestDto photoRequestDto) {
        return photoService.update(photoRequestDto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public boolean delete(@RequestParam Long id){
        return photoService.delete(id);
    }

}
