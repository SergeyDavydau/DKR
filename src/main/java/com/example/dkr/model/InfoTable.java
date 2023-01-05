package com.example.dkr.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(indexes = @Index(name = "workTable_info_index", columnList = "workTable_code"))
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InfoTable {

    @Id
    @GeneratedValue
    int code;

    @OneToOne()
    @JoinColumn(name = "workTable_code")
    WorkTable workTable;

    @Column
    String part;

    @Column
    String description;

    public InfoTable(String part, String description) {
        this.part = part;
        this.description = description;
    }

    @Override
    public String toString() {
        return "code:" + code + "/part:" + part + "/description" + description;
    }
}