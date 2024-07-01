package com.saikrishna.demo.pgraphql.services;

import com.saikrishna.demo.pgraphql.entity.AccountInformation;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountInformation create(AccountInformation accountInformation);

    public List<AccountInformation> getAll();
    public AccountInformation get(int accountId) ;

    List<Optional<AccountInformation>> getAccounts(int accountIds, String loanVersion);
}
