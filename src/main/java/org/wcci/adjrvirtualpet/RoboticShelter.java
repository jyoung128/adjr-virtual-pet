package org.wcci.adjrvirtualpet;

import java.util.HashMap;
import java.util.Map;

import org.wcci.adjrvirtualpet.interfaces.Robotic;

public class RoboticShelter<P extends Robotic>{
    
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
            System.out.println("Oil: "+pet.oilStatus());
            System.out.println("Charge: "+pet.chargeStatus());
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

    public void removeAllPets() {
        allPets.clear();
    }

    public void oilAllPets(int amountOfOil) {
        for(P pet : allPets.values())
        {
            pet.oil(amountOfOil);
        }
    }

    public void chargeAllPets(int hoursCharging) {
        for(P pet : allPets.values())
        {
            pet.charge(hoursCharging);
        }
    }

    public void playWithAllPets() {
        for(P pet : allPets.values())
        {
            pet.play();
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
