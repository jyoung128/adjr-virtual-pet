import React, { useState, useEffect } from "react";

export default function Display() {
  let [allOrganicDogs, setAllOrganicDogs] = useState([]);
  let [allOrganicCats, setAllOrganicCats] = useState([]);
  let [organicShelters, setOrganicShelters] = useState([])

  return (
    <div>
      <ul>
        {allOrganicCats.map((oneCat) => (
          <OrganicCat key={oneCat.petId} organicCat={oneCat} />
        ))}
      </ul>
      <ul>
        {allOrganicDogs.map((oneDog) => (
          <OrganicDog key={oneDog.petId} organicDog={oneDog} />
        ))}
      </ul>
      
    </div>
  );

  function OrganicCat({ organicCat }) {
    return (
      <>
        <ul>
          <li key={organicCat.petId}></li>
          <li>Name:{organicCat.name}</li>
          <li>Hunger:{organicCat.hunger}</li>
          <li>Thirst:{organicCat.thirst}</li>
          <li>mood:{organicCat.mood}</li>
        </ul>
      </>
    );
  }

  function OrganicDog({ organicDog }) {
    return (
      <>
        <ul>
          <li key={organicDog.petId}></li>
          <li>Name:{organicDog.name}</li>
          <li>Hunger:{organicDog.hunger}</li>
          <li>Thirst:{organicDog.thirst}</li>
          <li>mood:{organicDog.mood}</li>
        </ul>
      </>
    );
  }

  function OrganicShelter({organicShelter}){
    return (
        <ul>
            <li key={organicShelter.name}></li>
            <li>Organic Cats:{organicShelter.allPets}</li>
        </ul>
    )
  }

}
