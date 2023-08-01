package org.wcci.virtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.wcci.adjrvirtualpet.OrganicCat;
import org.wcci.adjrvirtualpet.OrganicDog;
import org.wcci.adjrvirtualpet.OrganicShelter;

public class OrganicShelterTest {
    @Test
    void testAddPetToShelter(){
        OrganicShelter<OrganicDog> shelter = new OrganicShelter<>();
        OrganicDog pet1 = new OrganicDog("Fred");
        shelter.addPet(pet1);
        assertEquals(pet1, shelter.getPet("Fred"));
    }

    @Test
    void testAdoptOutPet(){
        OrganicShelter<OrganicDog> shelter = new OrganicShelter<>();
        OrganicDog pet1 = new OrganicDog("Fred");
        shelter.adoptPet(pet1);
        assertEquals(null, shelter.getPet("Fred"));
    }

    @Test
    void testTimePassedAll(){
        OrganicShelter<OrganicDog> shelter1 = new OrganicShelter<>();
        OrganicShelter<OrganicCat> shelter2 = new OrganicShelter<>();

        OrganicDog pet1 = new OrganicDog("Fred");
        shelter1.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Phil");
        shelter1.addPet(pet2);

        OrganicCat pet3 = new OrganicCat("Steve");
        shelter2.addPet(pet3);

        OrganicCat pet4 = new OrganicCat("Dave");
        shelter2.addPet(pet4);

        shelter1.feedAllPets(1);
        shelter2.feedAllPets(1);

        assertEquals(false, pet1.isHungry());
        assertEquals(false, pet2.isHungry());
        assertEquals(false, pet3.isHungry());
        assertEquals(false, pet4.isHungry());

        shelter1.timePassedAll(48);
        shelter2.timePassedAll(48);

        assertEquals(true, pet1.isHungry());
        assertEquals(true, pet2.isHungry());
        assertEquals(true, pet3.isHungry());
        assertEquals(true, pet4.isHungry());
    }

    @Test
    void testAdoptOutAllPets(){
        OrganicShelter<OrganicDog> shelter1 = new OrganicShelter<>();
        OrganicShelter<OrganicCat> shelter2 = new OrganicShelter<>();

        OrganicDog pet1 = new OrganicDog("Fred");
        shelter1.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Steve");
        shelter1.addPet(pet2);

        OrganicCat pet3 = new OrganicCat("Phil");
        shelter2.addPet(pet3);

        OrganicCat pet4 = new OrganicCat("Dave");
        shelter2.addPet(pet4);

        shelter1.removeAllPets();
        shelter2.removeAllPets();

        assertEquals(null, shelter1.getPet("Fred"));
        assertEquals(null, shelter1.getPet("Steve"));
        assertEquals(null, shelter2.getPet("Phil"));
        assertEquals(null, shelter2.getPet("Dave"));
    }

    @Test
    void testFeedAllPets(){
        OrganicShelter<OrganicDog> shelter1 = new OrganicShelter<>();
        OrganicShelter<OrganicCat> shelter2 = new OrganicShelter<>();

        OrganicDog pet1 = new OrganicDog("Fred");
        shelter1.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Steve");
        shelter1.addPet(pet2);

        OrganicCat pet3 = new OrganicCat("Phil");
        shelter2.addPet(pet3);

        OrganicCat pet4 = new OrganicCat("Dave");
        shelter2.addPet(pet4);

        assertEquals(true, pet1.isHungry());
        assertEquals(true, pet2.isHungry());
        assertEquals(true, pet3.isHungry());
        assertEquals(true, pet4.isHungry());

        shelter1.feedAllPets(1);
        shelter2.feedAllPets(1);

        assertEquals(false, pet1.isHungry());
        assertEquals(false, pet2.isHungry());
        assertEquals(false, pet3.isHungry());
        assertEquals(false, pet4.isHungry());
    }

