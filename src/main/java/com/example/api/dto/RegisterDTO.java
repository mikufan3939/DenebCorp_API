package com.example.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class RegisterDTO {
    @NotEmpty(message = "O nome é obrigatório")
    private String firstName;

    @NotEmpty(message = "O sobrenome é obrigatório")
    private String lastName;

    @NotEmpty(message = "A data de nascimento é obrigatória")
    private Date birthDate;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Size(min = 8)
    private String password;

}
