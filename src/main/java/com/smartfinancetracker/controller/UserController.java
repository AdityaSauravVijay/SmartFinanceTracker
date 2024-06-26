package com.smartfinancetracker.controller;

import com.smartfinancetracker.models.User;
import com.smartfinancetracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    //Register api user
    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.loginUser(user), HttpStatus.OK);
    }
}
