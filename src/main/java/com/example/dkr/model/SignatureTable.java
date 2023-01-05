package com.example.dkr.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(name = "workTable_sign_index", columnList = "workTable_code"))
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class SignatureTable {

    @Id
    @GeneratedValue
    int code;

    @OneToOne()
    @JoinColumn(name = "workTable_code")
    WorkTable workTable;

    @Column
    int type;

    @Column
    String report;

    public SignatureTable(int type, String report) {
        this.type = type;
        this.report = report;
    }

    @Override
    public String toString() {
        return "code:" + code + "/type:" + type + "/report" + report;
    }
}
