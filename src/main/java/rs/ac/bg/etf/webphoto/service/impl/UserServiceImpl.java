package rs.ac.bg.etf.webphoto.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.bg.etf.webphoto.model.User;
import rs.ac.bg.etf.webphoto.model.dto.UserRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.UserResponseDto;
import rs.ac.bg.etf.webphoto.repository.UserRepository;
import rs.ac.bg.etf.webphoto.service.UserService;
import rs.ac.bg.etf.webphoto.utils.UserMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserResponseDto> findAll(Predicate predicate, Pageable pageable) {
        Page<User> users = userRepository.findAll(predicate, pageable);
        List<UserResponseDto> responseDtoList = users.stream().map(user -> userMapper.userToUserResponseDto(user)).collect(Collectors.toList());
        return new PageImpl<>(responseDtoList, pageable, users.getTotalElements());
    }

    @Override
    public UserResponseDto findById(Long id) {
        // handle exception
        User user = userRepository.findById(id).get();
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public User findOne(Long id) {
        // handle exception
        return userRepository.findById(id).get();
    }

    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = userMapper.userRequestToUser(userRequestDto);
        user.setDateOfCreation(LocalDateTime.now());
        userRepository.save(user);
        UserResponseDto responseDto = userMapper.userToUserResponseDto(user);
        return responseDto;
    }

    @Override
    public UserResponseDto update(UserRequestDto userRequestDto) {
        User user = findOne(userRequestDto.getId());
        user.setAvatar(userRequestDto.getAvatar());
        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        user.setEmail(userRequestDto.getEmail());
        user.setCountry(userRequestDto.getCountry());
        user.setPassword(userRequestDto.getPassword());
        user.setUsername(userRequestDto.getUsername());
        userRepository.save(user);
        return userMapper.userToUserResponseDto(user);
    }
}
