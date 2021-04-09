package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.model.Category;
import rs.ac.bg.etf.webphoto.model.Photo;
import rs.ac.bg.etf.webphoto.model.Tag;
import rs.ac.bg.etf.webphoto.model.dto.PhotoRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoResponseDto;
import rs.ac.bg.etf.webphoto.repository.PhotoRepository;
import rs.ac.bg.etf.webphoto.service.CategoryService;
import rs.ac.bg.etf.webphoto.service.PhotoDetailsService;
import rs.ac.bg.etf.webphoto.service.PhotoService;
import rs.ac.bg.etf.webphoto.service.TagService;
import rs.ac.bg.etf.webphoto.utils.PhotoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    private final PhotoMapper photoMapper;

    private final CategoryService categoryService;

    private final PhotoDetailsService photoDetailsService;

    private final TagService tagService;

    @Override
    public Page<PhotoResponseDto> findAll(Predicate predicate, Pageable pageable) {
        Page<Photo> photos = photoRepository.findAll(predicate, pageable);
        List<PhotoResponseDto> response = photos.stream().map(pd -> photoMapper.photoToPhotoResponseDto(pd)).collect(Collectors.toList());
        return new PageImpl<>(response, pageable, photos.getTotalElements());
    }

    @Override
    public PhotoResponseDto findById(Long id) {
        // TODO add exception
        return photoMapper.photoToPhotoResponseDto(photoRepository.findById(id).get());
    }

    @Override
    public PhotoResponseDto save(PhotoRequestDto photoRequestDto) {
        Photo photo = photoMapper.photoRequestDtoToPhoto(photoRequestDto);
        // photo details
        photoDetailsService.saveAll(photoRequestDto.getPhotoDetails());

        // category
        Category category = categoryService.findOne(photoRequestDto.getCategoryId());
        photo.setCategory(category);

        // tags
        List<Tag> tags = tagService.findOrCreate(photoRequestDto.getTags());
        photo.setTags(tags);

        return photoMapper.photoToPhotoResponseDto(photoRepository.save(photo));
    }

    @Override
    public PhotoResponseDto update(PhotoRequestDto photoRequestDto) {
        // TODO add exception
        Photo photo = photoRepository.findById(photoRequestDto.getId()).get();
        photo.setTitle(photoRequestDto.getTitle());
        photo.setDescription(photoRequestDto.getDescription());
        photo.setOrientation(photoRequestDto.getOrientation());
        photo.setPath(photoRequestDto.getPath());
        // cekiraj promenu kategorije? da li uopste moze to?
        // pronadji i dodaj photo.setCategory();
        // tagovi ? photo.setTags(photoRequestDto.get);
        // da li menjamo kroz ovo photo detalje? meni se cini ne ; photo.setPhotoDetails();
        return photoMapper.photoToPhotoResponseDto(photoRepository.save(photo));
    }
}
