package com.example.dkr.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
//@Table(indexes = @Index(name = "client_code", columnList = "client_code"))
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Adress {
    @Id
    @GeneratedValue
    int code;

    @Column
    String country;

    @Column
    String city;

    @Column
    String street;

    @OneToOne(mappedBy = "adress", cascade = CascadeType.ALL)
    Client client;

    @Override
    public String toString() {
        return country + "___" + city + "___" + street + "---Client:" + client.toString();
    }

}
