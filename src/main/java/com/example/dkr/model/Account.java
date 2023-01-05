package com.example.dkr.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(indexes = {@Index(columnList = "unp"),
                  @Index(columnList = "currCode"),
                  @Index(name = "bank_code", columnList = "bank_code")})
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Account{

    @Id
    @GeneratedValue
    int code;

    @Column
    String unp;

    @Column
    int currCode;

    @ManyToOne()
    @JoinColumn(name = "bank_code")
    Bank bank;

    public Account(String unp, int currCode, Bank bank) {
        this.unp = unp;
        this.currCode = currCode;
        this.bank = bank;
    }

    @Override
    public String toString() {
        return code + "___" + unp + "___" + currCode + "---Bank:" + (bank != null ? bank.toString() : "");
    }
}
