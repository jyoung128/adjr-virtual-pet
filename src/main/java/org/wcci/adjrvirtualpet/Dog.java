package org.wcci.adjrvirtualpet;

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
