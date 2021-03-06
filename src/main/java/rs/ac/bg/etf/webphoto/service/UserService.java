package rs.ac.bg.etf.webphoto.service;

import rs.ac.bg.etf.webphoto.model.dto.UserRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findAll();

    UserResponseDto findById(Long id);

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto update(UserRequestDto userRequestDto);
}
