package org.wcci.adjrvirtualpet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "petId")
public abstract class Cat extends Pet{

    public Cat(String name) {
        super(name);
        
    }
    
}
