package ru.flawden.divinitybankspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loan_offers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanOffer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interest_rate")
    private Double interestRate;

    @Column(name = "credit_name")
    private String creditName;

}