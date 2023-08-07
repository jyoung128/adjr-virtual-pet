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

const temperamentOptions = [
  {
    label: "Docile",
    value: "Docile",
  },
  {
    label: "Dominant",
    value: "Dominant",
  },
  {
    label: "Avoidant",
    value: "Avoidant",
  },
  {
    label: "Social",
    value: "Social",
  }
];

export default function CreatePet() {
  const [petName, setPetName] = useState("");

  const [species, setSpecies] = useState("");

  const [temperament, setTemperament] = useState("");

  const [shelterId, setShelterId] = useState(0);

  const [newShelterName, setNewShelterName] = useState("");

  const [postedPetId, setPostedPetId] = useState(0);

  useEffect(() => {
    setSpecies("Organic Dog");
    setTemperament("Docile");
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

  const handleShelterIdChange = ({ target }) => {
    setShelterId(target.value);
  };

  const handleNewShelterNameChange = ({ target }) => {
    setNewShelterName(target.value);
  };

  function handleSpeciesPost(){
    if(species == "Organic Dog" && shelterId){
      postDog().then(() => putDogInShelter());
    } else if(species == "Organic Dog") {
      postDog();
    }

    if(species == "Organic Cat" && shelterId){
      postCat().then(() => putCatInShelter());
    } else if(species == "Organic Cat") {
      postCat();
    }
  }

  async function postDog(){
    return fetch("/api/organicDogs", {
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
      .then((responseBody) => setPostedPetId(responseBody.petID))
      .catch((error) => {
        console.error("Error saving dog:", error);
      });
  };

  const putDogInShelter = () => {
    fetch(`/api/organicShelters/${shelterId}/organicDogs/${postedPetId}`, {
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
        console.log("Dog added to shelter successfully!");
      })
      .catch((error) => {
        console.error("Error adding dog to shelters:", error);
      });
  }

  const putCatInShelter = () => {
    fetch(`/api/organicShelters/${shelterId}/organicCats/${postedPetId}`, {
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
        console.log("Cat added to shelter successfully!");
      })
      .catch((error) => {
        console.error("Error adding cat to shelter:", error);
      });
  }

  const postCat = () => {
    fetch("/api/organicCats", {
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
        console.error("Error saving cat:", error);
      });
  };

  const postShelter = () => {
    fetch("/api/organicShelters", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name: newShelterName }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Shelter saved successfully!");
      })
      .catch((error) => {
        console.error("Error saving shelter:", error);
      });
  };

  return (
    <div>
      <div className="create-pet-container">
        <div className="create-pet-form">
          <form>
            <h2>Add a Pet</h2>
            <label htmlFor="petName">Pet Name: </label>
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
            <select id="temperament" name="temperament" onChange={handleTemperamentChange}>
                {temperamentOptions.map((option) => (
                  <option key={option.value} value={option.value}>{option.label}</option>
                ))}
            </select>

            <label htmlFor="shelterName">Shelter Name: </label>
            <OrganicShelters handleShelterIdChange={handleShelterIdChange} />
            {/* <input
              type="text"
              name="shelterName"
              value={shelterName}
              onChange={handleShelterNameChange}
            ></input> */}
          <button onClick={handleSpeciesPost}>Create Pet</button>
          </form>
        </div>
        <div>
          <div>{petName}</div>
          <div>{species}</div>
          <div>{temperament}</div>
          {/* <div>{shelterName}</div> */}
          <CreatePetImage species={species}/>
        </div>
      </div>
      <div className="create-pet-container">
        <div className="create-pet-form">
          <form>
            <h2>Add a Shelter</h2>
            <label htmlFor="newShelterName">Shelter Name: </label>
            <input
              type="text"
              name="newShelterName"
              value={newShelterName}
              onChange={handleNewShelterNameChange}
            ></input>
            <button onClick={postShelter}>Create Shelter</button>
          </form>
        </div>
        <div>
            <div>{newShelterName}</div>
            <CreateShelterImage />
        </div>
      </div>
    </div>
  );
}

function OrganicShelters({handleShelterIdChange}){
  const [allOrganicShelters, setAllOrganicShelters] = useState([]);

  useEffect(() => {
    getShelters();
  }, []);

  function getShelters() {
    fetch(`/api/organicShelters`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicShelters(responseBody));
    console.log(allOrganicShelters);
    return () => {};
  }

  if (allOrganicShelters && allOrganicShelters._embedded){
    return(
      <select name="shelterId" id="shelterId" onChange={handleShelterIdChange}>
        <option defaultValue>None</option>
        {allOrganicShelters["_embedded"]["organicShelterList"].map((oneShelter) => (
          <option key={oneShelter.shelterID} value={oneShelter.shelterID}>{oneShelter.name}</option>
        ))}
      </select>
      
    )
  }
}

function CreatePetImage({species}) {
  if(species == "Organic Cat"){
    return(
      <div className="create-pet-image-container">
        <img src="images/organicCat.png"></img>
      </div>
    );
  } else {
    return(
      <div className="create-pet-image-container">
        <img src="images/organicDog.png"></img>
      </div>
    );
  }
}

function CreateShelterImage(){
  return (
    <div className="create-pet-image-container">
      <img src="images/shelter.webp"></img>
    </div>
  );
}


