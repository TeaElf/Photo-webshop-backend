package rs.ac.bg.etf.webphoto.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.etf.webphoto.model.dto.LoginRequestDto;
import rs.ac.bg.etf.webphoto.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public void login(@Validated @RequestBody LoginRequestDto loginRequest) {
        authService.login(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        return authService.logout(request, response);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkIfAuthenticated() {
        return authService.checkIfAuthenticated();
    }

    // TODO
    // forgot password
    // change password
}
