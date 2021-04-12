package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
import rs.ac.bg.etf.webphoto.model.*;
import rs.ac.bg.etf.webphoto.model.dto.PhotoRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoResponseDto;
import rs.ac.bg.etf.webphoto.repository.PhotoRepository;
import rs.ac.bg.etf.webphoto.service.*;
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

    private final UserService userService;

    @Override
    public Page<PhotoResponseDto> findAll(Predicate predicate, Pageable pageable) {
        Page<Photo> photos = photoRepository.findAll(predicate, pageable);
        List<PhotoResponseDto> response = photos.stream().map(pd -> photoMapper.photoToPhotoResponseDto(pd)).collect(Collectors.toList());
        return new PageImpl<>(response, pageable, photos.getTotalElements());
    }

    @Override
    public PhotoResponseDto findById(Long id) {
        return photoMapper.photoToPhotoResponseDto(photoRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public PhotoResponseDto save(PhotoRequestDto photoRequestDto) {
        Photo photo = photoMapper.photoRequestDtoToPhoto(photoRequestDto);

        // user  - zameniti sa user id-em iz tokena
        User user = userService.findOne(photoRequestDto.getUserId());
        photo.setUser(user);

        // category
        Category category = categoryService.findOne(photoRequestDto.getCategoryId());
        photo.setCategory(category);

        // tags
        List<Tag> tags = tagService.findOrCreate(photoRequestDto.getTags());
        photo.setTags(tags);

        Photo response = photoRepository.save(photo);

        // photo details
        List<PhotoDetails> details = photoDetailsService.saveAll(photoRequestDto.getPhotoDetails(), photo);
        response.setPhotoDetails(details);

        return photoMapper.photoToPhotoResponseDto(response);
    }

    @Override
    public PhotoResponseDto update(PhotoRequestDto photoRequestDto) {
        Photo photo = photoRepository.findById(photoRequestDto.getId()).orElseThrow(ResourceNotFoundException::new);
        photo.setTitle(photoRequestDto.getTitle());
        photo.setDescription(photoRequestDto.getDescription());
        photo.setOrientation(photoRequestDto.getOrientation());
        photo.setPath(photoRequestDto.getPath());
        if (photoRequestDto.getCategoryId()!=null) {
            Category category = categoryService.findOne(photoRequestDto.getCategoryId());
            photo.setCategory(category);
        }
        if (!photoRequestDto.getTags().isEmpty()) {
            List<Tag> tags = tagService.findOrCreate(photoRequestDto.getTags());
            photo.setTags(tags);
        }
        // da li menjamo kroz ovo photo detalje? photo.setPhotoDetails();
        List<PhotoDetails> photoDetails = photoDetailsService.updateAll(photoRequestDto.getPhotoDetails());
        photo.setPhotoDetails(photoDetails);

        return photoMapper.photoToPhotoResponseDto(photoRepository.save(photo));
    }
}
