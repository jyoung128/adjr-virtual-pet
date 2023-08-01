package org.wcci.adjrvirtualpet;

public interface Robotic {
    public void oil(int amountOfOil);
    public void charge(int hoursCharging);
    public boolean needsOil();
    public boolean needsCharged();
    public void setCharge(int charge);
    public String getName();
    public Integer ageInYears();
    public String oilStatus();
    public String chargeStatus();
    public String moodStatus();
    public void timePassed(int hours);
    public void walk();
    public void play();
}
