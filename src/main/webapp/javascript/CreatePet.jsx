import React, { useState, useEffect } from "react";

export default function CreatePet() {
  const [petName, setPetName] = useState("");

  const [shelterName, setShelterName] = useState("");

  const [species, setSpecies] = useState("");

  const [temperament, setTemperament] = useState("");

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

  return (
    <div>
      <div>
        <form>
          <label htmlFor="petName">PetName</label>
          <input
            type="text"
            name="petName"
            value={petName}
            onChange={handlePetNameChange}
          ></input>

          <label htmlFor="species">Species</label>
          <input
            type="text"
            name="species"
            value={species}
            onChange={handleSpeciesChange}
          ></input>

          <label htmlFor="temperament">Temperament</label>
          <input
            type="text"
            name="temperament"
            value={temperament}
            onChange={handleTemperamentChange}
          ></input>

          <label htmlFor="shelterName">ShelterName</label>
          <input
            type="text"
            name="shelterName"
            value={shelterName}
            onChange={handleShelterNameChange}
          ></input>
        </form>
      </div>
      <div>
        <div>{petName}</div>
        <div>{species}</div>
        <div>{temperament}</div>
        <div>{shelterName}</div>
      </div>
    </div>
  );
}
