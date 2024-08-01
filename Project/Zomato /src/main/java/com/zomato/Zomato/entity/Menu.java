package com.zomato.Zomato.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;

@Entity
@Data
public class Menu {
    @Id
    @GeneratedValue
    private  Long  id;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private HashMap<String,Double> menuCard;
}
