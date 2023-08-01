package org.wcci.adjrvirtualpet;

import java.util.HashMap;
import java.util.Map;

import org.wcci.adjrvirtualpet.interfaces.Organic;

public class OrganicShelter<P extends Organic>{

    private Map<String, P>allPets = new HashMap<>();

    public Map<String, P> getAllPets() {
        return allPets;
    }

    public void summarizeShelter(){
        System.out.println();
        for(P pet : allPets.values())
        {
            System.out.println("Name: "+pet.getName());
            System.out.println("Age: "+pet.ageInYears()+" years");
            System.out.println("Hunger: "+pet.hungerStatus());
            System.out.println("Thirst: "+pet.thirstStatus());
            System.out.println("Energy: "+pet.energyStatus());
            System.out.println("Mood: "+pet.moodStatus());
            System.out.println();
            System.out.println("------------------------------------");
            System.out.println();
        }
    }

    public void setAllPets(Map<String, P> allPets) {
        this.allPets = allPets;
    }

    public void addPet(P pet) {
        allPets.put(pet.getName(), pet);
    }

    public void adoptPet(P pet) {
        allPets.remove(pet.getName());
    }

    public P getPet(String name) {
        return allPets.get(name);
    }

    public void feedAllPets(int amountOfFood) {
        for(P pet : allPets.values())
        {
            pet.feed(amountOfFood);
        }
    }

    public void waterAllPets(int amountOfWater) {
        for(P pet : allPets.values())
        {
            pet.water(amountOfWater);
        }
    }

    public void playWithAllPets() {
        for(P pet : allPets.values())
        {
            pet.play();
        }
    }

    public void setFeedingScheduleAll(String feedingSchedule) {
        for(P pet : allPets.values())
        {
            pet.setFeedingSchedule(feedingSchedule);
        }
    }

    public void removeFeedingScheduleAll() {
        for(P pet : allPets.values())
        {
            pet.removeFeedingSchedule();
        }
    }

    public void removeAllPets() {
        allPets.clear();
    }

    public void trainAllPets(String skill) {
        for(P pet : allPets.values())
        {
            pet.train(skill);
        }
    }

    public void timePassedAll(int hours) {
        for(P pet : allPets.values())
        {
            pet.timePassed(hours);
        }
    }

    public void walkAllPets() {
        for(P pet : allPets.values())
        {
            pet.walk();
        }
    }

}
