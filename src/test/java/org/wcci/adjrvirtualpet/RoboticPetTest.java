package org.wcci.adjrvirtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
// import org.wcci.adjrvirtualpet.RoboticCat;
// import org.wcci.adjrvirtualpet.RoboticDog;

public class RoboticPetTest {
    
    @Test
    void testRoboticDogsNeedMoreOil(){
        RoboticDog pet1 = new RoboticDog("");
        RoboticCat pet2 = new RoboticCat("");

        assertEquals(true, pet1.needsOil());
        pet1.oil(1);
        assertEquals(true, pet1.needsOil());

        assertEquals(true, pet2.needsOil());
        pet2.oil(1);
        assertEquals(false, pet2.needsOil());
    }

    @Test
    void testRoboticDogsNeedMoreCharging(){
        RoboticDog pet1 = new RoboticDog("");
        RoboticCat pet2 = new RoboticCat("");

        pet1.setCharge(15);
        pet2.setCharge(15);

        assertEquals(true, pet1.needsCharged());
        pet1.charge(1);
        assertEquals(true, pet1.needsCharged());

        assertEquals(true, pet2.needsCharged());
        pet2.charge(1);
        assertEquals(false, pet2.needsCharged());
    }

    @Test
    void testWalksUseOilAndCharge(){
        RoboticDog pet = new RoboticDog("");

        pet.oil(5);
        assertEquals(false, pet.needsCharged());
        assertEquals(false, pet.needsOil());

        pet.walk();
        pet.walk();
        pet.walk();
        pet.walk();
        pet.walk();
        pet.walk();

        assertEquals(true, pet.needsCharged());
        assertEquals(true, pet.needsOil());
    }

    
}
