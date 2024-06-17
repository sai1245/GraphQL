package com.prathyusha.demo.pgraphql.repository;


import com.prathyusha.demo.pgraphql.entity.AccountInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailsRepository extends JpaRepository<AccountInformation, Integer> {
}
