package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhotoRequestDto {

    private Long id;

    private String title;

    private String path;

    private String description;

    private String orientation;

    private Long userId; // iz tokena

    private Long categoryId;

    private List<PhotoDetailsRequestDto> photoDetails = new ArrayList<>();

    private List<String> tags = new ArrayList<>();

}
