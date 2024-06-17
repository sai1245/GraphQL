package com.prathyusha.demo.pgraphql.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Data
@Slf4j
@Entity
@Table(name = "account")
public class AccountInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int accountId ;

    public String loanVersion;

    public String holderName ;

    public String holderAddress;

    public Float finalAmount;

    private String  SSN_NUMBER;


}
