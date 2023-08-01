package org.wcci.adjrvirtualpet.interfaces;

public interface Organic {
    public void feed(int amountOfFood);
    public void water(int amountOfWater);
    public void cleanPersonalArea();
    public boolean isPersonalAreaClean();
    public String getName();
    public Integer ageInYears();
    public String hungerStatus();
    public String thirstStatus();
    public String energyStatus();
    public String moodStatus();
    public void play();
    public void setFeedingSchedule(String feedingSchedule);
    public void removeFeedingSchedule();
    public void train(String skill);
    public void timePassed(int hours);
    public void walk();
}
