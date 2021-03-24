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
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Page<UserResponseDto> findAll(@QuerydslPredicate(root = User.class, bindings = UserRepository.class) Predicate predicate,
                                         Pageable pageable){
        return userService.findAll(predicate, pageable);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public UserResponseDto findById(@RequestParam Long id){
        return userService.findById(id);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public UserResponseDto save(@RequestBody UserRequestDto userRequestDto){
        return userService.save(userRequestDto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public UserResponseDto update(@RequestBody UserRequestDto userRequestDto){
        return userService.update(userRequestDto);
    }

}
