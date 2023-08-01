package org.wcci.virtualpet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.wcci.adjrvirtualpet.entities.OrganicDog;
import org.wcci.adjrvirtualpet.entities.Pet.TEMPERAMENT;

public class PetTest {
    @Test
    void newbornState() {
        OrganicDog pet = new OrganicDog("Bozo");

        assertEquals("Bozo", pet.getName());

        assertEquals(0, pet.ageInYears());

        assertEquals(true, pet.isHungry());
        assertEquals(false, pet.isStarving());
        assertEquals(false, pet.isOverfed());
        assertEquals(false, pet.isDehydrated());
        assertEquals(true, pet.isThirsty());
        assertEquals(false, pet.isOverwatered());

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }

    @Test
    void thirstAfterTraining() {
        OrganicDog pet = new OrganicDog("");
        pet.setAgeMonths(1 * 12);

        assertEquals(1, pet.ageInYears());

        pet.water(1);
        assertEquals(false, pet.isThirsty());

        pet.train("sitting");
        assertEquals(true, pet.isThirsty());
    }

    @Test
    void tiredAfterTraining() {
        OrganicDog pet = new OrganicDog("");
        pet.setAgeMonths(1 * 12);

        assertEquals(1, pet.ageInYears());

        pet.water(1);
        pet.feed(1);
        assertEquals(false, pet.isThirsty());
        assertEquals(false, pet.isTired());

        pet.train("sitting");
        assertEquals(false, pet.isTired());
        pet.train("sitting");
        pet.train("sitting");
        assertEquals(true, pet.isTired());
    }

    @Test
    void initialOlderPetState() {
        OrganicDog pet = new OrganicDog("");

        pet.setAgeMonths(2 * 12);

        // Pets start untrained
        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }

    @Test
    void trainingHelpsOlderPets() {
        OrganicDog pet = new OrganicDog("");

        pet.setAgeMonths(2 * 12);
        assertEquals(2, pet.ageInYears());

        // Training should work if the pet is ready
        pet.feed(1);
        pet.water(1);
        pet.walk();
        pet.train("sitting");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() > 0.8);

