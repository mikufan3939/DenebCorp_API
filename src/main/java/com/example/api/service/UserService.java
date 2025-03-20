package com.example.api.service;

import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import com.example.api.utils.ImageUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getById(long id){
        User user=userRepository.findById(id).orElseThrow();
        user.setProfilePhoto(ImageUtils.decompressImage(user.getProfilePhoto()));
        return user;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public User updateUser(long id, User user){
        User selectedUser=userRepository.findById(id).orElseThrow();
        selectedUser.setFirstName(user.getFirstName());
        selectedUser.setEmail(user.getEmail());
        selectedUser.setLastName(user.getEmail());
        selectedUser.setBirthDate(user.getBirthDate());
        selectedUser.setPhoneNumber(user.getPhoneNumber());

        return userRepository.save(selectedUser);
    }

    public User addImage(long id, MultipartFile image) throws IOException {
        System.out.println(image);
        User selectedUser=userRepository.findById(id).orElseThrow();
        selectedUser.setProfilePhoto(ImageUtils.compressImage(image.getBytes()));
        return userRepository.save(selectedUser);
    }
}
