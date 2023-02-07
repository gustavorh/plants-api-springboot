package me.gustavorh.plants.controllers;

import java.lang.Iterable;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

import me.gustavorh.plants.entities.Plant;
import me.gustavorh.plants.repositories.PlantRepository;

/**
 *  Main controller used to map certain endpoints on the API.
 *  The purpose of this controller in a general way is to complete a CRUD environment, it can be of help to study and learn how to interact with different HTTP requests.
 */
@RestController
public class PlantController {

    /**
     *  Instantiation of the "Plant" repository for later use. With this initial instantiation
     *  we can access all CRUD methods (@link me.gustavorh.plants.repositories.PlantRepository).
     */
    private final PlantRepository plantRepository;

    public PlantController(final PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    /**
     * API Mapping (GET Type) to retrieve a list of all plants inside the database.
     *
     * @return list of all plants (json)
     */
    @GetMapping("/plants")
    public Iterable<Plant> getAllPlants() {
        return this.plantRepository.findAll();
    }

    /**
     * API Mapping (GET Type) to retrieve a specific plant with the given id as a parameter.
     *
     * @param id
     * @return data of specific plant (json)
     */
    @GetMapping("/plants/{id}")
    public Optional<Plant> getPlantById(@PathVariable("id") Integer id) {
        return this.plantRepository.findById(id);
    }

    /**
     * API Mapping (POST Type) to Create a new plant in the database.
     *
     * @param plant (object)
     * @return Plant object that was created
     */
    @PostMapping("/plants")
    public Plant createNewPlant(@RequestBody Plant plant) {
        Plant newPlant = this.plantRepository.save(plant);
        return newPlant;
    }

    /**
     * API Mapping (PUT Type) to Update a specific plant with the given id as parameter.
     * A plant object is also required as a parameter in order to update said plant.
     *
     * @param id
     * @param p (Plant object)
     * @return Plant object updated
     */
    @PutMapping("/plants/{id}")
    public Plant updatePlant(
            @PathVariable("id") Integer id,
            @RequestBody Plant p
    ) {
        Optional<Plant> plantToUpdateOptional = this.plantRepository.findById(id);
        if (!plantToUpdateOptional.isPresent()) {
            return null;
        }
        Plant plantToUpdate = plantToUpdateOptional.get();
        if (p.getHasFruit() != null) {
            plantToUpdate.setHasFruit(p.getHasFruit());
        }
        if (p.getQuantity() != null) {
            plantToUpdate.setQuantity(p.getQuantity());
        }
        if (p.getName() != null) {
            plantToUpdate.setName(p.getName());
        }
        if (p.getWateringFrequency() != null) {
            plantToUpdate.setWateringFrequency(p.getWateringFrequency());
        }
        Plant updatedPlant = this.plantRepository.save(plantToUpdate);
        return updatedPlant;
    }

    /**
     * API Mapping (DELETE Type) to delete a specific plant from the database with the given ID as parameter.
     *
     * @param id
     * @return Deleted plant
     */
    @DeleteMapping("/plants/{id}")
    public Plant deletePlant(@PathVariable("id") Integer id) {
        Optional<Plant> plantToDeleteOptional = this.plantRepository.findById(id);
        if (!plantToDeleteOptional.isPresent()) {
            return null;
        }
        Plant plantToDelete = plantToDeleteOptional.get();
        this.plantRepository.delete(plantToDelete);
        return plantToDelete;
    }
}