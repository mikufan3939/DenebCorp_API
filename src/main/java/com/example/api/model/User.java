package com.example.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(name = "users")
public class User implements UserDetails {
    //tentar aprender sobre uuid
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "email", unique = true, nullable = false)

    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column( name = "password")
    private String password;

    @Column(name = "birth_date")
    private Date birthDate;

    //adicionar compressao
    @Lob
    @Column(name = "profile_photo")
    private byte[] profilePhoto;

    @OneToMany(mappedBy = "user")
    @Column(name="ratings")
    private List<Rating> ratings;

    public User(String email, String phoneNumber, String encryptedPassword) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = encryptedPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        if (email != null && !email.isEmpty()) {
            return email;
        } else if (phoneNumber != null && !phoneNumber.isEmpty()) {
            return phoneNumber;
        }
        throw new IllegalStateException("Usuário sem email ou telefone válido");
    }
}
