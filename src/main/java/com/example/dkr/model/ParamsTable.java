package com.example.dkr.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(indexes = @Index( name = "workTable_code", columnList = "workTable_code"))
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ParamsTable {

    @Id
    @GeneratedValue
    int code;

    @ManyToOne()
    @JoinColumn(name = "workTable_code")
    WorkTable workTable;

    @Column
    String name;

    @Column
    String value;

    @Column
    Date dateCreate;

    public ParamsTable(String name, String value, Date dateCreate) {
        this.name = name;
        this.value = value;
        this.dateCreate = dateCreate;
    }

    @Override
    public String toString() {
        return "code:" + code + "/name:" + name + "/value" + value + "/dateCreate" + dateCreate;
    }
}
