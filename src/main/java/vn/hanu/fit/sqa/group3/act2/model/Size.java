package vn.hanu.fit.sqa.group3.act2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Example: "Small", "Medium", "Large"
    private int counts; // Number of available pizzas for this size

    // Other size-related attributes (e.g., price)

    // Getters and setters
}
