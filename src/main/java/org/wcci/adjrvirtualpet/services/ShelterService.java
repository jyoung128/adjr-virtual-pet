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

import org.wcci.adjrvirtualpet.entities.Cat;
import org.wcci.adjrvirtualpet.entities.Dog;
import org.wcci.adjrvirtualpet.repositories.DogRepo;
import org.wcci.adjrvirtualpet.repositories.CatRepo;

@Service
/**
 * I contain the business logic for responding to API requests for
 * student-related requests.
 */
public class ShelterService {
    final private static Logger logger = LoggerFactory.getLogger(ShelterService.class);
    final private DogRepo dogRepo;
    final private CatRepo catRepo;

    public ShelterService(
            @Autowired final DogRepo dogRepo,
            @Autowired final CatRepo catRepo) {
        this.dogRepo = dogRepo;
        this.catRepo = catRepo;
    }

    public Stream<Dog> dogStream() {
        final Iterable<Dog> dogs = this.dogRepo.findAll();

        // Standard conversion from iterator to stream.
        return StreamSupport.stream(dogs.spliterator(), false);
    }

    public Stream<Cat> catStream() {
        final Iterable<Cat> cats = this.catRepo.findAll();

        // Standard conversion from iterator to stream.
        return StreamSupport.stream(cats.spliterator(), false);
    }

    public Dog findDog(final long dog_id) {
        final Optional<Dog> possiblyADog = dogRepo.findById(dog_id);
        if (!possiblyADog.isPresent()) {
            logger.info("Dog not found: " + dog_id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dog not found " + dog_id);
        }
        return possiblyADog.get();
    }

    public Cat findCat(final long cat_id) {
        final Optional<Cat> possiblyACat = catRepo.findById(cat_id);
        if (!possiblyACat.isPresent()) {
            logger.info("Cat not found: " + cat_id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat not found " + cat_id);
        }
        return possiblyACat.get();
    }

    public Dog writeToDatabase(final Dog dog) {
        if (dog.getName().contains("bad word"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, cursing not allowed");

        return dogRepo.save(dog);
    }

    public Cat writeToDatabase(final Cat cat) {
        if (cat.getName().contains("bad word"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, cursing not allowed");

        return catRepo.save(cat);
    }


    public void deleteDogById(final long dog_id) {
        if (!dogRepo.findById(dog_id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dog not found " + dog_id);

        dogRepo.deleteById(dog_id);
    }

    public void deleteCatById(final long cat_id) {
        if (!catRepo.findById(cat_id).isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cat not found " + cat_id);

        catRepo.deleteById(cat_id);
    }

    public Dog updateDog(Dog dog, long dog_id) {
        final Dog databaseDog = findDog(dog_id);

        if (dog_id != databaseDog.dogID)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sorry, you may not change the dog_id");

        // Copy the non-ID info from the requestbody to the database object
        databaseDog.setName(dog.getName());

        // Ask the repo to write the modified student to MySQL (or whatever)
        writeToDatabase(databaseDog);

        return databaseDog;
    }

    public Cat updateCat(Cat cat, long cat_id) {
        final Cat databaseCat = findCat(cat_id);

        if (cat_id != databaseCat.catID)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sorry, you may not change the cat_id");

        // Copy the non-ID info from the requestbody to the database object
        databaseCat.setName(cat.getName());

        // Ask the repo to write the modified student to MySQL (or whatever)
        writeToDatabase(databaseCat);

        return databaseCat;
    }
}
