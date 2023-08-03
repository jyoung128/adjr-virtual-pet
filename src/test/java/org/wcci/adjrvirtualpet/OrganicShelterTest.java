package org.wcci.adjrvirtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
// import org.wcci.adjrvirtualpet.OrganicShelter;
import org.wcci.adjrvirtualpet.entities.OrganicCat;
import org.wcci.adjrvirtualpet.entities.OrganicDog;
import org.wcci.adjrvirtualpet.entities.OrganicShelter;
import org.wcci.adjrvirtualpet.services.ShelterService;

public class OrganicShelterTest {
    @Test
    void testAddPetToShelter() {
        OrganicShelter shelter = new OrganicShelter();
        OrganicDog dog = new OrganicDog("Fred");
        shelter.addDog(dog);
        assertEquals(dog, shelter.getDog("Fred"));
    }

    @Test
    void testAdoptOutPet() {
        OrganicShelter shelter = new OrganicShelter();
        OrganicDog pet1 = new OrganicDog("Fred");
        shelter.removeDog(pet1);
        assertEquals(null, shelter.getDog("Fred"));
    }

    @Test
    void testGetShelterID() {
        OrganicShelter shelter = new OrganicShelter();
        assertEquals(0, shelter.getShelterID());
    }

    @Test
    void testSetGetName() {
        OrganicShelter shelter = new OrganicShelter();
        shelter.setName("pound");
        assertEquals("pound", shelter.getName());
    }
}
