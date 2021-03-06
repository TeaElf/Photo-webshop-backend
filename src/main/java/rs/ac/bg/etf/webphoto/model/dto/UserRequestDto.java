package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;


@Data
public class UserRequestDto {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String email;

    private String country;

    private String avatar;

}
