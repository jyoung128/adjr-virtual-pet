package org.wcci.adjrvirtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.wcci.adjrvirtualpet.RoboticCat;
import org.wcci.adjrvirtualpet.RoboticDog;
import org.wcci.adjrvirtualpet.RoboticShelter;

public class RoboticShelterTest {
    @Test
    void testAddPetToShelter(){
        RoboticShelter<RoboticDog> shelter = new RoboticShelter<>();
        RoboticDog pet1 = new RoboticDog("Fred");
        shelter.addPet(pet1);
        assertEquals(pet1, shelter.getPet("Fred"));
    }

    @Test
    void testAdoptOutPet(){
        RoboticShelter<RoboticDog> shelter = new RoboticShelter<>();
        RoboticDog pet1 = new RoboticDog("Fred");
        shelter.adoptPet(pet1);
        assertEquals(null, shelter.getPet("Fred"));
    }

    @Test
    void testAdoptOutAllPets(){
        RoboticShelter<RoboticDog> shelter1 = new RoboticShelter<>();
        RoboticShelter<RoboticCat> shelter2 = new RoboticShelter<>();

        RoboticDog pet1 = new RoboticDog("Fred");
        shelter1.addPet(pet1);

        RoboticDog pet2 = new RoboticDog("Steve");
        shelter1.addPet(pet2);

        RoboticCat pet3 = new RoboticCat("Phil");
        shelter2.addPet(pet3);

        RoboticCat pet4 = new RoboticCat("Dave");
        shelter2.addPet(pet4);

        shelter1.removeAllPets();
        shelter2.removeAllPets();

        assertEquals(null, shelter1.getPet("Fred"));
        assertEquals(null, shelter1.getPet("Steve"));
        assertEquals(null, shelter2.getPet("Phil"));
        assertEquals(null, shelter2.getPet("Dave"));
    }

    @Test
    void testOilAllPets(){
        RoboticShelter<RoboticDog> shelter1 = new RoboticShelter<>();
        RoboticShelter<RoboticCat> shelter2 = new RoboticShelter<>();

        RoboticDog pet1 = new RoboticDog("Fred");
        shelter1.addPet(pet1);

        RoboticDog pet2 = new RoboticDog("Steve");
        shelter1.addPet(pet2);

        RoboticCat pet3 = new RoboticCat("Phil");
        shelter2.addPet(pet3);

        RoboticCat pet4 = new RoboticCat("Dave");
        shelter2.addPet(pet4);

        assertEquals(true, pet1.needsOil());
        assertEquals(true, pet2.needsOil());
        assertEquals(true, pet3.needsOil());
        assertEquals(true, pet4.needsOil());

        shelter1.oilAllPets(5);
        shelter2.oilAllPets(5);

        assertEquals(false, pet1.needsOil());
        assertEquals(false, pet2.needsOil());
        assertEquals(false, pet3.needsOil());
        assertEquals(false, pet4.needsOil());
    }

    @Test
    void testChargeAllPets(){
        RoboticShelter<RoboticDog> shelter1 = new RoboticShelter<>();
        RoboticShelter<RoboticCat> shelter2 = new RoboticShelter<>();

        RoboticDog pet1 = new RoboticDog("Fred");
        shelter1.addPet(pet1);

        RoboticDog pet2 = new RoboticDog("Steve");
        shelter1.addPet(pet2);

        RoboticCat pet3 = new RoboticCat("Phil");
        shelter2.addPet(pet3);

        RoboticCat pet4 = new RoboticCat("Dave");
        shelter2.addPet(pet4);

        pet1.setCharge(15);
        pet2.setCharge(15);
        pet3.setCharge(15);
        pet4.setCharge(15);

        assertEquals(true, pet1.needsCharged());
        assertEquals(true, pet2.needsCharged());
        assertEquals(true, pet3.needsCharged());
        assertEquals(true, pet4.needsCharged());


        shelter1.chargeAllPets(5);
        shelter2.chargeAllPets(5);

        assertEquals(false, pet1.needsCharged());
        assertEquals(false, pet2.needsCharged());
        assertEquals(false, pet3.needsCharged());
        assertEquals(false, pet4.needsCharged());
    }

    @Test
    void testWalkAllPets(){
        RoboticShelter<RoboticDog> shelter = new RoboticShelter<>();
        RoboticDog pet1 = new RoboticDog("Fred");
        shelter.addPet(pet1);

        RoboticDog pet2 = new RoboticDog("Steve");
        shelter.addPet(pet2);

        shelter.oilAllPets(5);

        assertEquals(false, pet1.needsOil());
        assertEquals(false, pet2.needsOil());

        shelter.walkAllPets();
        shelter.walkAllPets();
        shelter.walkAllPets();
        shelter.walkAllPets();
        shelter.walkAllPets();

        assertEquals(true, pet1.needsOil());
        assertEquals(true, pet2.needsOil());

        
    }
}
