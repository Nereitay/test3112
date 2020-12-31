package com.springboot.atm.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "account")
public class AccountModel {
    @Id
    @GeneratedValue(generator = "atmGenerator")
    @GenericGenerator(name = "atmGenerator", strategy = "uuid")
    @Column(name = "customerId", nullable = false, length = 36)
    private String customerId;
    private String customerName;
    @Pattern(regexp = "[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}", message = "the IBAN number is not correct.")
    private String ibanNumber;
    private String dni;
    private int balance;
}
