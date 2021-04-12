package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsRequestDto;
import rs.ac.bg.etf.webphoto.repository.PhotoDetailsRepository;
import rs.ac.bg.etf.webphoto.service.PhotoDetailsService;
import rs.ac.bg.etf.webphoto.utils.PhotoDetailsMapper;

import java.util.ArrayList;
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
        return photoDetailsMapper.photoToPhotoDetailsDto(findOne(id));
    }

    @Override
    public PhotoDetails findOne(Long id) {
        return photoDetailsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public PhotoDetailsDto save(PhotoDetailsDto photoDetailsDto) {
        PhotoDetails photoDetails = photoDetailsMapper.photoDetailsDtoToPhotoDetails(photoDetailsDto);
        return photoDetailsMapper.photoToPhotoDetailsDto(photoDetailsRepository.save(photoDetails));
    }

    @Override
    public List<PhotoDetails> saveAll(List<PhotoDetailsRequestDto> photoDetailsDtos) {
//        List<PhotoDetailsDto> details = photoDetailsDtos.stream().map(pd -> save(pd)).collect(Collectors.toList());
        List<PhotoDetails> details = photoDetailsDtos.stream().map(pd -> {
            PhotoDetails details1 = photoDetailsMapper.photoDetailsRequestDtoToPhotoDetails(pd);

            return photoDetailsRepository.save(details1);
        })
                .collect(Collectors.toList());
//        return details.stream().map(d-> photoDetailsMapper.photoDetailsDtoToPhotoDetails(d)).collect(Collectors.toList());
        return details;
    }

    @Override
    public List<PhotoDetails> saveAllDetails(List<PhotoDetails> photoDetails) {
        return photoDetails.stream().map(pd -> photoDetailsRepository.save(pd)).collect(Collectors.toList());
    }

    @Override
    public PhotoDetailsDto update(PhotoDetailsDto photoDetailsDto) {
        PhotoDetails photoDetails = findOne(photoDetailsDto.getId());
        photoDetails.setPath(photoDetailsDto.getPath());
        photoDetails.setPrice(photoDetailsDto.getPrice());
        photoDetails.setSize(photoDetailsDto.getSize());
        photoDetails.setInvoiceItems(photoDetails.getInvoiceItems());
        return photoDetailsMapper.photoToPhotoDetailsDto(photoDetailsRepository.save(photoDetails));
    }

    @Override
    public List<PhotoDetails> updateAll(List<PhotoDetailsRequestDto> photoDetailsDtos) {
        List<PhotoDetails> details = new ArrayList<>();
        for (PhotoDetailsRequestDto pd: photoDetailsDtos) {
            PhotoDetails photoDetails = findOne(pd.getId());
            photoDetails.setSize(pd.getSize());
            photoDetails.setPrice(pd.getPrice());
            details.add(photoDetails);
        }
        return photoDetailsRepository.saveAll(details);
    }
}
