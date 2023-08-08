package com.aston.bank_processing.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Account account;
    @Column(name = "type")
    private Integer transationType;
    @Column(name = "value")
    private Double value;

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Integer getTransationType() {
        return transationType;
    }

    public void setTransationType(Integer transationType) {
        this.transationType = transationType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