    @Test
    void testWaterAllPets(){
        OrganicShelter<OrganicDog> shelter1 = new OrganicShelter<>();
        OrganicShelter<OrganicCat> shelter2 = new OrganicShelter<>();

        OrganicDog pet1 = new OrganicDog("Fred");
        shelter1.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Steve");
        shelter1.addPet(pet2);

        OrganicCat pet3 = new OrganicCat("Phil");
        shelter2.addPet(pet3);

        OrganicCat pet4 = new OrganicCat("Dave");
        shelter2.addPet(pet4);

        assertEquals(true, pet1.isThirsty());
        assertEquals(true, pet2.isThirsty());
        assertEquals(true, pet3.isThirsty());
        assertEquals(true, pet4.isThirsty());


        shelter1.waterAllPets(1);
        shelter2.waterAllPets(1);

        assertEquals(false, pet1.isThirsty());
        assertEquals(false, pet2.isThirsty());
        assertEquals(false, pet3.isThirsty());
        assertEquals(false, pet4.isThirsty());
    }

    @Test
    void testPlayWithAllPets(){
        OrganicShelter<OrganicDog> shelter1 = new OrganicShelter<>();
        OrganicShelter<OrganicCat> shelter2 = new OrganicShelter<>();

        OrganicDog pet1 = new OrganicDog("Fred");
        shelter1.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Phil");
        shelter1.addPet(pet2);

        OrganicCat pet3 = new OrganicCat("Steve");
        shelter2.addPet(pet3);

        OrganicCat pet4 = new OrganicCat("Dave");
        shelter2.addPet(pet4);

        pet1.setAgeMonths(6);
        pet2.setAgeMonths(6);
        pet3.setAgeMonths(6);
        pet4.setAgeMonths(6);

        pet1.feed(1);
        pet1.water(1);
        pet1.walk();

        pet2.feed(1);
        pet2.water(1);
        pet2.walk();

        pet3.feed(1);
        pet3.water(1);
        pet3.walk();

        pet4.feed(1);
        pet4.water(1);
        pet4.walk();

        assertEquals(false, pet1.isHungry());
        assertEquals(false, pet1.isThirsty());

        assertEquals(false, pet2.isHungry());
        assertEquals(false, pet2.isThirsty());

        assertEquals(false, pet3.isHungry());
        assertEquals(false, pet3.isThirsty());

        assertEquals(false, pet4.isHungry());
        assertEquals(false, pet4.isThirsty());

        shelter1.playWithAllPets();
        shelter1.playWithAllPets();
        shelter1.playWithAllPets();
        shelter1.playWithAllPets();
        shelter1.playWithAllPets();

        shelter2.playWithAllPets();
        shelter2.playWithAllPets();
        shelter2.playWithAllPets();
        shelter2.playWithAllPets();
        shelter2.playWithAllPets();

        assertEquals(true, pet1.isHungry());
        assertEquals(true, pet1.isThirsty());

        assertEquals(true, pet2.isHungry());
        assertEquals(true, pet2.isThirsty());

        assertEquals(true, pet3.isHungry());
        assertEquals(true, pet3.isThirsty());

        assertEquals(true, pet4.isHungry());
        assertEquals(true, pet4.isThirsty());
    }

