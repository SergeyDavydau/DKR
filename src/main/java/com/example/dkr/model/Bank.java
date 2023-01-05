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
@Table(indexes = {@Index(columnList = "name"),
                  @Index(columnList = "rate")})
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Bank {

    @Id
    @GeneratedValue
    int code;

    @Column
    String name;

    @Column
    int rate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bank")
    List<Account> accounts = new ArrayList<>();

    public Bank(String name, int rate) {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return name + "___" + code + "___" + rate;
    }
}
