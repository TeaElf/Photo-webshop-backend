package rs.ac.bg.etf.webphoto.service;

import org.springframework.http.ResponseEntity;
import rs.ac.bg.etf.webphoto.model.dto.LoginRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    void login(LoginRequestDto loginRequest);

    ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response);

    ResponseEntity<?> checkIfAuthenticated();
}
