package ru.flawden.divinitybankspring.dto;

import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;

@Getter
@Setter
public class LoanDTO {

    private String creditName;
    private Double sum;
    private Integer creditTerm;

    @ConstructorProperties({"creditName", "sum", "creditTerm"})
    public LoanDTO(String creditName, Double sum, Integer creditTerm) {
        this.creditName = creditName;
        this.sum = sum;
        this.creditTerm = creditTerm;
    }


}
