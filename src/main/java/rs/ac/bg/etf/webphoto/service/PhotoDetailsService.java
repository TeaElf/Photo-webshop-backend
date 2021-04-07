package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsRequestDto;

import java.util.List;

public interface PhotoDetailsService {

    Page<PhotoDetailsDto> findAll(Predicate predicate, Pageable pageable);

    PhotoDetailsDto findById(Long id);

    PhotoDetailsDto save(PhotoDetailsDto photoDetailsDto);

    List<PhotoDetails> saveAll(List<PhotoDetailsRequestDto> photoDetailsDtos);

    PhotoDetailsDto update(PhotoDetailsDto photoDetailsDto);

}
