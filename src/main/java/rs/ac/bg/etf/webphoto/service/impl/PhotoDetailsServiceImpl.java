package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsRequestDto;
import rs.ac.bg.etf.webphoto.repository.PhotoDetailsRepository;
import rs.ac.bg.etf.webphoto.service.PhotoDetailsService;
import rs.ac.bg.etf.webphoto.utils.PhotoDetailsMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoDetailsServiceImpl implements PhotoDetailsService {

    private final PhotoDetailsRepository photoDetailsRepository;

    private final PhotoDetailsMapper photoDetailsMapper;

    @Override
    public Page<PhotoDetailsDto> findAll(Predicate predicate, Pageable pageable) {
        Page<PhotoDetails> photoDetails = photoDetailsRepository.findAll(predicate, pageable);
        List<PhotoDetailsDto> response = photoDetails.stream().map(pd -> photoDetailsMapper.photoToPhotoDetailsDto(pd)).collect(Collectors.toList());
        return new PageImpl<>(response, pageable, photoDetails.getTotalElements());
    }

    @Override
    public PhotoDetailsDto findById(Long id) {
        // TODO add exception
        return photoDetailsMapper.photoToPhotoDetailsDto(photoDetailsRepository.findById(id).get());
    }

    @Override
    public PhotoDetailsDto save(PhotoDetailsDto photoDetailsDto) {
        PhotoDetails photoDetails = photoDetailsMapper.photoDetailsDtoToPhotoDetails(photoDetailsDto);
        return photoDetailsMapper.photoToPhotoDetailsDto(photoDetailsRepository.save(photoDetails));
    }

    @Override
    public List<PhotoDetails> saveAll(List<PhotoDetailsRequestDto> photoDetailsDtos) {
//        List<PhotoDetailsDto> details = photoDetailsDtos.stream().map(pd -> save(pd)).collect(Collectors.toList());
        List<PhotoDetails> details = photoDetailsDtos.stream().map(pd ->
            photoDetailsRepository.save(photoDetailsMapper.photoDetailsRequestDtoToPhotoDetails(pd)))
                .collect(Collectors.toList());
//        return details.stream().map(d-> photoDetailsMapper.photoDetailsDtoToPhotoDetails(d)).collect(Collectors.toList());
        return details;
    }

    @Override
    public PhotoDetailsDto update(PhotoDetailsDto photoDetailsDto) {
        // TODO add exception
        PhotoDetails photoDetails = photoDetailsRepository.findById(photoDetailsDto.getId()).get();
        photoDetails.setPath(photoDetailsDto.getPath());
        photoDetails.setPrice(photoDetailsDto.getPrice());
        photoDetails.setSize(photoDetailsDto.getSize());
        photoDetails.setInvoiceItems(photoDetails.getInvoiceItems());
        // TODO get photo and add
        return photoDetailsMapper.photoToPhotoDetailsDto(photoDetailsRepository.save(photoDetails));
    }
}
