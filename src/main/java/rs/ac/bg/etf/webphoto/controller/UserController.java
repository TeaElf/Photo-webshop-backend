package rs.ac.bg.etf.webphoto.controller;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.User;
import rs.ac.bg.etf.webphoto.model.dto.UserRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.UserResponseDto;
import rs.ac.bg.etf.webphoto.repository.UserRepository;
import rs.ac.bg.etf.webphoto.service.UserService;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponseDto> findAll(@QuerydslPredicate(root = User.class, bindings = UserRepository.class) Predicate predicate,
                                         Pageable pageable){
        return userService.findAll(predicate, pageable);
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto){
        return userService.save(userRequestDto);
    }

    @PutMapping("/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto){
        return userService.update(id, userRequestDto);
    }

}
