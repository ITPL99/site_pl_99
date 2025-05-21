package com.example.site_pl_99.controller;

import com.example.site_pl_99.dto.UserDtoRequestRegister;
import com.example.site_pl_99.dto.UserDtoResponse;
import com.example.site_pl_99.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDtoResponse> getAllUsers() {
        return userService.getAll().stream().map(UserDtoResponse::new).toList();
    }

    @PostMapping("/add")
    public UserDtoResponse addNewUser(@RequestBody UserDtoRequestRegister userDtoRequestRegister) {
        return new UserDtoResponse( userService.save(userDtoRequestRegister));
    }

    @PostMapping("/get-login")
    public UserDtoResponse getUserByLogin(@RequestParam String username){
        return new UserDtoResponse(userService.getByUsername(username));
    }

    
}
