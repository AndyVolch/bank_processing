package com.aston.bank_processing.models;

import jakarta.persistence.*;

@Entity
@Table(name = "beneficials")
public class Beneficial {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "pincode")
    private String pincode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPincode() {
        return pincode;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
