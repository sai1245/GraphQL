package com.saikrishna.demo.pgraphql;

import com.saikrishna.demo.pgraphql.entity.AccountInformation;
import com.saikrishna.demo.pgraphql.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class PgraphqlApplication implements CommandLineRunner {

	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(PgraphqlApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		AccountInformation b1 = new AccountInformation();
		b1.setLoanVersion("PFA");
		b1.setHolderName("Test1");
		b1.setHolderAddress("Address1");
		b1.setFinalAmount(55.66F);
		b1.setSSN_NUMBER("SS1");


		AccountInformation b2 = new AccountInformation();
		b2.setLoanVersion("RPL");
		b2.setHolderName("Test2");
		b2.setHolderAddress("Address2");
		b2.setFinalAmount(55.66F);
		b2.setSSN_NUMBER("SS2");


		AccountInformation b3 = new AccountInformation();
		b3.setLoanVersion("RPL");
		b3.setHolderName("Test3");
		b3.setHolderAddress("Address3");
		b3.setFinalAmount(55.66F);
		b3.setSSN_NUMBER("SS3");

		AccountInformation b4 = new AccountInformation();
		b4.setLoanVersion("PFA");
		b4.setHolderName("Test4");
		b4.setHolderAddress("Address4");
		b4.setFinalAmount(55.66F);
		b4.setSSN_NUMBER("SS4");



		this.accountService.create(b1);
		this.accountService.create(b2);
		this.accountService.create(b3);
		this.accountService.create(b4);




	}

}
