package rs.ac.bg.etf.webphoto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.dto.LoginRequestDto;
import rs.ac.bg.etf.webphoto.service.AuthService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public void login(@Validated @RequestBody LoginRequestDto loginRequest) {
        authService.login(loginRequest);
    }

    // TODO
    // forgot password
    // change password

}
