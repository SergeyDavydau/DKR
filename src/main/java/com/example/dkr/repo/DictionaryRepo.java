package com.example.dkr.repo;


import com.example.dkr.model.Dictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface DictionaryRepo extends JpaRepository<Dictionary, Integer>, JpaSpecificationExecutor<Dictionary> {

    @Query
    Dictionary findDictionaryByCode(int code);

    @Modifying
    @Transactional
    @Query
    int deleteByCode(int code);
}
