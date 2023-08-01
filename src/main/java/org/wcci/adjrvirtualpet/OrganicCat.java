package org.wcci.adjrvirtualpet;

public class OrganicCat extends Cat implements Organic{

    private final int INITIAL_LITTER_BOX_CLEANLINESS = 100;

    private int litterBoxCleanliness;

    public OrganicCat(String name) {
        super(name);
        this.litterBoxCleanliness = INITIAL_LITTER_BOX_CLEANLINESS;
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
