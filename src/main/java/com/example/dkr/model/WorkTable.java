package com.example.dkr.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(columnList = "infoTable_code"),
                  @Index(columnList = "signatureTable_code")})
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkTable {
    @Id
    @GeneratedValue
    int code;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "infoTable_code")
    InfoTable infoTable;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "signatureTable_code")
    SignatureTable signatureTable;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "workTable")
    List<ParamsTable> paramsTables = new ArrayList<>();

    public WorkTable(WorkTable w){
        this.code = w.getCode();
        this.infoTable = w.getInfoTable();
        this.signatureTable = w.getSignatureTable();
        this.paramsTables = w.getParamsTables();
    }

}
