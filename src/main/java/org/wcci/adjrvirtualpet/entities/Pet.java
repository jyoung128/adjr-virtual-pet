package org.wcci.adjrvirtualpet.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pet {
    private static final int INITIAL_MOOD = 45;
    private static final int INITIAL_ENERGY = 70;
    private static final int INITIAL_THIRST = 55;
    private static final int INITIAL_HUNGER = 55;

    private static final int TIRED_LEVEL = 50;
    protected static final int SLEEPING_LEVEL = 20;
    private static final int CRANKY_LEVEL = 50;
    private static final int THIRSTY_LEVEL = 50;
    private static final int HUNGRY_LEVEL = 50;
    private static final int OVERWATERED_LEVEL = 0;
    private static final int DEHYDRATED_LEVEL = 80;
    private static final int OVERFED_LEVEL = 0;
    private static final int STARVING_LEVEL = 80;

    public enum TEMPERAMENT {DOCILE, DOMINANT, AVOIDANT, SOCIAL};

    final private static Logger logger = LoggerFactory.getLogger(Pet.class);

    @Id
    @GeneratedValue
    private long petId;

    private String name;
    private int ageInDays;
    private int hunger;
    private int thirst;
    private int energy;
    protected int mood;
    private TEMPERAMENT temperament;
    //private Map<String, Integer> skills = new HashMap<>();
    //private List<String> skills;
    private String feedingScheduleString;

    public Pet(){
    }

    public Pet(String name) {
        this.name = name;
        this.ageInDays = 0;
        this.hunger = INITIAL_HUNGER;
        this.thirst = INITIAL_THIRST;
        this.energy = INITIAL_ENERGY;
        this.mood = INITIAL_MOOD;
    }

    public Pet(String name, String species) {
        this.name = name;
        this.ageInDays = 0;
        this.hunger = INITIAL_HUNGER;
        this.thirst = INITIAL_THIRST;
        this.energy = INITIAL_ENERGY;
        this.mood = INITIAL_MOOD;
    }

    public Pet(String name, TEMPERAMENT temperament) {
        this.name = name;
        this.ageInDays = 0;
        this.hunger = INITIAL_HUNGER;
        this.thirst = INITIAL_THIRST;
        this.energy = INITIAL_ENERGY;
        this.mood = INITIAL_MOOD;
        this.temperament = temperament;
    }

    public boolean determineSociability()
    {
        switch(this.temperament){
            case DOCILE: return true;
            case DOMINANT: return false;
            case AVOIDANT: return false;
            case SOCIAL: return true;
            default: return true;
        }
    }

    public long getPetID() {
        return petId;
    }

    public void setPetID(long petId) {
        this.petId = petId;
    }

    /*public String getSkillName(String skill) {
        return skill.substring(0, skill.indexOf(":"));
    }

    public int getSkillLevel(String skill) {
        return Integer.parseInt(skill.substring(skill.indexOf(":") + 1));
    }*/

    /** Hunger is on a scale from 0 to 100 */ // This is a "javadoc"
    public Integer getHunger() {
        return this.hunger;
    }

    public String hungerStatus()
    {
        
        if(isOverfed())
        {
            return "overfed";
        }
        else if(isStarving())
        {
            return "starving";
        }
        else if(isHungry())
        {
            return "hungry";
        }
        else
        {
            return "not hungry";
        }
    }

    /** Allow any number of hours to pass */
    public void timePassed(int hours) {
        final List<Integer> feedingSchedule = parseFeedingSchedule();

        if (!feedingSchedule.isEmpty()) {
            this.feed(feedingSchedule.size());
        }
        this.hunger += hours;
        this.energy -= hours * 5;
        this.ageInDays += (hours / 24);
    }

    /** Makes a single hour pass */
    public void hourPassed() {
        this.energy -= 5;
    }

    /** Thirst is on a scale from 0 to 100 */
    public Integer getThirst() {
        return this.thirst;
    }

    public String thirstStatus(){

        if(isOverwatered())
        {
            return "overwatered";
        }
        else if(isDehydrated())
        {
            return "dehydrated";
        }
        else if (isThirsty())
        {
            return "thirsty";
        }
        else
        {
            return "not thirsty";
        }
    }

    /** Checks if hunger is greater than 50 */
    public boolean isHungry() {

        if (this.hunger > HUNGRY_LEVEL) {
            return true;
        } else {
            return false;
        }
    }

    /** Checks if hunger is greater than 80 */
    public boolean isStarving() {

        if (this.hunger > STARVING_LEVEL) {
            return true;
        } else {
            return false;
        }

    }

    /** Gives the pet a small snack to reduce hunger */
    public void giveSnack() {
        this.hunger -= 10;
    }

    private List<Integer> parseFeedingSchedule() {
        if(this.feedingScheduleString == null) return new ArrayList<>();
        try{
            return new ObjectMapper().readValue(this.feedingScheduleString, new TypeReference<List<Integer>>(){});
        } catch (Exception e) {
            logger.warn("Unable to parse schedule: " + this.feedingScheduleString);
            return new ArrayList<Integer>();
        }
    }

    private void saveFeedingSchedule(List<Integer> feedingSchedule){
        try{
            this.feedingScheduleString = new ObjectMapper().writeValueAsString(feedingSchedule);
        } catch (JsonProcessingException e) {
            logger.warn("Unable to serialize schedule: " + feedingSchedule.toString());
        }
    }

    /** Sets a routine feeding schedule for the pet */
    public void setFeedingSchedule(String string) {
        final List<Integer> feedingSchedule = parseFeedingSchedule();

        int start = 0;
        String state = "blanks";
        string += " ";

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            String newState = computeState(c);

            if (!newState.equals(state)) {
                if (!state.equals("blanks")) {
                    String substring = string.substring(start, i);
                    feedingSchedule.add(Integer.parseInt(substring));
                }
                start = i;
                state = newState;
            }

        }
        saveFeedingSchedule(feedingSchedule);

    }

    /** setFeedingSchedule helper method */
    private static String computeState(char c) {
        if (c == ' ' || c == ',' || c == ':') {
            return "blanks";
        } else if (c >= '0' && c <= '9') {
            return "number";
        } else {
            return "symbol";
        }
    }

    /** Checks if hunger is less than 0 */
    public boolean isOverfed() {
        if (this.hunger < OVERFED_LEVEL) {
            return true;
        } else {
            return false;
        }

    }

    /** Command the pet to sit */
    public void commandSit() {
    }

    /** Trains the pet with a specified skill as a String */
    public void train(String skill) {
        /*if (this.hunger <= HUNGRY_LEVEL) {
            if (this.skills.containsKey(skill)) {
                skills.put(skill, (skills.get(skill) + 1));
            } else {
                skills.put(skill, 1);
            }
        }

        this.energy -= 10;
        this.thirst += 10;*/
        return;
    }

    /** Returns the pet's chance of sitting */
    /*public double chanceOfSitting() {
        if (skills.containsKey("sitting") && skills.get("sitting") >= 1) {
            return 0.85;
        } else {
            return 0.5;
        }
    }*/

    /** Sets the pet's age in months */
    public void setAgeMonths(int months) {
        this.ageInDays = months * 30;
    }

    /** Sets the pet's mood */
    public void setMood(int mood){
        this.mood = mood;
    }

    public void feed(int amountOfFood) {
        this.hunger -= (amountOfFood * 15);
    }

    /** Checks if thirst is greater than 80 */
    public boolean isDehydrated() {
        if (this.thirst > DEHYDRATED_LEVEL) {
            return true;
        } else {
            return false;
        }
    }

    /** Checks if thirst is less than 0 */
    public boolean isOverwatered() {
        if (this.thirst < OVERWATERED_LEVEL) {
            return true;
        } else {
            return false;
        }
    }

    /** Checks if thirst is greater than 50 */
    public boolean isThirsty() {
        if (this.thirst > THIRSTY_LEVEL) {
            return true;
        } else {
            return false;
        }

    }

    /** Reduces thirst by 5 * i */
    public void water(int amountOfWater) {
        this.thirst -= (amountOfWater * 5);
    }

    public Integer getAgeInDays(){
        return this.ageInDays;
    }

    public Integer getEnergy(){
        return this.energy;
    }

    public Integer ageInYears() {
        if (this.ageInDays < 360) {
            return 0;
        } else {
            return Math.round(ageInDays / 360);
        }
    }

    /** Sets a routine walking schedule for the pet */
    public void setWalkingSchedule(String string) {
    }

    /** Verifies if the pet is being fed at a specified hour */
    public boolean isFedAt(int hour) {
        final List<Integer> feedingSchedule = parseFeedingSchedule();

        if (feedingSchedule.contains(hour)) {
            return true;
        } else {
            return false;
        }

    }

    /** Removes the previously set walking schedule */
    public void removeWalkingSchedule() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Removes the previously set feeding schedule */
    public void removeFeedingSchedule() {
        final List<Integer> feedingSchedule = parseFeedingSchedule();
        feedingSchedule.clear();
        saveFeedingSchedule(feedingSchedule);
    }

    /** Checks if the pet is healthy */
    public boolean isHealthy() {
        if (this.hunger < 95 && this.thirst < 95 && this.energy > 0) {
            return true;
        } else {
            return false;
        }

    }

    /** Checks if energy is less than 50 */
    public boolean isTired() {
        if (this.energy < TIRED_LEVEL) {
            return true;
        } else {
            return false;
        }

    }

    /** Checks if the pet is sleeping */
    public boolean isSleeping() {
        if (this.energy <= SLEEPING_LEVEL) {
            return true;
        } else {
            return false;
        }

    }

    /** Play with the pet */
    public void play() {
        this.hunger += 10;
        this.thirst += 10;
        this.energy -= 9;
    }

    /** Sets a routine watering schedule for the pet */
    public void setWateringSchedule(String string) {
    }

    /** Checks if the pet is cranky or not */
    public boolean isCranky() {
        if (this.mood < CRANKY_LEVEL) {
            return true;
        } else {
            return false;
        }

    }

    public String energyStatus() {

        if(isSleeping())
        {
            return "asleep";
        }
        else if (isTired())
        {
            return "tired";
        }
        else
        {
            return "not tired";
        }
        
    }

    public String moodStatus() {
        if(isCranky())
        {
            return "cranky";
        }
        else
        {
            return "happy";
        }
    }

    public void walk() {
        if (this.getAgeInDays() >= 180 && this.getEnergy() > SLEEPING_LEVEL) {
            this.mood += 10;
        }
    }

}
