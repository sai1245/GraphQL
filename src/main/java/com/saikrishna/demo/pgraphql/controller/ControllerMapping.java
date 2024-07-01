package com.saikrishna.demo.pgraphql.controller;

import com.saikrishna.demo.pgraphql.entity.AccountInformation;
import com.saikrishna.demo.pgraphql.entity.graphql.AccountTransaction;
import com.saikrishna.demo.pgraphql.entity.graphql.CreateAccount;
import com.saikrishna.demo.pgraphql.services.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ControllerMapping {

    @Autowired
    public AccountServiceImp accountService;

    @QueryMapping("getAllAccountInformation")
    public List<AccountInformation> getAllAccount() {
        return this.accountService.getAll();
    }

    //get single book
    @QueryMapping("getAccountInformation")
    public AccountInformation getAccount(@Argument int accountId) {
        return this.accountService.get(accountId);
    }

    @QueryMapping("getAccountsInformation")
    public List<Optional<AccountInformation>> getAccounts(@Argument int accountIds, @Argument String loanVersion) {
        return this.accountService.getAccounts(accountIds, loanVersion);
    }

    @QueryMapping("getAccountsInfo")
    public List<Optional<AccountInformation>> getAccountsByMap(@Argument List<Map<Integer,String>> accountsMap){
        List<Optional<AccountInformation>> AccountsMap = new ArrayList<>();
        for (Map<Integer, String> account : accountsMap) {
            Integer accountId = account.keySet().iterator().next();
            String loanVersion = account.values().iterator().next();
            AccountsMap = accountService.getAccounts(accountId,loanVersion);
        }
        return AccountsMap;
    }

    @MutationMapping("createAccount")
    public AccountInformation createAccount(@Argument CreateAccount account) throws Exception {

        List<AccountInformation>  accountInfo =  this.accountService.getAll();
       boolean isExist =  accountInfo.stream().anyMatch(x-> x.getSSN_NUMBER().equalsIgnoreCase(
               account.getSSN_NUMBER()));
        if(isExist){
            return new AccountInformation();
        }
        AccountInformation b1 = new AccountInformation();
        b1.setLoanVersion(account.getLoanVersion());
        b1.setHolderName(account.getHolderName());
        b1.setHolderAddress(account.getHolderAddress());
        b1.setFinalAmount(account.getStartingBalance());
        b1.setSSN_NUMBER(account.getSSN_NUMBER());
        return this.accountService.create(b1);
    }

    @MutationMapping("updateTransactionInAccount")
    public AccountInformation updateTransactionInAccount(@Argument AccountTransaction account) throws Exception {

        AccountInformation  accountInfo = this.accountService.get(account.getAccountId());
        Float finalAmount = 0.0F;
        if(accountInfo != null){
            if(account.getOperation().equals(AccountTransaction.OPERATIONS.CREDIT)){
                finalAmount =  accountInfo.getFinalAmount() - account.getAmount();
            }else{
                finalAmount =    accountInfo.getFinalAmount() +  account.getAmount();
            }
            if(finalAmount < 0 ){
                return accountInfo;
            }
            accountInfo.setFinalAmount(finalAmount);
            return this.accountService.create(accountInfo);
        }
        return new AccountInformation();

    }
}
