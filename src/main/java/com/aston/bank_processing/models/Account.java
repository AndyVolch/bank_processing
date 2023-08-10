package com.aston.bank_processing.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "beneficial_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Beneficial beneficial;

    @Column(name = "account")
    private String accountNumber;
    @Column(name = "balance")
    private Double balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Beneficial getBeneficial() {
        return beneficial;
    }

    public void setBeneficial(Beneficial beneficial) {
        this.beneficial = beneficial;
    }

    public String getAccount() {
        return accountNumber;
    }

    public void setAccount(String account) {
        this.accountNumber = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
