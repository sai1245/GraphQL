package com.prathyusha.demo.pgraphql.services;

import com.prathyusha.demo.pgraphql.entity.AccountInformation;
import com.prathyusha.demo.pgraphql.entity.graphql.CreateAccount;
import org.springframework.graphql.data.method.annotation.Argument;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountInformation create(AccountInformation accountInformation);

    public List<AccountInformation> getAll();
    public AccountInformation get(int accountId) ;

    List<Optional<AccountInformation>> getAccounts(List<Integer> accountIds, String loanVersion);
}
