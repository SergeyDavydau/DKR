package com.example.dkr.repo;

import com.example.dkr.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Integer> {
    @Modifying
    @Transactional
    @Query
    int deleteAllByBankCode(int code);

    @Query
    Account findByCode (int code);

    @Query
    List<Account> findAllByBank_Name(String bankName);
}
