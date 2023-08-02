import React, { useState, useEffect } from "react";

export default function Display() {
  let [allOrganicDogs, setAllOrganicDogs] = useState([]);
  let [allOrganicCats, setAllOrganicCats] = useState([]);
  let [organicShelters, setOrganicShelters] = useState([])

  const getCats = () => {
    fetch(`/api/organicDogs/{organicDog_id}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicDogs(responseBody.results));
    return () => {};
  };

  const getDogs = () => {
    fetch(`/api/organicCats/{organicCat_id}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicCats(responseBody.results));
    return () => {};
  };

//   const getShelters = () => {
//     fetch("/api/organicDogs/{organicDog_id}", { method: "GET", cache: "default" })
//       .then((response) => response.json())
//       .then((responseBody) => setAllOrganicDogs(responseBody.results));
//     return () => {};
//   };


  return (
    <div>
        <div id="cats">
        <button onClick={getCats}>Show All Cats</button>
      <ul>
        {allOrganicCats.map((oneCat) => (
          <OrganicCat key={oneCat.petId} organicCat={oneCat} />
        ))}
      </ul>
      </div>
      <div id="dogs">
      <button onClick={getDogs}> Show All dogs</button>
      <ul>
        {allOrganicDogs.map((oneDog) => (
          <OrganicDog key={oneDog.petId} organicDog={oneDog} />
        ))}
      </ul>
      </div>
      <div id="shelter">
      {/* <button onClick={getShelters}>All Shelters</button> */}
      <ul>
        {organicShelters.map((oneShelter) => (
            <OrganicShelter key={oneShelter.shelterId} organicShelter={oneShelter} />
        ))}
      </ul>
      </div>
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
            <li key={organicShelter.shelterId}></li>
            <li>Organic Pets:{organicShelter.allPets}</li>
        </ul>
    )
  }

}
