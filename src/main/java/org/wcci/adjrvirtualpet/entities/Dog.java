package org.wcci.adjrvirtualpet.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "petId")
public abstract class Dog extends Pet{

    public Dog(String name) {
        super(name);
        
    }

    public Dog(String name, TEMPERAMENT temperament) {
        super(name, temperament);
    }
    
    /** Walk the dog */
    public abstract void walk();
}
