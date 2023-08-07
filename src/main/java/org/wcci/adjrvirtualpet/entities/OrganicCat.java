package org.wcci.adjrvirtualpet.entities;

import org.wcci.adjrvirtualpet.interfaces.Organic;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "petId")
public class OrganicCat extends Cat implements Organic{

    private final int INITIAL_LITTER_BOX_CLEANLINESS = 100;
    private String species;

    private int litterBoxCleanliness;

    public OrganicCat(String name) {
        super(name);
        this.species = "Organic Cat";
        this.litterBoxCleanliness = INITIAL_LITTER_BOX_CLEANLINESS;
    }

    public OrganicCat(){
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
        this.litterBoxCleanliness -= (5 * hours);
    }

    @Override
    public void cleanPersonalArea() {
        this.litterBoxCleanliness = INITIAL_LITTER_BOX_CLEANLINESS;
    }

    @Override
    public boolean isPersonalAreaClean() {
        if(litterBoxCleanliness < 50)
        {
            return false;
        }
        else{
            return true;
        }
    }
    
}
