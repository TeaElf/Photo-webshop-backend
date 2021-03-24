package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;
import rs.ac.bg.etf.webphoto.model.Photo;

import java.util.List;

@Data
public class TagDto {

    private Long id;

    private String name;

    // TODO regulisati ovo, da li uopste pri updateu i saveu i da li mozda prazna lista?
    private List<Photo> photos;
}
