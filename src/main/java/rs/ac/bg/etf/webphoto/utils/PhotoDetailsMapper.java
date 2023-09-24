package rs.ac.bg.etf.webphoto.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsRequestDto;

@Mapper(componentModel = "spring")
public interface PhotoDetailsMapper {

    @Mapping(target = "photoId", source = "photo.id")
    PhotoDetailsDto photoToPhotoDetailsDto(PhotoDetails photoDetails);

    @Mapping(target = "photo.id", source = "photoId")
    PhotoDetails photoDetailsDtoToPhotoDetails(PhotoDetailsDto photoDetailsDto);

    PhotoDetails photoDetailsRequestDtoToPhotoDetails(PhotoDetailsRequestDto photoDetailsRequestDto);
}
