import React, { useState, useEffect } from "react";

export default function Display() {
  

  return (
    <div>
      <div id="cats">
        <OrganicCats />
      </div>
      <div id="dogs">
        <OrganicDogs />
      </div>
      <div id="shelter">
        <OrganicShelter />
      </div>
    </div>
  );
}

function OrganicCats() {
  let [allOrganicCats, setAllOrganicCats] = useState([]);

  function getCats() {
    fetch(`/api/organicCats`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicCats(responseBody));
    return () => {};
  }

  if (allOrganicCats && allOrganicCats._embedded) {
    return (
      <div>
        <button onClick={getCats}>Show All Cats</button>
        <ul>
          {allOrganicCats["_embedded"]["organicCatList"].map((oneCat) => (
            <OrganicCat key={oneCat.petId} organicCat={oneCat} />
          ))}
        </ul>
      </div>
    );
  } else {
    return <button onClick={getCats}>Show All Cats</button>;
  }
}

function OrganicDogs() {
  let [allOrganicDogs, setAllOrganicDogs] = useState([]);

  function getDogs() {
    fetch(`/api/organicDogs`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicDogs(responseBody));
    return () => {};
  }

  if (allOrganicDogs && allOrganicDogs._embedded) {
    return (
      <div>
        <button onClick={getDogs}>Show All Dogs</button>
        <ul>
          {allOrganicDogs["_embedded"]["organicDogList"].map((oneDog) => (
            <OrganicDog key={oneDog.petId} organicDog={oneDog} />
          ))}
        </ul>
      </div>
    );
  } else {
    return <button onClick={getDogs}>Show All Dogs</button>;
  }
}

function OrganicShelter() {
  let [allOrganicShelters, setAllOrganicShelters] = useState([]);

  function getShelters() {
    fetch(`/api/organicShelters`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicShelters(responseBody));
      console.log(allOrganicShelters);
    return () => {};
  }

  if (allOrganicShelters && allOrganicShelters._embedded) {
  return (
    <div>
      <button onClick={getShelters}>Show All Shelters</button>
      <ul>
        {allOrganicShelters["_embedded"]["organicShelterList"].map((oneShelter) => (
          <ListOrganicShelter key={oneShelter.shelterIdId} allOrganicShelters={oneShelter} />
        ))}
      </ul>
      {console.log(JSON.stringify(allOrganicShelters))}
    </div>
  );
} else {
  return <button onClick={getShelters}>Show All Shelters</button>;
}
}

// function getDogs() {
//   fetch(`/api/organicDogs`, { method: "GET", cache: "default" })
//     .then((response) => response.json())
//     .then((responseBody) => setAllOrganicDogs(responseBody));
//   return () => {};
//   if (allOrganicDogs && allOrganicDogs._embedded) {
//     return (
//       <div>
//         <button onClick={getDogs}> Show All dogs</button>
//         <button onClick={getCats}>Show All Cats</button>
//         <ul>
//           {allOrganicDogs["_embedded"]["organicDogList"].map((oneDog) => (
//             <OrganicDog key={oneDog.petID} organicDog={oneDog} />
//           ))}
//         </ul>
//       </div>
//     );
//   }
// }
  
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

function ListOrganicShelter({ organicShelter }) {
  return (
    <ul>
      <li key={organicShelter.shelterId}></li>
      <li>Organic Pets:{organicShelter.allPets}</li>
      <li>Shelter Name:{organicShelter.name}</li>
    </ul>
  );
}

