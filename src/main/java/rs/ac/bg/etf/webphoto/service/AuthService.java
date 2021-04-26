package rs.ac.bg.etf.webphoto.service;

import rs.ac.bg.etf.webphoto.model.dto.LoginRequestDto;

public interface AuthService {

    void login(LoginRequestDto loginRequest);

}
