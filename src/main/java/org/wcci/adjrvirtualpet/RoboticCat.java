package org.wcci.adjrvirtualpet;

import org.wcci.adjrvirtualpet.entities.Cat;
import org.wcci.adjrvirtualpet.interfaces.Robotic;

public class RoboticCat extends Cat implements Robotic{

    private static final int INITIAL_CHARGE = 70;
    private static final int INITIAL_OIL = 45;

    private int oil;
    private int charge;

    public RoboticCat(String name) {
        super(name);
        this.oil = INITIAL_OIL;
        this.charge = INITIAL_CHARGE;
    }

    @Override
    public void oil(int amountOfOil) {
        this.oil += (amountOfOil * 5);
    }

    @Override
    public void charge(int hoursCharging) {
        this.charge += (hoursCharging * 5);
    }

    @Override
    public boolean needsOil() {
        if(this.oil < 50)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean needsCharged() {
        if(this.charge < 20)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void setCharge(int charge) {
        this.charge = charge;
    }

    @Override
    public String oilStatus() {
        if(needsOil())
        {
            return "needs oil";
        }
        else
        {
            return "oil level normal";
        }
    }

    @Override
    public String chargeStatus() {
        if(needsCharged())
        {
            return "needs charged";
        }
        else
        {
            return "charged";
        }
    }
    
}
