package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.dto.PhotoRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoResponseDto;

public interface PhotoService {

    Page<PhotoResponseDto> findAll(Predicate predicate, Pageable pageable);

    PhotoResponseDto findById(Long id);

    PhotoResponseDto save(PhotoRequestDto photoRequestDto);

    PhotoResponseDto update(Long id, PhotoRequestDto photoRequestDto);

    boolean delete(Long id);
}
