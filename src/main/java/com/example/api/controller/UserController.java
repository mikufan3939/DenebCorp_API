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

    @GetMapping("/{id}")
    public User getById(@PathVariable long id){
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable long id){
        return userService.updateUser(id, user);
    }
}
