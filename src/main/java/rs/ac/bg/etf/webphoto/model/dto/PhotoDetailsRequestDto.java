package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;

@Data
public class PhotoDetailsRequestDto {

    private Long id;

    private String size;

    private Double price;

    // base64 value for path
    private String encodedValue;

}
