import React, { useState, useEffect } from "react";

export default function Display() {
  const [selectedID, setSelectedID] = useState(0);
  const [showAdoptPrompt, setShowAdoptPrompt] = useState(false);
  const [showPetMenu, setShowPetMenu] = useState(false);

  let [selectedPet, setSelectedPet] = useState([]);
  const [selectedPetName, setSelectedPetName] = useState("");
  const [selectedPetHunger, setSelectedPetHunger] = useState();
  const [selectedPetThirst, setSelectedPetThirst] = useState();
  const [selectedPetMood, setSelectedPetMood] = useState();

  const [isPetMenuSaveButtonDisabled, setIsPetMenuSaveButtonDisabled] = useState(true);

  function toCamelCase(string) {
    return string.toLowerCase().replace(/ (\w)/g, (_, letter) => letter.toUpperCase());
  }

  function DisplayShelter({ shelter }) {
    return (
      <div className="item-container">
        <div>
          <ul className="stats">
            <li>Name: {shelter.name}</li>
            <li>Organic Dogs: {shelter.dogCount}</li>
            <li>Organic Cats: {shelter.catCount}</li>
          </ul>
        </div>
        <div className="shelter-image-container">
          <img src="images/shelter.webp"></img>
        </div>
      </div>
    );
  }
  
  function ShelterLister({ shelterType }) {
    let [allShelters, setAllShelters] = useState([]);
    const camelCaseType = toCamelCase(shelterType);
  
    function getShelters() {
      fetch(`/api/${camelCaseType + "s"}`, { method: "GET", cache: "default" })
        .then((response) => response.json())
        .then((responseBody) => setAllShelters(responseBody));
      console.log(allShelters);
      return () => {};
    }
  
    if (allShelters && allShelters._embedded) {
    return (
      <div>
        <ul className="item-list">
          {allShelters["_embedded"][`${camelCaseType}List`].map((oneShelter) => (
            <DisplayShelter key={oneShelter.shelterID} shelter={oneShelter} />
          ))}
        </ul>
        <button onClick={getShelters}>Show All {shelterType}s</button>
        {console.log(JSON.stringify(allShelters))}
      </div>
    );
    } else {
      return <button onClick={getShelters}>Show All {shelterType}s</button>;
    }
  }

  /////////////////////ATTEMPTING TO CREATE A GENERALIZED VERSION OF ORGANICCAT/DOG/SHELTER




  function DisplayPet({ pet, species}) {
    console.log("In DisplayPet, pet is " + species);
    const camelCaseSpecies = toCamelCase(species);
    return (
      <div id={`${camelCaseSpecies}-number-${pet.petID}`}>
        <div className="item-container">
          <div>
            <ul className="stats">
              <li>Name: {pet.name}</li>
              <li>Hunger: {pet.hunger}</li>
              <li>Thirst: {pet.thirst}</li>
              <li>Mood: {pet.mood}</li>
            </ul>
          </div>
          <div className="pet-image-container">
            <img src={`images/${camelCaseSpecies}.png`}></img>
          </div>
        </div>
        <div id={`${camelCaseSpecies}-number-${pet.id}-buttons`}>
          <a onClick={() => openPetMenu(pet.petID, pet.species)}>Edit</a>
        </div>
      </div>
    );
  }

  function PetLister({species}) {
    console.log("In ListPets, species is " + species);
    let [allPets, setAllPets] = useState([]);
    const camelCaseSpecies = toCamelCase(species);
    const animalTypeCapitalized = species.slice(species.indexOf(" ") + 1);
    const animalType = animalTypeCapitalized.toLowerCase();
  
    function getPets() {
      fetch(`/api/${camelCaseSpecies + "s"}`, { method: "GET", cache: "default" })
        .then((response) => response.json())
        .then((responseBody) => {
          setAllPets(responseBody);
        });
      return () => {};
    }
  
    if (allPets && allPets._embedded) {
      return (
        <div id={`list-of-${animalType}s`}>
          <ul className="item-list">
            {allPets["_embedded"][`${camelCaseSpecies}List`].map((onePet) => (
              <DisplayPet key={onePet.petId} pet={onePet} species={species}/>
            ))}
          </ul>
          <button onClick={getPets}>Show All {animalTypeCapitalized}s</button>
        </div>
      );
    } else {
      return <button onClick={getPets}>Show All {animalTypeCapitalized}s</button>;
    }
  }
  
  /* 
  const updateDogName = (ID) => {
    const newDogName = document.getElementById(`pet-${ID}-name-textbox`).value;
    const data = {
      name: newDogName,
    };
  
    fetch(`api/organicDogs/${ID}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Dog name updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog name:", error);
      });
  };*/

  const openPetMenu = (ID, species) => {
    setShowPetMenu(true);
    const apiRoute = toCamelCase(species) + "s";
    
    fetch(`/api/${apiRoute}/${ID}`, { method: "GET", cache: "default" })
        .then((response) => response.json())
        .then((responseBody) => {
          setSelectedID(ID);
          setSelectedPetName(responseBody.name);
          setSelectedPetHunger(responseBody.hunger);
          setSelectedPetThirst(responseBody.thirst);
          setSelectedPetMood(responseBody.mood);
          setSelectedPet(responseBody);
        });
  }

  const feedSelectedDog = () => {
    selectedPet.hunger -= 15;
    setSelectedPetHunger(selectedPet.hunger);

    fetch(`/api/organicDogs/${selectedPet.petID}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(selectedPet),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Dog updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog:", error);
      });
  }

  const feedSelectedCat = () => {
    selectedPet.hunger -= 15;
    setSelectedPetHunger(selectedPet.hunger);

    fetch(`/api/organicCats/${selectedPet.petID}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(selectedPet),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Cat updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating cat:", error);
      });
  }

  const waterSelectedDog = () => {
    selectedPet.thirst -= 5;
    setSelectedPetThirst(selectedPet.thirst);

    fetch(`/api/organicDogs/${selectedPet.petID}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(selectedPet),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Dog updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog:", error);
      });
  }

  const waterSelectedCat = () => {
    selectedPet.thirst -= 5;
    setSelectedPetThirst(selectedPet.thirst);

    fetch(`/api/organicCats/${selectedPet.petID}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(selectedPet),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Cat updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating cat:", error);
      });
  }

  const closePetMenu = () => {
    setShowPetMenu(false);
  }

  const handleNameTextChange = (event) => {
    setSelectedPetName(event.target.value);

    setIsPetMenuSaveButtonDisabled(false);
  }
  
  const promptAdopt = (ID) => {
    closePetMenu();
    setSelectedID(ID);
    setShowAdoptPrompt(true);
  };
  const closeAdoptPrompt = () => {
    setShowAdoptPrompt(false);
    setShowPetMenu(true);
  }
  
  const adoptDog = () => {
    closeAdoptPrompt();
    closePetMenu();
    const ID = selectedID;
  
    fetch(`/api/organicDogs/${ID}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Dog adopted successfully!");
      })
      .catch((error) => {
        console.error("Error adopting dog:", error);
      });
  
    const dog = document.getElementById(`dog-number-${ID}`);
    dog.parentNode.removeChild(dog);
  };

  const adoptCat = () => {
    closeAdoptPrompt();
    closePetMenu();
    const ID = selectedID;
  
    fetch(`/api/organicCats/${ID}`, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Cat adopted successfully!");
      })
      .catch((error) => {
        console.error("Error adopting cat:", error);
      });
  
    const cat = document.getElementById(`dog-number-${ID}`);
    cat.parentNode.removeChild(cat);
  };

  function PetImage({species}) {
    if(species == "Organic Cat"){
      return(
        <div className="pet-image-container">
          <img src="images/organicCat.png"></img>
        </div>
      );
    } else if(species == "Organic Dog") {
      return(
        <div className="pet-image-container">
          <img src="images/organicDog.png"></img>
        </div>
      );
    }
  }

  return (
    <div>
      <div id="cats">
      <PetLister species="Organic Cat" />
      </div>
      <div id="dogs">
      <PetLister species="Organic Dog" />
      </div>
      <div id="shelter">
        <ShelterLister shelterType="Organic Shelter" />
      </div>

      {showAdoptPrompt && (<div className="menu" id="adopt-prompt">
        <div className="popup">
          <p>Are you sure you want to give this pet up for adoption? This action can not be undone.</p><br/>
          <button onClick={selectedPet.species === "Organic Dog" ? adoptDog : adoptCat}>I'm Sure</button>
          <button onClick={closeAdoptPrompt}>Cancel</button>
        </div>
      </div>
      )}

      {showPetMenu && (<div className="menu" id="pet-menu">
        <div className="popup">
          <div className="item-container">
            <div>
              <ul className="stats">
                <li>Name: <input type="text" value={selectedPetName} onChange={handleNameTextChange}/></li>
                <li>Hunger: {selectedPetHunger} <button onClick={selectedPet.species === "Organic Dog" ? feedSelectedDog : feedSelectedCat}>Feed</button></li>
                <li>Thirst: {selectedPetThirst} <button onClick={selectedPet.species === "Organic Dog" ? waterSelectedDog : waterSelectedCat}>Water</button></li>
                <li>Mood: {selectedPetMood}</li>
              </ul>
            </div>
            <PetImage species={selectedPet.species} />
          </div>
          <button disabled={isPetMenuSaveButtonDisabled}>Save</button>
          <button onClick={closePetMenu}>Close</button>
          <button onClick={() => promptAdopt(selectedID)}>Adopt</button>
        </div>
      </div>
      )}
    </div>
  );
}