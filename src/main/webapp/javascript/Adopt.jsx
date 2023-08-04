import React, { useState, useEffect } from "react";

export default function Adopt() {
  const [id, setId] = useState("");
  const [organicPetName, setOrganicDogName] = useState("");
  const [organicShelterName, setOrganicShelterName] = useState("");

  const getDog = () => {
    fetch(`api/organicDogs/${id}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setOrganicDogName(responseBody.name));
  };

  const deleteDog = () => {
    fetch(`api/organicDogs/${id}`, {
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
  };

  const getCat = () => {
    fetch(`api/organicCats/${id}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setOrganicDogName(responseBody.name));
  };

  const deleteCat = () => {
    fetch(`api/organicCats/${id}`, {
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
  };

  const getShelter = () => {
    fetch(`api/organicShelters/${id}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setOrganicShelterName(responseBody.name));
  };

  const deleteShelter = () => {
    fetch(`api/organicShelters/${id}`, {
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
  };

  const handleIdChange = ({ target }) => {
    setId(target.value);
  };

  return (
    <div>
      <div>
        <form>
          <label htmlFor="id">ID of Pet you want to Adopt out</label>
          <OrganicCats />
          <OrganicDogs />
          {/* <input
            type="text"
            name="id"
            value={id}
            onChange={handleIdChange}
          ></input> */}
        </form>
      </div>
      <div>
        <div>{organicPetName}</div>
        <div>{organicShelterName}</div>
        <button onClick={getDog}>See Dogs Name by ID</button>
        <button onClick={getCat}>See Cats Name by ID</button>
        <button onClick={getShelter}>See Shelter Name by ID</button>
        <button onClick={deleteDog}>Delete Dog</button>
        <button onClick={deleteCat}>Delete Cat</button>
        <button onClick={deleteShelter}>Delete Shelter</button>
      </div>
    </div>
  );
}


