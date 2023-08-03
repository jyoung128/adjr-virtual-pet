package org.wcci.adjrvirtualpet.services;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.wcci.adjrvirtualpet.entities.OrganicCat;
import org.wcci.adjrvirtualpet.entities.OrganicDog;
import org.wcci.adjrvirtualpet.entities.OrganicShelter;
import org.wcci.adjrvirtualpet.repositories.OrganicDogRepo;
import org.wcci.adjrvirtualpet.repositories.OrganicShelterRepo;
import org.wcci.adjrvirtualpet.repositories.OrganicCatRepo;

@Service
/**
 * I contain the business logic for responding to API requests for
 * student-related requests.
 */
public class ShelterService {
    final private static Logger logger = LoggerFactory.getLogger(ShelterService.class);
    final private OrganicDogRepo organicDogRepo;
    final private OrganicCatRepo organicCatRepo;
    final private OrganicShelterRepo organicShelterRepo;

    public ShelterService(
            @Autowired final OrganicDogRepo organicDogRepo,
            @Autowired final OrganicCatRepo organicCatRepo,
            @Autowired final OrganicShelterRepo organicShelterRepo) {
        this.organicDogRepo = organicDogRepo;
        this.organicCatRepo = organicCatRepo;
        this.organicShelterRepo = organicShelterRepo;
    }

    public Stream<OrganicDog> organicDogStream() {
        final Iterable<OrganicDog> organicDogs = this.organicDogRepo.findAll();

        // Standard conversion from iterator to stream.
        return StreamSupport.stream(organicDogs.spliterator(), false);
    }

    public Stream<OrganicCat> organicCatStream() {
        final Iterable<OrganicCat> organicCats = this.organicCatRepo.findAll();

        // Standard conversion from iterator to stream.
        return StreamSupport.stream(organicCats.spliterator(), false);
    }

    public Stream<OrganicShelter> organicShelterStream() {
        final Iterable<OrganicShelter> organicShelters = this.organicShelterRepo.findAll();

        // Standard conversion from iterator to stream.
        return StreamSupport.stream(organicShelters.spliterator(), false);
    }

    public OrganicDog findOrganicDog(final long organicDog_id) {
        final Optional<OrganicDog> possiblyAOrganicDog = organicDogRepo.findById(organicDog_id);
        if (!possiblyAOrganicDog.isPresent()) {
            logger.info("OrganicDog not found: " + organicDog_id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicDog not found " + organicDog_id);
        }
        return possiblyAOrganicDog.get();
    }

    public OrganicCat findOrganicCat(final long organicCat_id) {
        final Optional<OrganicCat> possiblyAOrganicCat = organicCatRepo.findById(organicCat_id);
        if (!possiblyAOrganicCat.isPresent()) {
            logger.info("OrganicCat not found: " + organicCat_id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicCat not found " + organicCat_id);
        }
        return possiblyAOrganicCat.get();
    }

    public OrganicShelter findOrganicShelter(final long organicShelter_id) {
        final Optional<OrganicShelter> possiblyAOrganicShelter = organicShelterRepo.findById(organicShelter_id);
        if (!possiblyAOrganicShelter.isPresent()) {
            logger.info("OrganicShelter not found: " + organicShelter_id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicShelter not found " + organicShelter_id);
        }
        return possiblyAOrganicShelter.get();
    }

    public OrganicDog writeToDatabase(final OrganicDog organicDog) {
        if (organicDog.getName().contains("bad word"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, cursing not allowed");

        return organicDogRepo.save(organicDog);
    }

    public OrganicCat writeToDatabase(final OrganicCat organicCat) {
        if (organicCat.getName().contains("bad word"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, cursing not allowed");

        return organicCatRepo.save(organicCat);
    }

    public OrganicShelter writeToDatabase(final OrganicShelter organicShelter) {
        if (organicShelter.getName().contains("bad word"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, cursing not allowed");

        return organicShelterRepo.save(organicShelter);
    }


    public void deleteOrganicDogById(final long organicDog_id) {
        if (!organicDogRepo.findById(organicDog_id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicDog not found " + organicDog_id);

        organicDogRepo.deleteById(organicDog_id);
    }

    public void deleteOrganicCatById(final long organicCat_id) {
        if (!organicCatRepo.findById(organicCat_id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicCat not found " + organicCat_id);

        organicCatRepo.deleteById(organicCat_id);
    }

    public void deleteOrganicShelterById(final long organicShelter_id) {
        if (!organicShelterRepo.findById(organicShelter_id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicShelter not found " + organicShelter_id);

        organicShelterRepo.deleteById(organicShelter_id);
    }

    public OrganicDog updateOrganicDog(OrganicDog organicDog, long organicDog_id) {
        final OrganicDog databaseOrganicDog = findOrganicDog(organicDog_id);

        if (organicDog_id != databaseOrganicDog.getPetID())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sorry, you may not change the organicDog_id");

        // Copy the non-ID info from the requestbody to the database object
        databaseOrganicDog.setName(organicDog.getName());
        databaseOrganicDog.setAge(organicDog.getAgeInDays());
        databaseOrganicDog.setHunger(organicDog.getHunger());
        databaseOrganicDog.setThirst(organicDog.getThirst());
        databaseOrganicDog.setEnergy(organicDog.getEnergy());
        databaseOrganicDog.setMood(organicDog.getMood());

        // Ask the repo to write the modified student to MySQL (or whatever)
        writeToDatabase(databaseOrganicDog);

        return databaseOrganicDog;
    }

    public OrganicCat updateOrganicCat(OrganicCat organicCat, long organicCat_id) {
        final OrganicCat databaseOrganicCat = findOrganicCat(organicCat_id);

        if (organicCat_id != databaseOrganicCat.getPetID())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sorry, you may not change the organicCat_id");

        // Copy the non-ID info from the requestbody to the database object
        databaseOrganicCat.setName(organicCat.getName());

        // Ask the repo to write the modified student to MySQL (or whatever)
        writeToDatabase(databaseOrganicCat);

        return databaseOrganicCat;
    }

    public OrganicShelter updateOrganicShelter(OrganicShelter organicShelter, long organicShelter_id) {
        final OrganicShelter databaseOrganicShelter = findOrganicShelter(organicShelter_id);

        if (organicShelter_id != databaseOrganicShelter.getShelterID())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sorry, you may not change the organicShelter_id");

        // Copy the non-ID info from the requestbody to the database object
        databaseOrganicShelter.setName(organicShelter.getName());

        // Ask the repo to write the modified student to MySQL (or whatever)
        writeToDatabase(databaseOrganicShelter);

        return databaseOrganicShelter;
    }
}
