package me.gustavorh.plants.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

/**
 * Plant Entity used to define a plant's basic properties, we have used Lombok in order to re-utilize and simplify code when it comes to Getters and Setters.
 * Using Lombok will make our code much more legible and decrease its length overall.
 * <p>
 * Since this project is using an H2 Database, a Table name of "PLANTS" has been defined in order to create the structure of the database.
 */
@Entity
@Getter
@Setter
@Table(name = "PLANTS")
public class Plant {

    /**
     * Since an ID is considered a unique identifier, we want to assign a new and unique ID for each time a plant is created, it will also help to identify specific plants for later use (such as Read, Update, Delete).
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Continuing with the database structure, we have defined a column name of "NAME" to store the name of a plant.
     * We have repeated this process to complete the database structure with the column names of "QUANTIY", "WATERING_FREQUENCY" and "HAS_FRUIT".
     */
    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "WATERING_FREQUENCY")
    private Integer wateringFrequency;

    @Column(name = "HAS_FRUIT")
    private Boolean hasFruit;

}