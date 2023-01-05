package com.example.dkr.repo;

import com.example.dkr.model.WorkTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkTableRepo extends JpaRepository<WorkTable, Integer> {

    @Query("SELECT w FROM WorkTable w JOIN FETCH w.signatureTable JOIN FETCH w.infoTable JOIN FETCH w.paramsTables " +
            "WHERE w.signatureTable.type = ?1 AND  w.infoTable.part = ?2 AND (SELECT COUNT(p.code) FROM ParamsTable p WHERE p.workTable.code = w.code AND p.name = ?3) > 0")
    List<WorkTable> findAllBySignatureTableTypeAndInfoTablePartAndParamTableName(int signatureType, String infoPart, String paramName);
}
