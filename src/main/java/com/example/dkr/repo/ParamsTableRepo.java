package com.example.dkr.repo;

import com.example.dkr.model.ParamsTable;
import com.example.dkr.model.WorkTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParamsTableRepo extends JpaRepository<ParamsTable, Integer> {

    @Query
    List<ParamsTable> findAllByWorkTableCode(int code);

}
