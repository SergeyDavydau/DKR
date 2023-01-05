package com.example.dkr.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(indexes = @Index( name = "adress_code", columnList = "adress_code"))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Client{

   @Id
   @GeneratedValue
   int code;

   @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @JoinColumn(name = "adress_code")
   Adress adress;

   @Column
   String name;

   @Column
   String surName;

   @Override
   public String toString() {
      return name + "___" + surName;
   }

}
