package com.prathyusha.demo.pgraphql.services;

import com.prathyusha.demo.pgraphql.entity.AccountInformation;
import com.prathyusha.demo.pgraphql.repository.AccountDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {

    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    public AccountServiceImp(AccountDetailsRepository accountDetailsRepository) {
        this.accountDetailsRepository = accountDetailsRepository;
    }

    @Override
    public AccountInformation create(AccountInformation accountInformation) {
        return this.accountDetailsRepository.save(accountInformation);
    }

    @Override
    public List<AccountInformation> getAll() {
        return this.accountDetailsRepository.findAll();
    }

    @Override
    public AccountInformation get(int accountId) {
        return this.accountDetailsRepository.findById(accountId).orElseThrow(() -> new RuntimeException("accountId you are looking for not found on server !!"));
    }

    @Override
    public List<Optional<AccountInformation>> getAccounts(List<Integer> accountIds, String loanVersion){
        List<Optional<AccountInformation>> accountInfoList = new ArrayList<>();
        for (Integer accountId : accountIds) {
            Optional<AccountInformation> accountInfo = accountDetailsRepository.findById(accountId);
            if(accountInfo.isPresent() && loanVersion.equals(accountInfo.get().getLoanVersion())) {
                accountInfoList.add(accountInfo);
            }
        }
        return accountInfoList;
    }
}
