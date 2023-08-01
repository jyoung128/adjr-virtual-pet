package org.wcci.adjrvirtualpet.restControllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.wcci.adjrvirtualpet.entities.Cat;
import org.wcci.adjrvirtualpet.entities.Dog;
import org.wcci.adjrvirtualpet.services.ShelterService;

@RestController
public class ShelterRestController {
    public static final String LIST_ALL_DOGS = "listAllDogs";
    public static final String LIST_ALL_CATS = "listAllCats";

    final private ShelterService shelterService;

    public ShelterRestController(@Autowired ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/api/dogs")
    public CollectionModel<EntityModel<Dog>> getDogs() {
        List<EntityModel<Dog>> dogs = this.shelterService.dogStream()
                .map(dog -> EntityModel.of(dog))
                .collect(Collectors.toList());
        return CollectionModel.of(dogs);
    }

    @GetMapping("/api/cats")
    public CollectionModel<EntityModel<Cat>> getCats() {
        List<EntityModel<Cat>> cats = this.shelterService.catStream()
                .map(cat -> EntityModel.of(cat))
                .collect(Collectors.toList());
        return CollectionModel.of(cats);
    }

    @GetMapping("/api/dogs/{dog_id}")
    public EntityModel<Dog> getDog(@PathVariable final Long dog_id) {
        final Dog dog = shelterService.findDog(dog_id);
        return EntityModel.of(dog,
                linkTo(methodOn(ShelterRestController.class).getDogs()).withRel(LIST_ALL_DOGS),
                linkTo(methodOn(ShelterRestController.class).getDog(dog_id)).withSelfRel());
    }

    @GetMapping("/api/cats/{cat_id}")
    public EntityModel<Cat> getCat(@PathVariable final Long cat_id) {
        final Cat cat = shelterService.findCat(cat_id);
        return EntityModel.of(cat,
                linkTo(methodOn(ShelterRestController.class).getCats()).withRel(LIST_ALL_CATS),
                linkTo(methodOn(ShelterRestController.class).getDog(cat_id)).withSelfRel());
    }

    @PostMapping("/api/dogs")
    public EntityModel<Dog> newDog(@RequestBody final Dog dog) {
        return EntityModel.of(shelterService.writeToDatabase(dog),
                linkTo(methodOn(ShelterRestController.class).getDog(dog.petID)).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getDogs()).withRel(LIST_ALL_DOGS));
    }

    @PostMapping("/api/cats")
    public EntityModel<Cat> newCat(@RequestBody final Cat cat) {
        return EntityModel.of(shelterService.writeToDatabase(cat),
                linkTo(methodOn(ShelterRestController.class).getCat(cat.petID)).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getCats()).withRel(LIST_ALL_CATS));
    }

    @DeleteMapping("/api/dogs/{dog_id}")
    public void deleteDog(@PathVariable long dog_id) {
        shelterService.deleteDogById(dog_id);
    }

    @DeleteMapping("/api/cats/{cat_id}")
    public void deleteCat(@PathVariable long cat_id) {
        shelterService.deleteCatById(cat_id);
    }

    // Talk to the Product Owner before changing this
    @DeleteMapping("/api/dogs")
    public void deleteAllDogs() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/api/cats")
    public void deleteAllCats() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/api/dogs/{dog_id}")
    public EntityModel<Dog> updateDog(
            @PathVariable final long dog_id, // the name of the parameter (dog_id) must match "{dog_id}" in
                                                 // the line above
            @RequestBody final Dog dog) {
        // Update the dog if that is the right thing to do
        final Dog databaseDog = shelterService.updateDog(dog, dog_id);

        // Return the modified database dog
        return EntityModel.of(databaseDog,
                linkTo(methodOn(ShelterRestController.class).getDog(dog.petID)).withSelfRel());
    }

    @PutMapping("/api/cats/{cat_id}")
    public EntityModel<Cat> updateCat(
            @PathVariable final long cat_id,
            @RequestBody final Cat cat) {
        final Cat databaseCat = shelterService.updateCat(cat, cat_id);

        return EntityModel.of(databaseCat,
                linkTo(methodOn(ShelterRestController.class).getDog(cat.petID)).withSelfRel());
    }
}
