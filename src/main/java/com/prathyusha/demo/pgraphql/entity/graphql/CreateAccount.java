package com.prathyusha.demo.pgraphql.entity.graphql;

import lombok.Data;

@Data
public class CreateAccount {

    private  String  holderName;
    private String  holderAddress;
    private String  SSN_NUMBER;
    private Float  startingBalance;
}
