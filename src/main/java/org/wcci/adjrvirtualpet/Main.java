package org.wcci.adjrvirtualpet;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        OrganicShelter<OrganicDog> organicDogShelter = new OrganicShelter<>();
        OrganicShelter<OrganicCat> organicCatShelter = new OrganicShelter<>();
        RoboticShelter<RoboticDog> roboticDogShelter = new RoboticShelter<>();
        RoboticShelter<RoboticCat> roboticCatShelter = new RoboticShelter<>();
        
        OrganicDog pet1 = new OrganicDog("fred");
        organicDogShelter.addPet(pet1);

        RoboticDog pet2 = new RoboticDog("steve");
        roboticDogShelter.addPet(pet2);

        OrganicCat pet3 = new OrganicCat("phil");
        organicCatShelter.addPet(pet3);

        RoboticCat pet4 = new RoboticCat("frank");
        roboticCatShelter.addPet(pet4);

        Scanner scanner = new Scanner(System.in);
        String choice;

        System.out.println("Welcome to my animal shelter!");
        System.out.println();

        do{
            System.out.println("What would you like to do?");
            System.out.println("Please enter one of the following:");
            System.out.println();
            System.out.println("'Add pet'");
            System.out.println("'View pet'");
            System.out.println("'View all pets'");
            System.out.println("'Adopt pet'");
            System.out.println("'Quit'");
            System.out.println();

            choice = scanner.nextLine().toLowerCase();
            System.out.println();

        
            switch(choice)
            {
                
                case "add pet" : 
                    System.out.println();
                    System.out.println("Please enter the name of the pet you would like to add");
                    System.out.println();
            
                    String petName = scanner.nextLine();
                    String petType;
                    String petSpecies;
                    
                    do {
                        System.out.println("Is "+petName+" 'robotic' or 'organic'?");
                        System.out.println();
                        petType = scanner.next();
                        System.out.println();
                    } while(!petType.equalsIgnoreCase("robotic") && !petType.equalsIgnoreCase("organic"));
                    
                    do {
                        System.out.println("Is "+petName+" a 'dog' or a 'cat'?");
                        System.out.println();
                        petSpecies = scanner.next();
                        System.out.println();
                    } while(!petSpecies.equalsIgnoreCase("dog") && !petSpecies.equalsIgnoreCase("cat"));
                    
                    if(petType.equalsIgnoreCase("organic"))
                    {
                        if(petSpecies.equalsIgnoreCase("dog"))
                        {
                            OrganicDog pet = new OrganicDog(petName);
                            organicDogShelter.addPet(pet);
                        }
                        else
                        {
                            OrganicCat pet = new OrganicCat(petName);
                            organicCatShelter.addPet(pet);
                        }
                    }
                    else if (petType.equalsIgnoreCase("robotic"))
                    {
                        if(petSpecies.equalsIgnoreCase("dog"))
                        {
                            RoboticDog pet = new RoboticDog(petName);
                            roboticDogShelter.addPet(pet);
                        }
                        else
                        {
                            RoboticCat pet = new RoboticCat(petName);
                            roboticCatShelter.addPet(pet);
                        }
                    }
            
                    System.out.println("We've just accepted "+petName+" into the shelter. Thank you!");
                    System.out.println();
                break;

                case "view pet": 
                    System.out.println("Please enter the name of the pet you would like to view");
                    System.out.println();

                    petName = scanner.nextLine();
                    System.out.println();

                    do {
                        System.out.println("Is "+petName+" 'robotic' or 'organic'?");
                        System.out.println();
                        petType = scanner.next();
                        System.out.println();
                    } while(!petType.equalsIgnoreCase("robotic") && !petType.equalsIgnoreCase("organic"));
                    
                    do {
                        System.out.println("Is "+petName+" a 'dog' or a 'cat'?");
                        System.out.println();
                        petSpecies = scanner.next();
                        System.out.println();
                    } while(!petSpecies.equalsIgnoreCase("dog") && !petSpecies.equalsIgnoreCase("cat"));
                    
                    if(petType.equalsIgnoreCase("organic"))
                    {
                        if(petSpecies.equalsIgnoreCase("dog"))
                        {
                            if(organicDogShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                OrganicDog pet = organicDogShelter.getPet(petName.toLowerCase());

                                System.out.println("Name: "+pet.getName());
                                System.out.println("Age: "+pet.ageInYears()+" years");
                                System.out.println("Hunger: "+pet.hungerStatus());
                                System.out.println("Thirst: "+pet.thirstStatus());
                                System.out.println("Energy: "+pet.energyStatus());
                                System.out.println("Mood: "+pet.moodStatus());
                                System.out.println();
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                        else
                        {
                            if(organicCatShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                OrganicCat pet = organicCatShelter.getPet(petName.toLowerCase());

                                System.out.println("Name: "+pet.getName());
                                System.out.println("Age: "+pet.ageInYears()+" years");
                                System.out.println("Hunger: "+pet.hungerStatus());
                                System.out.println("Thirst: "+pet.thirstStatus());
                                System.out.println("Energy: "+pet.energyStatus());
                                System.out.println("Mood: "+pet.moodStatus());
                                System.out.println();
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                    }
                    else if (petType.equalsIgnoreCase("robotic"))
                    {
                        if(petSpecies.equalsIgnoreCase("dog"))
                        {
                            if(roboticDogShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                RoboticDog pet = roboticDogShelter.getPet(petName.toLowerCase());

                                System.out.println("Name: "+pet.getName());
                                System.out.println("Age: "+pet.ageInYears()+" years");
                                System.out.println("Oil: "+pet.oilStatus());
                                System.out.println("Charge: "+pet.chargeStatus());
                                System.out.println("Mood: "+pet.moodStatus());
                                System.out.println();
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                        else
                        {
                            if(roboticCatShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                RoboticCat pet = roboticCatShelter.getPet(petName.toLowerCase());

                                System.out.println("Name: "+pet.getName());
                                System.out.println("Age: "+pet.ageInYears()+" years");
                                System.out.println("Oil: "+pet.oilStatus());
                                System.out.println("Charge: "+pet.chargeStatus());
                                System.out.println("Mood: "+pet.moodStatus());
                                System.out.println();
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                    }
                break;

                case "view all pets":
                    organicDogShelter.summarizeShelter();
                    organicCatShelter.summarizeShelter();
                    roboticDogShelter.summarizeShelter();
                    roboticCatShelter.summarizeShelter();
                break;

                case "adopt pet": 
                    System.out.println("Please enter the name of the pet you would like to adopt");
                    System.out.println();
            
                    petName = scanner.nextLine();
                    System.out.println();

                    do {
                        System.out.println("Is "+petName+" 'robotic' or 'organic'?");
                        System.out.println();
                        petType = scanner.next();
                        System.out.println();
                    } while(!petType.equalsIgnoreCase("robotic") && !petType.equalsIgnoreCase("organic"));
                    
                    do {
                        System.out.println("Is "+petName+" a 'dog' or a 'cat'?");
                        System.out.println();
                        petSpecies = scanner.next();
                        System.out.println();
                    } while(!petSpecies.equalsIgnoreCase("dog") && !petSpecies.equalsIgnoreCase("cat"));
                    
                    if(petType.equalsIgnoreCase("organic"))
                    {
                        if(petSpecies.equalsIgnoreCase("dog"))
                        {
                            if(organicDogShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                System.out.println("Congratulations! You've just given "+petName+" their new forever home!");
                                System.out.println("Thank you for adopting with us!");
                                System.out.println();
                                organicDogShelter.adoptPet(organicDogShelter.getPet(petName));
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                        else
                        {
                            if(organicCatShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                System.out.println("Congratulations! You've just given "+petName+" their new forever home!");
                                System.out.println("Thank you for adopting with us!");
                                System.out.println();
                                organicCatShelter.adoptPet(organicCatShelter.getPet(petName));
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                    }
                    else if (petType.equalsIgnoreCase("robotic"))
                    {
                        if(petSpecies.equalsIgnoreCase("dog"))
                        {
                            if(roboticDogShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                System.out.println("Congratulations! You've just given "+petName+" their new forever home!");
                                System.out.println("Thank you for adopting with us!");
                                System.out.println();
                                roboticDogShelter.adoptPet(roboticDogShelter.getPet(petName));
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                        else
                        {
                            if(roboticCatShelter.getAllPets().containsKey(petName.toLowerCase()))
                            {
                                System.out.println("Congratulations! You've just given "+petName+" their new forever home!");
                                System.out.println("Thank you for adopting with us!");
                                System.out.println();
                                roboticCatShelter.adoptPet(roboticCatShelter.getPet(petName));
                            }
                            else
                            {
                                System.out.println("Sorry, we don't have that pet in this shelter.");
                                System.out.println();
                            }
                        }
                    }
                break;

                default: 
                    System.out.println();
                break;
            }
        } while(!"quit".equals(choice));
        
        scanner.close();
    }
}

