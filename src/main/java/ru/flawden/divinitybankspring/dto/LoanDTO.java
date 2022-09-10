package ru.flawden.divinitybankspring.dto;

public class LoanDTO {

    private String product;
    private Double summ;
    private Integer loanTerm;

    public LoanDTO(String product, Double summ, Integer loanTerm) {
        this.product = product;
        this.summ = summ;
        this.loanTerm = loanTerm;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getSumm() {
        return summ;
    }

    public void setSumm(Double summ) {
        this.summ = summ;
    }

    public Integer getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(Integer loanTerm) {
        this.loanTerm = loanTerm;
    }

}
