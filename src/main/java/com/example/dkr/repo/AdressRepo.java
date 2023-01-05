package com.example.dkr.repo;

import com.example.dkr.model.Adress;
import org.hibernate.annotations.Synchronize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdressRepo extends JpaRepository<Adress, Integer> {

    @Query
    List<Adress> getAdressByCountry(String country);

    @Override
    void deleteAll(Iterable<? extends Adress> entities);
}

