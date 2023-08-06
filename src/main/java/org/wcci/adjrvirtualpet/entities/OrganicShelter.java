package org.wcci.adjrvirtualpet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganicShelter {

    @Id
    @GeneratedValue
    private long shelterId;

    private String name;

    @JsonIgnore
    @OneToMany()
    @JoinTable()
    private Set<OrganicDog> dogs = new HashSet<>();

    @JsonIgnore
    @OneToMany()
    @JoinTable()
    private Set<OrganicCat> cats = new HashSet<>();

    private int dogCount;
    private int catCount;

    public void organicShelter() {
    }

    public void organicShelter(String name) {
        this.name = name;
    }

    public long getShelterID() {
        return shelterId;
    }

    public void setShelterID(long shelterId) {
        this.shelterId = shelterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDogCount() {
        return dogCount;
    }

    public void setDogCount(int dogCount) {
        this.dogCount = dogCount;
    }

    public int getCatCount() {
        return catCount;
    }

    public void setCatCount(int catCount) {
        this.catCount = catCount;
    }

    public Set<OrganicDog> getDogs() {
        return dogs;
    }

    public Set<OrganicCat> getCats() {
        return cats;
    }

    public OrganicDog getDog(String name) {
        for (OrganicDog dog : dogs) {
            if (dog.getName().equals(name)) {
                return dog;
            }
        }
        return null;
    }

    public void addDog(final OrganicDog dog) {
        this.dogs.add(dog);
        this.dogCount = dogs.size();
    }

    public void addCat(final OrganicCat cat) {
        this.cats.add(cat);
        this.catCount = cats.size();
    }

    public void removeDog(final OrganicDog dog) {
        this.dogs.remove(dog);
    }

    public void removeCat(final OrganicCat cat) {
        this.cats.remove(cat);
    }

}
