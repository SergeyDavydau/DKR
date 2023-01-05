package com.example.dkr.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table()
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Dictionary {

    @Id
    @GeneratedValue
    int code;

    @Column
    String name;

    @Column
    String value;

    public Dictionary(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + "___" + value;
    }

}
