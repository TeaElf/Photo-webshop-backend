package rs.ac.bg.etf.webphoto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.dto.UserRequestDto;
import rs.ac.bg.etf.webphoto.model.dto.UserResponseDto;
import rs.ac.bg.etf.webphoto.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<UserResponseDto> findAll(){
        return userService.findAll();
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
