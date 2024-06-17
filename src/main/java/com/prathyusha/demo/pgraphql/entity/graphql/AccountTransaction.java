package com.prathyusha.demo.pgraphql.entity.graphql;

import lombok.Data;

@Data
public class AccountTransaction {

    public enum OPERATIONS {

        DEBIT   ,
        CREDIT   ,


    }
    private int  accountId;
    private float amount;
    private OPERATIONS operation;
}
