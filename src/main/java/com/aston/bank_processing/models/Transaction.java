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
    @ManyToOne
    @JoinColumn(name = "reciever_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Account accountReciever;
    @Column(name = "type")
    private TransactionType transationType;
    @Column(name = "value")
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccountReciever() {
        return accountReciever;
    }

    public void setAccountReciever(Account accountReciever) {
        this.accountReciever = accountReciever;
    }

    public TransactionType getTransationType() {
        return transationType;
    }

    public void setTransationType(TransactionType transationType) {
        this.transationType = transationType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public enum TransactionType {
        DEPOSIT, WITHDRAW, TRANSFER
    }
}
