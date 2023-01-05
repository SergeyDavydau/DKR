package com.example.dkr.repo.filter;

import com.example.dkr.model.Dictionary;
import org.springframework.data.jpa.domain.Specification;

public abstract class DictionaryFilter{

    public static Specification<Dictionary> filterByCodeBetween (int codeStart, int codeFinish){
        return (root, query, criteriaBuilder)
                    ->
                   criteriaBuilder.between(root.get("code"), codeStart, codeFinish);
    }

}
