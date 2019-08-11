package com.jdub1.model;

/*
 Skladamy zamowienie w restauracji

 Na zamowieniu jest informacja co zamawiamy - jedno pole z opisem zamowienia.
 dodajemy godzine zlozenia zamowienia, oraz godzine dostarczenia zamowienia

 w zamowieniu musi byc podana ilosc (ile osob) oraz numer stolika

 Kwota zaplacona
 kwota rachynku
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="rOrder")
public class Order implements IBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @CreationTimestamp
    private LocalDateTime timeOrdered;
    private LocalDateTime timeDelivered;


    private int peopleCount;

    private int tableNumber;

    private double toPay;
    private Double paid;
}
