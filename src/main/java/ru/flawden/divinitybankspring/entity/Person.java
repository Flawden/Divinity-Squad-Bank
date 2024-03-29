package ru.flawden.divinitybankspring.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person implements Annotation {

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public String getFullName() { return firstname + " " + surname; }

    public Person(){}

    public Person(String firstname, String surname, String gender, Date dateOfBirth, String email, String password, Boolean enabled) {
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
