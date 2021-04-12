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

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Page<PhotoDetailsDto> findAll(@QuerydslPredicate(root = PhotoDetails.class, bindings = PhotoDetailsRepository.class) Predicate predicate,
                                         Pageable pageable) {
        return photoDetailsService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public PhotoDetailsDto findById(@RequestParam Long id) {
        return photoDetailsService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public PhotoDetailsDto save(@RequestBody PhotoDetailsDto photoDetailsDto) {
        return photoDetailsService.save(photoDetailsDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public PhotoDetailsDto update(@RequestBody PhotoDetailsDto photoDetailsDto) {
        return photoDetailsService.update(photoDetailsDto);
    }

    // TODO DELETE

}
