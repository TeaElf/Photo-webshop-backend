package rs.ac.bg.etf.webphoto.controller;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsDto;
import rs.ac.bg.etf.webphoto.repository.PhotoDetailsRepository;
import rs.ac.bg.etf.webphoto.service.PhotoDetailsService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/details")
public class PhotoDetailsController {

    private final PhotoDetailsService photoDetailsService;

    @GetMapping
    public Page<PhotoDetailsDto> findAll(@QuerydslPredicate(root = PhotoDetails.class, bindings = PhotoDetailsRepository.class) Predicate predicate,
                                         Pageable pageable) {
        return photoDetailsService.findAll(predicate, pageable);
    }

    @GetMapping("/{id}")
    public PhotoDetailsDto findById(@PathVariable Long id) {
        return photoDetailsService.findById(id);
    }

    @PostMapping
    public PhotoDetailsDto save(@RequestBody PhotoDetailsDto photoDetailsDto) {
        return photoDetailsService.save(photoDetailsDto);
    }

    @PutMapping("/{id}")
    public PhotoDetailsDto update(@PathVariable Long id, @RequestBody PhotoDetailsDto photoDetailsDto) {
        return photoDetailsService.update(id, photoDetailsDto);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id){
        return photoDetailsService.delete(id);
    }

    @DeleteMapping("/{photoId}")
    public boolean deleteAll(@PathVariable Long photoId){
        return photoDetailsService.deleteAll(photoId);
    }

}
