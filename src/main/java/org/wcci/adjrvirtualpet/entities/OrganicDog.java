package org.wcci.adjrvirtualpet.entities;

import org.wcci.adjrvirtualpet.interfaces.Organic;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "petId")
public class OrganicDog extends Dog implements Organic{

    private final int INITIAL_CAGE_CLEANLINESS = 100;

    private int cageCleanliness;
    private boolean hasBeenRecentlyWalked;
    private String species;

    public OrganicDog(String name) {
        super(name);
        this.species = "Organic Dog";
        this.cageCleanliness = INITIAL_CAGE_CLEANLINESS;
    }

    public OrganicDog(String name, TEMPERAMENT temperament) {
        super(name, temperament);
    }

    public OrganicDog(){
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    /** Allow any number of hours to pass */
    public void timePassed(int hours) {
        super.timePassed(hours);
        if(this.hasBeenRecentlyWalked)
        {
            this.cageCleanliness -= (hours);
        }
        else
        {
            this.cageCleanliness -= (3 * hours);
        }
        this.hasBeenRecentlyWalked = false;
    }

    @Override
    public void cleanPersonalArea() {
        this.cageCleanliness = INITIAL_CAGE_CLEANLINESS;
    }

    @Override
    public boolean isPersonalAreaClean() {
        if(cageCleanliness < 30)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void walk() {
        if (this.getAgeInDays() >= 180 && this.getEnergy() > SLEEPING_LEVEL) {
            this.mood += 10;
        }
        this.hasBeenRecentlyWalked = true;
    }
    
}
