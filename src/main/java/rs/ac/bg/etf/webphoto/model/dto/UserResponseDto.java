package rs.ac.bg.etf.webphoto.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {

    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String email;

    private String country;

    private LocalDateTime dateOfCreation;

    private String avatar;
}
