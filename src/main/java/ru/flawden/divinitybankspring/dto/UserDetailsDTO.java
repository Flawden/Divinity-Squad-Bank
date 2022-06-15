package ru.flawden.divinitybankspring.dto;

import ru.flawden.divinitybankspring.entity.DebitCardEntity;
import ru.flawden.divinitybankspring.entity.LoanEntity;
import ru.flawden.divinitybankspring.entity.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class UserDetailsDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;

    private List<DebitCardEntity> debitCardList;
    private List<LoanEntity> loanList;
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<DebitCardEntity> getDebitCardList() {
        return debitCardList;
    }

    public void setDebitCardList(List<DebitCardEntity> debitCardList) {
        this.debitCardList = debitCardList;
    }

    public List<LoanEntity> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<LoanEntity> loanList) {
        this.loanList = loanList;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public UserDetailsDTO() {}

    public UserDetailsDTO(Long id, String firstName, String lastName, String email, Boolean enabled, List<DebitCardEntity> debitCardList, List<LoanEntity> loanList, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.debitCardList = debitCardList;
        this.loanList = loanList;
        this.roles = roles;
    }

}
