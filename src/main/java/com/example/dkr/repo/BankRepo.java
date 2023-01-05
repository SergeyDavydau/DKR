package com.example.dkr.repo;

import com.example.dkr.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepo extends JpaRepository<Bank, Integer> {



}
