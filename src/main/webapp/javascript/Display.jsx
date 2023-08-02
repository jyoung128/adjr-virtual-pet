import React, { useState, useEffect } from "react";

export default function Display() {
  let [allOrganicDogs, setAllOrganicDogs] = useState([]);
  let [allOrganicCats, setAllOrganicCats] = useState([]);
  let [organicShelters, setOrganicShelters] = useState([]);

  const getCats = () => {
    fetch(`/api/organicCats`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicCats(responseBody));
    return () => {};
  };

  const getDogs = () => {
    fetch(`/api/organicDogs`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicDogs(responseBody));
    return () => {};
  };

  //   const getShelters = () => {
  //     fetch("/api/organicDogs/{organicDog_id}", { method: "GET", cache: "default" })
  //       .then((response) => response.json())
  //       .then((responseBody) => setAllOrganicShelters(responseBody.results));
  //     return () => {};
  //   };
  if (allOrganicCats && allOrganicCats._embedded) {
    return (
      <div>
        <button onClick={getCats}>Show All Cats</button>
        <button onClick={getDogs}> Show All dogs</button>
        <ul>
          {allOrganicCats["_embedded"]["organicCatList"].map((oneCat) => (
            <OrganicCat key={oneCat.petId} organicCat={oneCat} />
          ))}
        </ul>
      </div>
    );
  }

  else if (allOrganicDogs && allOrganicDogs._embedded) {
    return (
      <div>
        <button onClick={getDogs}> Show All dogs</button>
        <button onClick={getCats}>Show All Cats</button>
        <ul>
          {allOrganicDogs["_embedded"]["organicDogList"].map((oneDog) => (
            <OrganicDog key={oneDog.petID} organicDog={oneDog} />
          ))}
        </ul>
      </div>
    );
  }

  return (
    <div>
      <div id="cats">
        <button onClick={getCats}>Show All Cats</button>
      </div>
      <div id="dogs">
        <button onClick={getDogs}> Show All dogs</button>
      </div>
      <div id="shelter">
        {/* <button onClick={getShelters}>All Shelters</button> */}
        <ul>
          {organicShelters.map((oneShelter) => (
            <OrganicShelter
              key={oneShelter.shelterId}
              organicShelter={oneShelter}
            />
          ))}
        </ul>
      </div>
    </div>
  );
}

function OrganicCat({ organicCat }) {
  return (
    <>
      <li key={organicCat.petID}></li>
      <li>Name:{organicCat.name}</li>
      <li>Hunger:{organicCat.hunger}</li>
      <li>Thirst:{organicCat.thirst}</li>
      <li>mood:{organicCat.mood}</li>
    </>
  );
}

function OrganicDog({ organicDog }) {
  return (
    <>
      <li key={organicDog.petID}></li>
      <li>Name:{organicDog.name}</li>
      <li>Hunger:{organicDog.hunger}</li>
      <li>Thirst:{organicDog.thirst}</li>
      <li>mood:{organicDog.mood}</li>
    </>
  );
}

function OrganicShelter({ organicShelter }) {
  return (
    <ul>
      <li key={organicShelter.shelterId}></li>
      <li>Organic Pets:{organicShelter.allPets}</li>
    </ul>
  );
}
