package me.gustavorh.plants.controllers;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.List;
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
     * Instantiation of the "Plant" repository for later use. With this initial instantiation
     * we can access all CRUD methods (@link me.gustavorh.plants.repositories.PlantRepository).
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
     * @param p  (Plant object)
     * @return Plant object updated
     */
    @PutMapping("/plants/{id}")
    public Plant updatePlant(
            @PathVariable("id") Integer id,
            @RequestBody Plant p
    ) {
        /*
         * This first part of the code will create a new Optional (as we don't know if the end-user will provide a valid ID for a plant) which will contain the
         * plant with the provided ID, if a plant is not found with said ID the application will end early returning a null statement as the plant couldn't be found.
         */
        Optional<Plant> plantToUpdateOptional = this.plantRepository.findById(id);
        if (!plantToUpdateOptional.isPresent()) {
            return null;
        }

        /*
         * In the case of a plant was found with the provided ID, we call the get() method to retrieve its information and assigning it to a new local variable called 'plantToUpdate'.
         * With this, we can later check if the passed object in the body of the request contains specific properties of the main object (Plant) and if it does, call their respective
         * getters and setters to retrieve and assign their respective information passed by the object.
         */
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

        /*
         * Once the plant has been updated with the respective data, we can save this new updated plant to the database.
         * Even though the 'updatedPlant' is redundant in this case, as we can just return the with the whole save() method,
         * I decided to keep the variable in order to see more clearly what is being done in the execution of the code.
         */
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
        /*
         * Same case as before, we create a new Optional for in the case a plant with specified ID does not exist and in the event of a plant is not found
         * we end the application early returning a null statement.
         */
        Optional<Plant> plantToDeleteOptional = this.plantRepository.findById(id);
        if (!plantToDeleteOptional.isPresent()) {
            return null;
        }
        Plant plantToDelete = plantToDeleteOptional.get();
        this.plantRepository.delete(plantToDelete);

        return plantToDelete;
    }

    /**
     * API Mapping (GET Type) to search with custom queries.
     *
     * @param hasFruit
     * @param quantity
     * @return
     */
    @GetMapping("/plants/search")
    /*
     * We have defined both params as non-required as our application has custom methods to handle whether one or both params are passed in the query.
     */
    public List<Plant> searchPlants(
            @RequestParam(name = "hasFruit", required = false) Boolean hasFruit,
            @RequestParam(name = "maxQuantity", required = false) Integer quantity
    ) {
        /*
         * Depending on the query, the application will handle all kind of queries passed as params, even if one or both params are passed.
         */
        if (hasFruit != null && quantity != null && hasFruit) {
            return this.plantRepository.findByHasFruitTrueAndQuantityLessThan(quantity);
        }
        if (hasFruit != null && quantity != null && !hasFruit) {
            return this.plantRepository.findByHasFruitFalseAndQuantityLessThan(quantity);
        }
        if (hasFruit != null && hasFruit) {
            return this.plantRepository.findByHasFruitTrue();
        }
        if (hasFruit != null && !hasFruit) {
            return this.plantRepository.findByHasFruitFalse();
        }
        if (quantity != null) {
            return this.plantRepository.findByQuantityLessThan(quantity);
        }
        return new ArrayList<>();
    }
}