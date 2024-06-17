package com.prathyusha.demo.pgraphql.controller;

import com.prathyusha.demo.pgraphql.entity.AccountInformation;
import com.prathyusha.demo.pgraphql.entity.graphql.AccountTransaction;
import com.prathyusha.demo.pgraphql.entity.graphql.CreateAccount;
import com.prathyusha.demo.pgraphql.services.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
    public List<Optional<AccountInformation>> getAccounts(@Argument List<Integer> accountIds, @Argument String loanVersion) {
        return this.accountService.getAccounts(accountIds, loanVersion);
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
