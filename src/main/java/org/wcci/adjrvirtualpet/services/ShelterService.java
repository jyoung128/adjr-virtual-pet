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
import org.wcci.adjrvirtualpet.repositories.OrganicDogRepo;
import org.wcci.adjrvirtualpet.repositories.OrganicCatRepo;

@Service
/**
 * I contain the business logic for responding to API requests for
 * student-related requests.
 */
public class ShelterService {
    final private static Logger logger = LoggerFactory.getLogger(ShelterService.class);
    final private OrganicDogRepo organicOrganicDogRepo;
    final private OrganicCatRepo organicOrganicCatRepo;

    public ShelterService(
            @Autowired final OrganicDogRepo organicOrganicDogRepo,
            @Autowired final OrganicCatRepo organicOrganicCatRepo) {
        this.organicOrganicDogRepo = organicOrganicDogRepo;
        this.organicOrganicCatRepo = organicOrganicCatRepo;
    }

    public Stream<OrganicDog> organicOrganicDogStream() {
        final Iterable<OrganicDog> organicOrganicDogs = this.organicOrganicDogRepo.findAll();

        // Standard conversion from iterator to stream.
        return StreamSupport.stream(organicOrganicDogs.spliterator(), false);
    }

    public Stream<OrganicCat> organicOrganicCatStream() {
        final Iterable<OrganicCat> organicOrganicCats = this.organicOrganicCatRepo.findAll();

        // Standard conversion from iterator to stream.
        return StreamSupport.stream(organicOrganicCats.spliterator(), false);
    }

    public OrganicDog findOrganicDog(final long organicOrganicDog_id) {
        final Optional<OrganicDog> possiblyAOrganicDog = organicOrganicDogRepo.findById(organicOrganicDog_id);
        if (!possiblyAOrganicDog.isPresent()) {
            logger.info("OrganicDog not found: " + organicOrganicDog_id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicDog not found " + organicOrganicDog_id);
        }
        return possiblyAOrganicDog.get();
    }

    public OrganicCat findOrganicCat(final long organicOrganicCat_id) {
        final Optional<OrganicCat> possiblyAOrganicCat = organicOrganicCatRepo.findById(organicOrganicCat_id);
        if (!possiblyAOrganicCat.isPresent()) {
            logger.info("OrganicCat not found: " + organicOrganicCat_id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicCat not found " + organicOrganicCat_id);
        }
        return possiblyAOrganicCat.get();
    }

    public OrganicDog writeToDatabase(final OrganicDog organicOrganicDog) {
        if (organicOrganicDog.getName().contains("bad word"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, cursing not allowed");

        return organicOrganicDogRepo.save(organicOrganicDog);
    }

    public OrganicCat writeToDatabase(final OrganicCat organicOrganicCat) {
        if (organicOrganicCat.getName().contains("bad word"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, cursing not allowed");

        return organicOrganicCatRepo.save(organicOrganicCat);
    }


    public void deleteOrganicDogById(final long organicOrganicDog_id) {
        if (!organicOrganicDogRepo.findById(organicOrganicDog_id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicDog not found " + organicOrganicDog_id);

        organicOrganicDogRepo.deleteById(organicOrganicDog_id);
    }

    public void deleteOrganicCatById(final long organicOrganicCat_id) {
        if (!organicOrganicCatRepo.findById(organicOrganicCat_id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OrganicCat not found " + organicOrganicCat_id);

        organicOrganicCatRepo.deleteById(organicOrganicCat_id);
    }

    public OrganicDog updateOrganicDog(OrganicDog organicOrganicDog, long organicOrganicDog_id) {
        final OrganicDog databaseOrganicDog = findOrganicDog(organicOrganicDog_id);

        if (organicOrganicDog_id != databaseOrganicDog.getPetID())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sorry, you may not change the organicOrganicDog_id");

        // Copy the non-ID info from the requestbody to the database object
        databaseOrganicDog.setName(organicOrganicDog.getName());

        // Ask the repo to write the modified student to MySQL (or whatever)
        writeToDatabase(databaseOrganicDog);

        return databaseOrganicDog;
    }

    public OrganicCat updateOrganicCat(OrganicCat organicOrganicCat, long organicOrganicCat_id) {
        final OrganicCat databaseOrganicCat = findOrganicCat(organicOrganicCat_id);

        if (organicOrganicCat_id != databaseOrganicCat.getPetID())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sorry, you may not change the organicOrganicCat_id");

        // Copy the non-ID info from the requestbody to the database object
        databaseOrganicCat.setName(organicOrganicCat.getName());

        // Ask the repo to write the modified student to MySQL (or whatever)
        writeToDatabase(databaseOrganicCat);

        return databaseOrganicCat;
    }
}
