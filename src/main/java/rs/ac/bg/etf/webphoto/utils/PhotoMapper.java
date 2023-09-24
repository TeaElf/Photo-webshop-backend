package rs.ac.bg.etf.webphoto.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rs.ac.bg.etf.webphoto.model.Photo;
import rs.ac.bg.etf.webphoto.model.dto.PhotoRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.PhotoResponseDto;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

    @Mapping(target = "userId", source = "user.id")
    PhotoResponseDto photoToPhotoResponseDto(Photo photo);

    @Mapping(target = "tags", ignore = true)
    Photo photoRequestDtoToPhoto(PhotoRequestDto photoRequestDto);

}
