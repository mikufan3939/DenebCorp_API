package com.example.api.controller;

import com.example.api.model.User;
import com.example.api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public User createUser(@RequestBody User user){
        System.out.println(user.getFirstName());
        return userService.createUser(user);
    }

    @PostMapping("/{id}/addPhoto")
    public User addPhoto(@RequestParam("image")MultipartFile image, @PathVariable long id) throws IOException {
        return userService.addImage(id, image);
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
}
