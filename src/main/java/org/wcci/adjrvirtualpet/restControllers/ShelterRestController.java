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
import org.wcci.adjrvirtualpet.services.ShelterService;

@RestController
public class ShelterRestController {
    public static final String LIST_ALL_ORGANIC_DOGS = "listAllOrganicDogs";
    public static final String LIST_ALL_ORGANIC_CATS = "listAllOrganicCats";

    final private ShelterService shelterService;

    public ShelterRestController(@Autowired ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/api/organicOrganicDogs")
    public CollectionModel<EntityModel<OrganicDog>> getOrganicDogs() {
        List<EntityModel<OrganicDog>> organicOrganicDogs = this.shelterService.organicOrganicDogStream()
                .map(organicOrganicDog -> EntityModel.of(organicOrganicDog))
                .collect(Collectors.toList());
        return CollectionModel.of(organicOrganicDogs);
    }

    @GetMapping("/api/organicOrganicCats")
    public CollectionModel<EntityModel<OrganicCat>> getOrganicCats() {
        List<EntityModel<OrganicCat>> organicOrganicCats = this.shelterService.organicOrganicCatStream()
                .map(organicOrganicCat -> EntityModel.of(organicOrganicCat))
                .collect(Collectors.toList());
        return CollectionModel.of(organicOrganicCats);
    }

    @GetMapping("/api/organicOrganicDogs/{organicOrganicDog_id}")
    public EntityModel<OrganicDog> getOrganicDog(@PathVariable final Long organicOrganicDog_id) {
        final OrganicDog organicOrganicDog = shelterService.findOrganicDog(organicOrganicDog_id);
        return EntityModel.of(organicOrganicDog,
                linkTo(methodOn(ShelterRestController.class).getOrganicDogs()).withRel(LIST_ALL_ORGANIC_DOGS),
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicOrganicDog_id)).withSelfRel());
    }

    @GetMapping("/api/organicOrganicCats/{organicOrganicCat_id}")
    public EntityModel<OrganicCat> getOrganicCat(@PathVariable final Long organicOrganicCat_id) {
        final OrganicCat organicOrganicCat = shelterService.findOrganicCat(organicOrganicCat_id);
        return EntityModel.of(organicOrganicCat,
                linkTo(methodOn(ShelterRestController.class).getOrganicCats()).withRel(LIST_ALL_ORGANIC_CATS),
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicOrganicCat_id)).withSelfRel());
    }

    @PostMapping("/api/organicOrganicDogs")
    public EntityModel<OrganicDog> newOrganicDog(@RequestBody final OrganicDog organicOrganicDog) {
        return EntityModel.of(shelterService.writeToDatabase(organicOrganicDog),
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicOrganicDog.petID)).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getOrganicDogs()).withRel(LIST_ALL_ORGANIC_DOGS));
    }

    @PostMapping("/api/organicOrganicCats")
    public EntityModel<OrganicCat> newOrganicCat(@RequestBody final OrganicCat organicOrganicCat) {
        return EntityModel.of(shelterService.writeToDatabase(organicOrganicCat),
                linkTo(methodOn(ShelterRestController.class).getOrganicCat(organicOrganicCat.petID)).withSelfRel(),
                linkTo(methodOn(ShelterRestController.class).getOrganicCats()).withRel(LIST_ALL_ORGANIC_CATS));
    }

    @DeleteMapping("/api/organicOrganicDogs/{organicOrganicDog_id}")
    public void deleteOrganicDog(@PathVariable long organicOrganicDog_id) {
        shelterService.deleteOrganicDogById(organicOrganicDog_id);
    }

    @DeleteMapping("/api/organicOrganicCats/{organicOrganicCat_id}")
    public void deleteOrganicCat(@PathVariable long organicOrganicCat_id) {
        shelterService.deleteOrganicCatById(organicOrganicCat_id);
    }

    // Talk to the Product Owner before changing this
    @DeleteMapping("/api/organicOrganicDogs")
    public void deleteAllOrganicDogs() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/api/organicOrganicCats")
    public void deleteAllOrganicCats() {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/api/organicOrganicDogs/{organicOrganicDog_id}")
    public EntityModel<OrganicDog> updateOrganicDog(
            @PathVariable final long organicOrganicDog_id, // the name of the parameter (organicOrganicDog_id) must match "{organicOrganicDog_id}" in
                                                 // the line above
            @RequestBody final OrganicDog organicOrganicDog) {
        // Update the organicOrganicDog if that is the right thing to do
        final OrganicDog databaseOrganicDog = shelterService.updateOrganicDog(organicOrganicDog, organicOrganicDog_id);

        // Return the modified database organicOrganicDog
        return EntityModel.of(databaseOrganicDog,
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicOrganicDog.petID)).withSelfRel());
    }

    @PutMapping("/api/organicOrganicCats/{organicOrganicCat_id}")
    public EntityModel<OrganicCat> updateOrganicCat(
            @PathVariable final long organicOrganicCat_id,
            @RequestBody final OrganicCat organicOrganicCat) {
        final OrganicCat databaseOrganicCat = shelterService.updateOrganicCat(organicOrganicCat, organicOrganicCat_id);

        return EntityModel.of(databaseOrganicCat,
                linkTo(methodOn(ShelterRestController.class).getOrganicDog(organicOrganicCat.petID)).withSelfRel());
    }
}