        assertEquals(true, pet.isHealthy());
    }

    @Test
    void trainingHelpsOlderPetsTheSameSkill() {
        OrganicDog pet = new OrganicDog("");

        pet.setAgeMonths(2 * 12);
        assertEquals(2, pet.ageInYears());

        // Training should work if the pet is ready
        pet.feed(1);
        pet.water(1);
        pet.walk();
        pet.train("fetching");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5); // We only trainied fetching in this case

        assertEquals(true, pet.isHealthy());
    }

    @Test
    void trainingDoesntHelpHungryOlderPets() {
        OrganicDog pet = new OrganicDog("");

        pet.setAgeMonths(2 * 12);
        assertEquals(2, pet.ageInYears());

        pet.timePassed(8);

        // Pet should be thirsty and hungry by now
        pet.train("sitting");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }

    @Test
    void trainingDoesntHelpNewborns() {
        OrganicDog pet = new OrganicDog("");
        pet.train("sitting");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() == 0.5);
    }

    @Test
    void moreTrainingHelpsMore() {
        OrganicDog pet = new OrganicDog("");

        pet.setAgeMonths(6);

        pet.feed(1);
        pet.water(1);
        pet.walk();

        pet.train("sitting");
        pet.train("sitting");
        pet.train("sitting");
        pet.train("sitting");
        pet.train("sitting");

        pet.commandSit();
        assertEquals(true, pet.chanceOfSitting() > 0.8);

        pet.timePassed(1);
        assertEquals(true, pet.isSleeping());
    }

    @Test
    void playing() {
        OrganicDog pet = new OrganicDog("");

        pet.setAgeMonths(6);

        pet.feed(1);
        pet.water(1);
        pet.walk();

        assertEquals(false, pet.isHungry());
        assertEquals(false, pet.isThirsty());
        assertEquals(false, pet.isSleeping());

        pet.play();
        pet.play();
        pet.play();
        pet.play();
        pet.play();

        assertEquals(true, pet.isHungry());
        assertEquals(true, pet.isThirsty());

        assertEquals(false, pet.isSleeping());
        pet.hourPassed();
        assertEquals(true, pet.isSleeping());
    }

    @Test
    void testHourPassed() {
        OrganicDog pet1 = new OrganicDog("");
        pet1.hourPassed();

        OrganicDog pet2 = new OrganicDog("");
        pet2.timePassed(1);

        assertEquals(pet1.isHungry(), pet2.isHungry());
        assertEquals(pet1.isStarving(), pet2.isStarving());
        assertEquals(pet1.isOverfed(), pet2.isOverfed());
        assertEquals(pet1.isHealthy(), pet2.isHealthy());
        assertEquals(pet1.isSleeping(), pet2.isSleeping());
        assertEquals(pet1.isDehydrated(), pet2.isDehydrated());
    }

    @Test
    void testFeedingSchedule() {
        OrganicDog pet = new OrganicDog("");
        pet.setFeedingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM

        assertEquals(false, pet.isFedAt(5));
        assertEquals(true, pet.isFedAt(6));
        assertEquals(false, pet.isFedAt(7));
        assertEquals(false, pet.isFedAt(8));
        assertEquals(true, pet.isFedAt(9));
        assertEquals(false, pet.isFedAt(10));
        assertEquals(true, pet.isFedAt(17));
        assertEquals(false, pet.isFedAt(18));
        assertEquals(false, pet.isFedAt(19));
        assertEquals(false, pet.isFedAt(20));
        assertEquals(true, pet.isFedAt(21));
        assertEquals(false, pet.isFedAt(22));

        pet.removeFeedingSchedule();
        assertEquals(false, pet.isFedAt(5));
        assertEquals(false, pet.isFedAt(6));
        assertEquals(false, pet.isFedAt(7));
        assertEquals(false, pet.isFedAt(8));
        assertEquals(false, pet.isFedAt(9));
        assertEquals(false, pet.isFedAt(10));
        assertEquals(false, pet.isFedAt(17));
        assertEquals(false, pet.isFedAt(18));
        assertEquals(false, pet.isFedAt(19));
        assertEquals(false, pet.isFedAt(20));
        assertEquals(false, pet.isFedAt(21));
        assertEquals(false, pet.isFedAt(22));
    }

    @Test
    void testFeedingSchedulePassed() {
        OrganicDog pet1 = new OrganicDog("");
        pet1.setWalkingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM
        pet1.timePassed(48);

        OrganicDog pet2 = new OrganicDog("");
        pet2.setFeedingSchedule("8:1, 16:1"); // one bowl at 8AM and one bowl at 4PM
        pet2.setWalkingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM
        pet2.timePassed(48);

        assertEquals(true, pet1.isStarving());
        assertEquals(false, pet2.isStarving());

        assertEquals(false, pet1.isOverfed());
        assertEquals(false, pet2.isOverfed());
    }

    @Test
    void testYears() {
        OrganicDog pet = new OrganicDog("");
        assertEquals(0, pet.ageInYears());

        pet.setFeedingSchedule("8:1, 16:1"); // one bowl at 8AM and one bowl at 4PM
        pet.setWalkingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM
        pet.setWateringSchedule("5, 8, 16, 20"); // 6AM, 9AM, 5PM, 9PM
        pet.timePassed(24 * 365);

        assertEquals(1, pet.ageInYears());
    }

    @Test
    void manualFeedingIsFine() {
        OrganicDog pet = new OrganicDog("");

        pet.setWalkingSchedule("6, 9, 17, 21"); // 6AM, 9AM, 5PM, 9PM

        pet.timePassed(8);
        pet.feed(1);
        pet.timePassed(16);
        pet.feed(1);
        pet.timePassed(8);
        pet.feed(1);
        pet.timePassed(16);
        pet.feed(1);
        assertEquals(false, pet.isHungry());
        pet.timePassed(8);
        assertEquals(true, pet.isHungry());
        assertEquals(false, pet.isStarving());
        assertEquals(false, pet.isOverfed());
        pet.feed(1);
        pet.timePassed(16);
        pet.feed(1);
        assertEquals(false, pet.isStarving());
        assertEquals(false, pet.isOverfed());
    }

    @Test
    void testOverFeedingSchedulePassed() {
        OrganicDog pet1 = new OrganicDog("");
        pet1.timePassed(48);

        OrganicDog pet2 = new OrganicDog("");
        pet2.setFeedingSchedule("8:1, 10:1, 12:1, 14:1, 16:1, 18:1"); // One bowl every two hours
        pet2.timePassed(48);

        assertEquals(true, pet1.isStarving());
        assertEquals(false, pet2.isStarving());

        assertEquals(false, pet1.isOverfed());
        assertEquals(true, pet2.isOverfed());
    }

    @Test
    void testOverFeedingSchedulePassedZero() {
        OrganicDog pet1 = new OrganicDog("");
        pet1.timePassed(48);

        OrganicDog pet2 = new OrganicDog("");
        pet2.setFeedingSchedule("8:1, 10:0, 12:0, 14:0, 16:0, 18:1"); // One bowl every two hours
        pet2.timePassed(48);

        assertEquals(true, pet1.isStarving());
        assertEquals(false, pet2.isStarving());

        assertEquals(false, pet1.isOverfed());
        assertEquals(true, pet2.isOverfed());
    }

    @Test
    void feedingHelps() {
        OrganicDog pet = new OrganicDog("");
        pet.giveSnack();
        assertEquals(false, pet.isHungry());
        assertEquals(false, pet.isStarving());
        assertEquals(true, pet.isHealthy());
    }

    @Test
    void walkingImprovesMood() {
        OrganicDog pet = new OrganicDog("");
        pet.setAgeMonths(6);
        assertEquals(true, pet.isCranky());
        pet.walk();
        assertEquals(false, pet.isCranky());
    }

    @Test
    void walkingDoesntWorkForNewborns() {
        OrganicDog pet = new OrganicDog("");
        assertEquals(true, pet.isCranky());
        pet.walk();
        assertEquals(true, pet.isCranky());
    }

    @Test
    void walkingDoesntWorkForTiredPets() {
        OrganicDog pet = new OrganicDog("");
        pet.setAgeMonths(6);
        pet.timePassed(10);
        assertEquals(true, pet.isCranky());
        pet.walk();
        assertEquals(true, pet.isCranky());
    }

    @Test
    void dominantPetsAreNotSocial() {
        OrganicDog pet = new OrganicDog("", TEMPERAMENT.DOMINANT);
        assertEquals(false, pet.determineSociability());
        
    }

    @Test
    void socialPetsAreSocial() {
        OrganicDog pet = new OrganicDog("", TEMPERAMENT.SOCIAL);
        assertEquals(true, pet.determineSociability());
        
    }

    @Test
    void docilePetsAreSocial() {
        OrganicDog pet = new OrganicDog("", TEMPERAMENT.DOCILE);
        assertEquals(true, pet.determineSociability());
        
    }

    @Test
    void avoidantPetsAreNotSocial() {
        OrganicDog pet = new OrganicDog("", TEMPERAMENT.AVOIDANT);
        assertEquals(false, pet.determineSociability());
        
    }
}