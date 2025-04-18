package ru.flawden.divinitybankspring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class PersonDTO {

    @NotEmpty(message = "Firstname shouldn't be empty")
    @Size(min = 2, max = 15, message = "Firstname should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Za-z]+", message = "Firstname must contain only Latin letters")
    private String firstname;

    @NotEmpty(message = "Surname shouldn't be empty")
    @Size(min = 2, max = 15, message = "Surname should be between 2 and 30 characters")
    @Pattern(regexp = "[A-Za-z]+", message = "Surname must contain only Latin letters")
    private String surname;

    @NotEmpty(message = "Gender shouldn't be empty")
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password shouldn't be empty")
    private String password;

}