    @Test
    void testAllFeedingSchedule(){
        OrganicShelter<OrganicCat> shelter = new OrganicShelter<>();
        OrganicCat pet1 = new OrganicCat("Fred");
        shelter.addPet(pet1);

        OrganicCat pet2 = new OrganicCat("Steve");
        shelter.addPet(pet2);

        shelter.setFeedingScheduleAll("6, 9, 17, 21");

        assertEquals(false, pet1.isFedAt(5));
        assertEquals(true, pet1.isFedAt(6));
        assertEquals(false, pet1.isFedAt(7));
        assertEquals(false, pet1.isFedAt(8));
        assertEquals(true, pet1.isFedAt(9));
        assertEquals(false, pet1.isFedAt(10));
        assertEquals(true, pet1.isFedAt(17));
        assertEquals(false, pet1.isFedAt(18));
        assertEquals(false, pet1.isFedAt(19));
        assertEquals(false, pet1.isFedAt(20));
        assertEquals(true, pet1.isFedAt(21));
        assertEquals(false, pet1.isFedAt(22));

        assertEquals(false, pet2.isFedAt(5));
        assertEquals(true, pet2.isFedAt(6));
        assertEquals(false, pet2.isFedAt(7));
        assertEquals(false, pet2.isFedAt(8));
        assertEquals(true, pet2.isFedAt(9));
        assertEquals(false, pet2.isFedAt(10));
        assertEquals(true, pet2.isFedAt(17));
        assertEquals(false, pet2.isFedAt(18));
        assertEquals(false, pet2.isFedAt(19));
        assertEquals(false, pet2.isFedAt(20));
        assertEquals(true, pet2.isFedAt(21));
        assertEquals(false, pet2.isFedAt(22));

        shelter.removeFeedingScheduleAll();

        assertEquals(false, pet1.isFedAt(5));
        assertEquals(false, pet1.isFedAt(6));
        assertEquals(false, pet1.isFedAt(7));
        assertEquals(false, pet1.isFedAt(8));
        assertEquals(false, pet1.isFedAt(9));
        assertEquals(false, pet1.isFedAt(10));
        assertEquals(false, pet1.isFedAt(17));
        assertEquals(false, pet1.isFedAt(18));
        assertEquals(false, pet1.isFedAt(19));
        assertEquals(false, pet1.isFedAt(20));
        assertEquals(false, pet1.isFedAt(21));
        assertEquals(false, pet1.isFedAt(22));

        assertEquals(false, pet2.isFedAt(5));
        assertEquals(false, pet2.isFedAt(6));
        assertEquals(false, pet2.isFedAt(7));
        assertEquals(false, pet2.isFedAt(8));
        assertEquals(false, pet2.isFedAt(9));
        assertEquals(false, pet2.isFedAt(10));
        assertEquals(false, pet2.isFedAt(17));
        assertEquals(false, pet2.isFedAt(18));
        assertEquals(false, pet2.isFedAt(19));
        assertEquals(false, pet2.isFedAt(20));
        assertEquals(false, pet2.isFedAt(21));
        assertEquals(false, pet2.isFedAt(22));
    }

    @Test
    void testTrainAllPets(){
        OrganicShelter<OrganicDog> shelter = new OrganicShelter<>();
        OrganicDog pet1 = new OrganicDog("Fred");
        shelter.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Steve");
        shelter.addPet(pet2);

        pet1.setAgeMonths(2 * 12);
        assertEquals(2, pet1.ageInYears());

        pet2.setAgeMonths(2 * 12);
        assertEquals(2, pet2.ageInYears());

        shelter.feedAllPets(1);
        shelter.waterAllPets(1);

        shelter.trainAllPets("sitting");

        assertEquals(true, pet1.chanceOfSitting() > 0.8);
        assertEquals(true, pet2.chanceOfSitting() > 0.8);

    }

    @Test
    void testWalkAllPets(){
        OrganicShelter<OrganicDog> shelter = new OrganicShelter<>();
        OrganicDog pet1 = new OrganicDog("Fred");
        shelter.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Steve");
        shelter.addPet(pet2);

        pet1.setAgeMonths(2 * 12);
        assertEquals(2, pet1.ageInYears());

        pet2.setAgeMonths(2 * 12);
        assertEquals(2, pet2.ageInYears());

        assertEquals(true, pet1.isCranky());
        assertEquals(true, pet2.isCranky());

        shelter.walkAllPets();

        assertEquals(false, pet1.isCranky());
        assertEquals(false, pet2.isCranky());
    }

    @Test 
    void testShelterSummary(){
        OrganicShelter<OrganicDog> shelter = new OrganicShelter<>();
        OrganicDog pet1 = new OrganicDog("Fred");
        shelter.addPet(pet1);

        OrganicDog pet2 = new OrganicDog("Steve");
        shelter.addPet(pet2);

        pet1.giveSnack();

        pet2.setAgeMonths(2 * 12);
        assertEquals(2, pet2.ageInYears());

        shelter.summarizeShelter();
    }
}
