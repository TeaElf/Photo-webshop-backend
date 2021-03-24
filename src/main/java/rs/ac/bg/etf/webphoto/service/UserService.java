package rs.ac.bg.etf.webphoto.service;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.etf.webphoto.model.User;
import rs.ac.bg.etf.webphoto.model.dto.UserRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.UserResponseDto;


public interface UserService {

    Page<UserResponseDto> findAll(Predicate predicate, Pageable pageable);

    UserResponseDto findById(Long id);

    User findOne(Long id);

    UserResponseDto save(UserRequestDto userRequestDto);

    UserResponseDto update(UserRequestDto userRequestDto);
}
