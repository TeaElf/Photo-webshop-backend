package rs.ac.bg.etf.webphoto.utils;

import org.mapstruct.Mapper;
import rs.ac.bg.etf.webphoto.model.Photo;
import rs.ac.bg.etf.webphoto.model.dto.PhotoRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoResponseDto;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    PhotoResponseDto photoToPhotoResponseDto(Photo photo);

    Photo photoRequestDtoToPhoto(PhotoRequestDto photoRequestDto);

}
