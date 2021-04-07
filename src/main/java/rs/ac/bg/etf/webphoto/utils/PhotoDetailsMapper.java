package rs.ac.bg.etf.webphoto.utils;

import org.mapstruct.Mapper;
import rs.ac.bg.etf.webphoto.model.PhotoDetails;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoDetailsRequestDto;

@Mapper(componentModel = "spring")
public interface PhotoDetailsMapper {

    PhotoDetailsDto photoToPhotoDetailsDto(PhotoDetails photoDetails);

    PhotoDetails photoDetailsDtoToPhotoDetails(PhotoDetailsDto photoDetailsDto);

    PhotoDetails photoDetailsRequestDtoToPhotoDetails(PhotoDetailsRequestDto photoDetailsRequestDto);
}
