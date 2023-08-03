import React, { useState, useEffect } from "react";

export default function Adopt() {
  const [id, setId] = useState("");
  const [organicDogName, setOrganicDogName] = useState("");

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

  const handleIdChange = ({ target }) => {
    setId(target.value);
  };

  return (
    <div>
      <div>
        <form>
          <label htmlFor="id">ID of Pet you want to Adopt out</label>
          <input
            type="text"
            name="id"
            value={id}
            onChange={handleIdChange}
          ></input>
        </form>
      </div>
      <div>
        <div>{organicDogName}</div>
        <button onClick={getDog}>See Dogs Name by ID</button>
        <button onClick={deleteDog}>Delete Dog</button>
      </div>
    </div>
  );
}
