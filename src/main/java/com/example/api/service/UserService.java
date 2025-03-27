package com.example.api.service;

import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hibernate.annotations.processing.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getById(long id) {
        try{
            return userRepository.findById(id).orElseThrow();
        }
        catch (NoSuchElementException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado!", exception);
        }
    }

    public User createUser(User user) {
        try{
            return userRepository.save(user);
        }
        catch (Exception exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
        }
    }

    public void deleteUser(long id){
        try{
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException exception){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!", exception);
        }
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
        selectedUser.setProfilePhoto(image.getBytes());
        return userRepository.save(selectedUser);
    }
}
