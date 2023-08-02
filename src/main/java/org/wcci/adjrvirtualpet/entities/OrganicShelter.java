package org.wcci.adjrvirtualpet.entities;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

//@Entity
//@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganicShelter {

    // @Id
    // @GeneratedValue
    private long shelterId;

    private String name;
    private Map<String, OrganicDog> allDogs = new HashMap<>();
    private Map<String, OrganicCat> allCats = new HashMap<>();

    public void organicShelter() {
    }

    public void organicShelter(String name) {
        this.name = name;
    }

    public Map<String, OrganicDog> getAllDogs() {
        return allDogs;
    }

    public void setAllDogs(Map<String, OrganicDog> allDogs) {
        this.allDogs = allDogs;
    }

    public Map<String, OrganicCat> getAllCats() {
        return allCats;
    }

    public void setAllCats(Map<String, OrganicCat> allCats) {
        this.allCats = allCats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public void summarizeShelter(){
    // System.out.println();
    // for(P pet : allPets.values())
    // {
    // System.out.println("Name: "+pet.getName());
    // System.out.println("Age: "+pet.ageInYears()+" years");
    // System.out.println("Hunger: "+pet.hungerStatus());
    // System.out.println("Thirst: "+pet.thirstStatus());
    // System.out.println("Energy: "+pet.energyStatus());
    // System.out.println("Mood: "+pet.moodStatus());
    // System.out.println();
    // System.out.println("------------------------------------");
    // System.out.println();
    // }
    // }

    public void addDog(OrganicDog dog) {
        allDogs.put(dog.getName(), dog);
    }

    public void adoptDog(OrganicDog dog) {
        allDogs.remove(dog.getName());
    }

    public void addCat(OrganicCat cat) {
        allCats.put(cat.getName(), cat);
    }

    public void adoptCat(OrganicCat cat) {
        allCats.remove(cat.getName());
    }

    public OrganicDog getDog(String name) {
        return allDogs.get(name);
    }

    public OrganicCat getCat(String name) {
        return allCats.get(name);
    }

    public void feedAllDogs(int amountOfFood) {
        for (OrganicDog dog : allDogs.values()) {
            dog.feed(amountOfFood);
        }
    }

    public void feedAllCats(int amountOfFood) {
        for (OrganicCat cat : allCats.values()) {
            cat.feed(amountOfFood);
        }
    }

    public void waterAllDogs(int amountOfWater) {
        for (OrganicDog dog : allDogs.values()) {
            dog.water(amountOfWater);
        }
    }

    public void waterAllCats(int amountOfWater) {
        for (OrganicCat cat : allCats.values()) {
            cat.water(amountOfWater);
        }
    }

    public void playWithAllDogs() {
        for (OrganicDog dog : allDogs.values()) {
            dog.play();
        }
    }

    public void playWithAllCats() {
        for (OrganicCat cat : allCats.values()) {
            cat.play();
        }
    }

    public void setFeedingScheduleAllDogs(String feedingSchedule) {
        for (OrganicDog dog : allDogs.values()) {
            dog.setFeedingSchedule(feedingSchedule);
        }
    }

    public void setFeedingScheduleAllCats(String feedingSchedule) {
        for (OrganicCat cat : allCats.values()) {
            cat.setFeedingSchedule(feedingSchedule);
        }
    }

    public void removeFeedingScheduleAllDogs() {
        for (OrganicDog dog : allDogs.values()) {
            dog.removeFeedingSchedule();
        }
    }

    public void removeFeedingScheduleAllCats() {
        for (OrganicCat cat : allCats.values()) {
            cat.removeFeedingSchedule();
        }
    }

    public void removeAllDogs() {
        allDogs.clear();
    }

    public void removeAllCats() {
        allCats.clear();
    }

    public void trainAllDogs(String skill) {
        for (OrganicDog dog : allDogs.values()) {
            dog.train(skill);
        }
    }

    public void timePassedAllDogs(int hours) {
        for (OrganicDog dog : allDogs.values()) {
            dog.timePassed(hours);
        }
    }

    public void timePassedAllCats(int hours) {
        for (OrganicCat cat : allCats.values()) {
            cat.timePassed(hours);
        }
    }

    public void walkAllDogs() {
        for (OrganicDog dog : allDogs.values()) {
            dog.walk();
        }
    }

}
