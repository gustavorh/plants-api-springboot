package me.gustavorh.plants.repositories;

import org.springframework.data.repository.CrudRepository;

import me.gustavorh.plants.entities.Plant;

import java.util.List;

/**
 * Plant interface which will be used as a Repository, it extends CrudRepository as this project is aimed to be a CRUD application.
 */
public interface PlantRepository extends CrudRepository<Plant, Integer> {
    /**
     * We have declared custom query methods with JPA, the naming convention is based on the official Spring Boot documentation
     * @link https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     *
     * @return query responses
     */
    List<Plant> findByHasFruitTrue();
    List<Plant> findByHasFruitFalse();
    List<Plant> findByQuantityLessThan(Integer quantity);
    List<Plant> findByHasFruitTrueAndQuantityLessThan(Integer quantity);
    List<Plant> findByHasFruitFalseAndQuantityLessThan(Integer quantity);
}
