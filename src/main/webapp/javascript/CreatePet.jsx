import React, { useState, useEffect } from "react";

const speciesOptions = [
  {
    label: "Organic Dog",
    value: "Organic Dog",
  },
  {
    label: "Organic Cat",
    value: "Organic Cat",
  }
];

export default function CreatePet() {
  const [petName, setPetName] = useState("");

  const [shelterName, setShelterName] = useState("");

  const [species, setSpecies] = useState("");

  const [temperament, setTemperament] = useState("");

  useEffect(() => {
    setSpecies("Organic Dog");
  }, [])

  const handlePetNameChange = ({ target }) => {
    setPetName(target.value);
  };

  const handleSpeciesChange = ({ target }) => {
    setSpecies(target.value);
  };

  const handleTemperamentChange = ({ target }) => {
    setTemperament(target.value);
  };

  const handleShelterNameChange = ({ target }) => {
    setShelterName(target.value);
  };

  function handleSpeciesPost(){
    if(species == "Organic Dog"){
      postDog();
    }

    if(species == "Organic Cat"){
      postCat();
    }
  }

  const postDog = () => {
    fetch("api/organicDogs", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(petName),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Dog saved successfully!");
      })
      .catch((error) => {
        console.error("Error saving activity:", error);
      });
  };

  const postCat = () => {
    fetch("api/organicCats", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(petName),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Cat saved successfully!");
      })
      .catch((error) => {
        console.error("Error saving activity:", error);
      });
  };

  const postShelter = () => {
    fetch("api/organicShelters", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name: shelterName }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Shelter saved successfully!");
      })
      .catch((error) => {
        console.error("Error saving activity:", error);
      });
  };

  return (
    <div className="create-pet-container">
      <div className="create-pet-form">
        <form>
          <h2>Add a Pet</h2>
          <label htmlFor="petName">PetName: </label>
          <input
            type="text"
            name="petName"
            value={petName}
            onChange={handlePetNameChange}
          ></input>

          <label htmlFor="species">Species: </label>
          <select name="species" id="species" onChange={handleSpeciesChange}>
            {speciesOptions.map((option) => (
              <option key={option.value} value={option.value}>{option.label}</option>
            ))}
          </select>

          <label htmlFor="temperament">Temperament: </label>
          <input
            type="text"
            name="temperament"
            value={temperament}
            onChange={handleTemperamentChange}
          ></input>

          <label htmlFor="shelterName">Shelter Name: </label>
          <input
            type="text"
            name="shelterName"
            value={shelterName}
            onChange={handleShelterNameChange}
          ></input>
        <button onClick={handleSpeciesPost}>Create Pet</button>
        <button onClick={postShelter}>Create Shelter</button>
        </form>
      </div>
      <div>
        <div>{petName}</div>
        <div>{species}</div>
        <div>{temperament}</div>
        <div>{shelterName}</div>
        <CreatePetImage species={species}/>
      </div>
    </div>
  );
}

function CreatePetImage({species}) {
  if(species == "Organic Cat"){
    return(
      <div className="create-pet-image-container">
        <img src="images/cat.png"></img>
      </div>
    );
  } else {
    return(
      <div className="create-pet-image-container">
        <img src="images/dog.png"></img>
      </div>
    );
  }
}
