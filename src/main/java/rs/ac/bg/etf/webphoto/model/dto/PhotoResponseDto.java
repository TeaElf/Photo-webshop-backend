package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class PhotoResponseDto {

    private Long id;

    private String title;

    private String path;

    private String description;

    private String orientation;

    private CategoryDto category; // razmisliti da se vrati mozda samo string

    private List<PhotoDetailsDto> photoDetails;

    private List<TagDto> tags; // razmisliti da se vrati mozda samo string ali onda pretraga po naslovu

    private Long userId;

}
