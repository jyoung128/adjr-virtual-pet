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

import org.wcci.adjrvirtualpet.entities.OrganicCat;
import org.wcci.adjrvirtualpet.entities.OrganicDog;
import org.wcci.adjrvirtualpet.entities.OrganicShelter;
import org.wcci.adjrvirtualpet.services.ShelterService;

@RestController
public class ShelterRestController {
    public static final String LIST_ALL_ORGANIC_DOGS = "listAllOrganicDogs";
    public static final String LIST_ALL_ORGANIC_CATS = "listAllOrganicCats";

    final private ShelterService shelterService;

    public ShelterRestController(@Autowired ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/api/organicDogs")
    public CollectionModel<EntityModel<OrganicDog>> getOrganicDogs() {
        List<EntityModel<OrganicDog>> organicDogs = this.shelterService.organicDogStream()
                .map(organicDog -> EntityModel.of(organicDog))
                .collect(Collectors.toList());
        return CollectionModel.of(organicDogs);
    }

    @GetMapping("/api/organicCats")
    public CollectionModel<EntityModel<OrganicCat>> getOrganicCats() {
        List<EntityModel<OrganicCat>> organicCats = this.shelterService.organicCatStream()
                .map(organicCat -> EntityModel.of(organicCat))
                .collect(Collectors.toList());
        return CollectionModel.of(organicCats);
    }

    @GetMapping("/api/organicDogs/{organicDog_id}")
    public EntityModel<OrganicDog> getOrganicDog(@PathVariable final Long organicDog_id) {
        final OrganicDog organicDog = shelterService.findOrganicDog(organicDog_id);
        return EntityModel.of(organicDog,
                linkTo(methodOn(ShelterRestController.class).getOrganicDogs()).withRel(LIST_ALL_ORGANIC_DOGS),
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicDog_id)).withSelfRel());
    }

    @GetMapping("/api/organicCats/{organicCat_id}")
    public EntityModel<OrganicCat> getOrganicCat(@PathVariable final Long organicCat_id) {
        final OrganicCat organicCat = shelterService.findOrganicCat(organicCat_id);
        return EntityModel.of(organicCat,
                linkTo(methodOn(ShelterRestController.class).getOrganicCats()).withRel(LIST_ALL_ORGANIC_CATS),
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicCat_id)).withSelfRel());
    }

    @PostMapping("/api/organicDogs")
    public EntityModel<OrganicDog> newOrganicDog(@RequestBody final OrganicDog organicDog) {
        return EntityModel.of(shelterService.writeToDatabase(organicDog),
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicDog.getPetID())).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getOrganicDogs()).withRel(LIST_ALL_ORGANIC_DOGS));
    }

    @PostMapping("/api/organicCats")
    public EntityModel<OrganicCat> newOrganicCat(@RequestBody final OrganicCat organicCat) {
        return EntityModel.of(shelterService.writeToDatabase(organicCat),
                linkTo(methodOn(ShelterRestController.class).getOrganicCat(organicCat.getPetID())).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getOrganicCats()).withRel(LIST_ALL_ORGANIC_CATS));
    }

    @DeleteMapping("/api/organicDogs/{organicDog_id}")
    public void deleteOrganicDog(@PathVariable long organicDog_id) {
        shelterService.deleteOrganicDogById(organicDog_id);
    }

    @DeleteMapping("/api/organicCats/{organicCat_id}")
    public void deleteOrganicCat(@PathVariable long organicCat_id) {
        shelterService.deleteOrganicCatById(organicCat_id);
    }

    // Talk to the Product Owner before changing this
    @DeleteMapping("/api/organicDogs")
    public void deleteAllOrganicDogs() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/api/organicCats")
    public void deleteAllOrganicCats() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/api/organicDogs/{organicDog_id}")
    public EntityModel<OrganicDog> updateOrganicDog(
            @PathVariable final long organicDog_id, // the name of the parameter (organicDog_id) must match "{organicDog_id}" in
                                                 // the line above
            @RequestBody final OrganicDog organicDog) {
        // Update the organicDog if that is the right thing to do
        final OrganicDog databaseOrganicDog = shelterService.updateOrganicDog(organicDog, organicDog_id);

        // Return the modified database organicDog
        return EntityModel.of(databaseOrganicDog,
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicDog.getPetID())).withSelfRel());
    }

    @PutMapping("/api/organicCats/{organicCat_id}")
    public EntityModel<OrganicCat> updateOrganicCat(
            @PathVariable final long organicCat_id,
            @RequestBody final OrganicCat organicCat) {
        final OrganicCat databaseOrganicCat = shelterService.updateOrganicCat(organicCat, organicCat_id);

        return EntityModel.of(databaseOrganicCat,
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicCat.getPetID())).withSelfRel());
    }
}
