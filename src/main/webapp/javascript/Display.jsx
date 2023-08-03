import React, { useState, useEffect } from "react";

export default function Display() {
  let [allOrganicDogs, setAllOrganicDogs] = useState([]);
  let [allOrganicCats, setAllOrganicCats] = useState([]);
  let [organicShelters, setOrganicShelters] = useState([]);
  const [organicCatID, setOrganicCatID] = useState([]);
  const [organicCatIDIndex, setOrganicCatIDIndex] = useState(0);
  const [organicCat, setOrganicCat] = useState({
    name: "",
    hunger: "",
    thirst: "",
    energy: "",
  });

  function getCats() {
    fetch(`/api/organicCats`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) =>
        setOrganicCatID(responseBody._embedded.organicCatList.petID)
      );
    return () => {};
  }

  const getCat = () => {
    fetch(`api/organicCats/` + organicCatID[organicCatIDIndex], {
      method: "GET",
      cache: "default",
    })
      .then((response) => response.json())
      .then((responseBody) =>
        setOrganicCat({
          name: responseBody.name,
          hunger: responseBody.hunger,
          thirst: responseBody.thirst,
          energy: responseBody.energy,
        })
      );
  };

  // useEffect(() => {
  //   if (organicCatID) {
  //     fetch(`api/organicCats/${organicCatID[organicCatIDIndex]}`, {
  //       method: "GET",
  //       cache: "default",
  //     })
  //       .then((response) => response.json())
  //       .then((responseBody) =>
  //         setOrganicCat({
  //           name: responseBody.name,
  //           hunger: responseBody.hunger,
  //           thirst: responseBody.thirst,
  //           energy: responseBody.energy,
  //         })
  //       );
  //   }

  //   return () => {};
  // }, [organicCatID, organicCatIDIndex]);

  function DisplayCats() {
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

    return (
      <div>
        <div>
          <ul>
            {allOrganicCats["_embedded"]["organicCatList"].map((oneCat) => (
              <OrganicCat key={oneCat.petID} organicCat={oneCat} />
            ))}
          </ul>
        </div>
      </div>
    );
  }

  return (
    <div>
      <div>
        <button onClick={getCats}>Get list of all cats</button>
        <button onClick={getCat}>Get first cat</button>
        <div>{JSON.stringify(organicCatID)}</div>
        <div>{organicCat.name}</div>
        <div>{organicCat.energy}</div>
        <div>{organicCat.hunger}</div>
        <div>{organicCat.thirst}</div>
      </div>
    </div>
  );
}

//   if (allOrganicCats && allOrganicCats._embedded) {
//     return (
//       <div>
//         <button onClick={getCats}>Show All Cats</button>
//         <button onClick={getDogs}> Show All dogs</button>
//         <ul>
//           {allOrganicCats["_embedded"]["organicCatList"].map((oneCat) => (
//             <OrganicCat key={oneCat.petId} organicCat={oneCat} />
//           ))}
//         </ul>
//       </div>
//     );
//   }
// }

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
// //   const getShelters = () => {
// //     fetch("/api/organicDogs/{organicDog_id}", { method: "GET", cache: "default" })
// //       .then((response) => response.json())
// //       .then((responseBody) => setAllOrganicShelters(responseBody.results));
// //     return () => {};
// //   };

// function OrganicDog({ organicDog }) {
//   return (
//     <>
//       <li key={organicDog.petID}></li>
//       <li>Name:{organicDog.name}</li>
//       <li>Hunger:{organicDog.hunger}</li>
//       <li>Thirst:{organicDog.thirst}</li>
//       <li>mood:{organicDog.mood}</li>
//     </>
//   );
// }

// function OrganicShelter({ organicShelter }) {
//   return (
//     <ul>
//       <li key={organicShelter.shelterId}></li>
//       <li>Organic Pets:{organicShelter.allPets}</li>
//     </ul>
//   );
// }
