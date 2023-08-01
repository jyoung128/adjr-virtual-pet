package org.wcci.virtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.wcci.adjrvirtualpet.entities.OrganicCat;
import org.wcci.adjrvirtualpet.entities.OrganicDog;

public class OrganicPetTest {
    @Test
    void testSoilingPersonalArea(){
        OrganicDog pet1 = new OrganicDog("");
        OrganicCat pet2 = new OrganicCat("");

        assertEquals(true, pet1.isPersonalAreaClean());
        assertEquals(true, pet2.isPersonalAreaClean());

        pet1.timePassed(48);
        pet2.timePassed(48);

        assertEquals(false, pet1.isPersonalAreaClean());
        assertEquals(false, pet2.isPersonalAreaClean());
    }

    @Test
    void testWalkingHelpsCageStayClean(){
        OrganicDog pet = new OrganicDog("");

        assertEquals(true, pet.isPersonalAreaClean());
        pet.timePassed(24);
        assertEquals(false, pet.isPersonalAreaClean());
        pet.cleanPersonalArea();
        assertEquals(true, pet.isPersonalAreaClean());
        pet.walk();
        pet.timePassed(24);
        assertEquals(true, pet.isPersonalAreaClean());
    }
}
