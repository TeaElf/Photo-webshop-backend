package rs.ac.bg.etf.webphoto.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.model.dto.UserRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.UserResponseDto;
import rs.ac.bg.etf.webphoto.repository.UserRepository;
import rs.ac.bg.etf.webphoto.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponseDto> findAll() {
        return null;
    }

    @Override
    public UserResponseDto findById(Long id) {
        return null;
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto) {
        return null;
    }
}
