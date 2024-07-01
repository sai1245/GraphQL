package com.saikrishna.demo.pgraphql.repository;


import com.saikrishna.demo.pgraphql.entity.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailsRepository extends JpaRepository<AccountInformation, Integer> {
}
