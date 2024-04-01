package vn.hanu.fit.sqa.group3.act2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Topping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Example: "Pepperoni", "Mushroom", "Seafood"
    private int counts; // Number of available toppings

}