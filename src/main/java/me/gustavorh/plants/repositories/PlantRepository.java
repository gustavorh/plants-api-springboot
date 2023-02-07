package me.gustavorh.plants.repositories;

import org.springframework.data.repository.CrudRepository;

import me.gustavorh.plants.entities.Plant;

/**
 * Plant interface which will be used as a Repository, it extends CrudRepository as this project is aimed to be a CRUD application.
 */
public interface PlantRepository extends CrudRepository<Plant, Integer> {
}
