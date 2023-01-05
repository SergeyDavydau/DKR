package com.example.dkr.repo;

import com.example.dkr.model.Client;
import org.hibernate.annotations.JoinFormula;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Integer> {

    @Query
    List<Client> getClientByAdress_Country(String city);
}
