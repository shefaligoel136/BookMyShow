package com.machinecoding.bookmyshow.Controllers;

import com.machinecoding.bookmyshow.Dto.SignUpUserRequestDto;
import com.machinecoding.bookmyshow.Dto.SignUpUserResponseDto;
import com.machinecoding.bookmyshow.Models.User;
import com.machinecoding.bookmyshow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpUserResponseDto signUpUser(SignUpUserRequestDto request) {
        User user = userService.signUpUser(
                request.getEmail(), request.getPassword()
        );

        SignUpUserResponseDto response = new SignUpUserResponseDto();
        response.setUserId(user.getId());

        return response;
    }
}
