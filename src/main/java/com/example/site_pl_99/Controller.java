package com.example.site_pl_99;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.exceptions.UserNotFoundException;
import com.example.site_pl_99.service.UserService;
import com.example.site_pl_99.utils.Internalization;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class Controller {
    private final Internalization internalization;

    private final UserService userService;

    public Controller(Internalization internalization, UserService userService) {
        this.internalization = internalization;
        this.userService = userService;
    }


    @GetMapping(value = "/all")
    public List<UserDtoResponse> getAllUsers(){
        return userService.getAll().stream().map(x -> new UserDtoResponse(x)).toList();
    }

    @PostMapping(value = "/register")
    public UserDtoResponse register(
            @RequestBody UserDtoRequestRegister user
    ){
        return new UserDtoResponse(userService.save(user));
    }
}
