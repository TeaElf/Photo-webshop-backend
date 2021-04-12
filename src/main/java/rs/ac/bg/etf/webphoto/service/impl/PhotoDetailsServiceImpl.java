package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.exceptions.specifications.ResourceNotFoundException;
import rs.ac.bg.etf.webphoto.model.Photo;
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
    public List<PhotoDetails> saveAll(List<PhotoDetailsRequestDto> photoDetailsRequestDtos, Photo photo) {
        List<PhotoDetails> pDetails = photoDetailsRequestDtos.stream().map(pDe -> photoDetailsMapper.photoDetailsRequestDtoToPhotoDetails(pDe)).collect(Collectors.toList());
        for (PhotoDetails de: pDetails) {
            de.setPhoto(photo);
        }
        return pDetails.stream().map(pd -> photoDetailsRepository.save(pd)).collect(Collectors.toList());
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

    @Override
    public boolean delete(Long id) {
        if(photoDetailsRepository.existsById(id))
            photoDetailsRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean deleteAll(Long photoId) {
        List<PhotoDetails> photoDetails = photoDetailsRepository.findByPhoto_Id(photoId);
        if(!photoDetails.isEmpty())
            photoDetailsRepository.deleteAll(photoDetails);
        return true;
    }
}
