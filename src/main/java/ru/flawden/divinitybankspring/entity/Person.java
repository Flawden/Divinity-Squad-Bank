package ru.flawden.divinitybankspring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotEmpty(message = "Email shouldn't be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password shouldn't be empty")
    private String password;
    private Boolean enabled;

    @OneToMany(mappedBy = "owner")
    private List<Card> cards;

    @OneToMany(mappedBy = "owner")
    private List<Loan> loans;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public String getFullName() {
        return firstname + " " + surname;
    }

    public Person() {
    }

    public Person(String firstname, String surname, String gender, Date dateOfBirth, String email, String password, Boolean enabled) {
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

}
