package ru.flawden.divinitybankspring.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class User implements Serializable, Comparator<User> {

    private int id;
    private Date registrationDate = new Date();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    private String firstName;
    private String lastName;
    private String eMail;
    private String password;
    private Date birthdate = new Date();
    private boolean gender;
    private List<Loan> loanList = new ArrayList<>();
    private List<DebitCard> debitCardList = new ArrayList<>();

    public Date getBirthdate() {
        return birthdate;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public User() {}

//    public User(String firstName, String lastName, String eMail, String password, Date birthdate, boolean gender) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.eMail = eMail;
//        this.password = password;
//        this.birthdate = birthdate;
//        this.gender = gender;
//        createdDate = new Date();
//    }

//    public User(int id, String firstName, String lastName, String eMail, String password, Date birthdate) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.eMail = eMail;
//        this.password = password;
//        this.birthdate = birthdate;
//        createdDate = new Date();
//        gender = true;
//    }

    public User(int id, String firstName, String lastName, String eMail, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.password = password;
        this.registrationDate = new Date();
        this.gender = true;
    }
//
//    public User(int id, String firstName, String lastName, String eMail, String password, Date birthdate, boolean gender) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.eMail = eMail;
//        this.password = password;
//        this.birthdate = birthdate;
//        this.gender = gender;
//        createdDate = new Date();
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String surName) {
        this.lastName = surName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthdate;
    }

    public void setBirthDate(Date date) {
        this.birthdate = date;
    }

    public boolean getGender() {
        return gender;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public List<DebitCard> getDebitCardList() {
        return debitCardList;
    }

    public void setDebitCardList(List<DebitCard> debitCardList) {
        this.debitCardList = debitCardList;
    }

    @Override
    public String toString() {
        return "First name: " + firstName + "\n" +
                "Last name: " + lastName + "\n" +
                "E-mail: " + eMail + "\n" +
                "Password: " + password + "\n" +
                "Date of Birth: " + simpleDateFormat.format(birthdate) + "\n" +
                "Gender: " + gender + "\n" +
                "Loan list: " + loanList + "\n" +
                "Debit Card List: " + debitCardList + "\n" +
                "Id: " + id + "\n\n";
    }

    @Override
    public int compare(User o1, User o2) {
        return o1.geteMail().compareTo(o2.geteMail());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(eMail, user.eMail) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eMail, password);
    }
}
