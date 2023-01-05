package com.example.dkr.repo;

import com.example.dkr.model.SignatureTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureTableRepo extends JpaRepository<SignatureTable, Integer> {
}
