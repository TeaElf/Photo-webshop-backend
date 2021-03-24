package rs.ac.bg.etf.webphoto.utils;

import org.mapstruct.Mapper;
import rs.ac.bg.etf.webphoto.model.Tag;
import rs.ac.bg.etf.webphoto.model.dto.TagDto;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagToTagDto(Tag tag);

    Tag tagDtoToTag(TagDto tagDto);
}
