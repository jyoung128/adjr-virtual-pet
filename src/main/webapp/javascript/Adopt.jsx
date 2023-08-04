import React, { useState, useEffect } from "react";

export default function Adopt() {
  const [id, setId] = useState("");
  const [organicShelterName, setOrganicShelterName] = useState("");
  const [organicDog, setOrganicDog] = useState("");
  const [organicCat, setOrganicCat] = useState("");

  const getDog = () => {
    fetch(`api/organicDogs/${id}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => {
        setOrganicDog(responseBody);
      });
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
      .then((responseBody) => setOrganicCat(responseBody));
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

  const updateDogHunger = () => {
    const data = {
      ...organicDog,
      hunger: 30,
    };

    fetch(`api/organicDogs/${id}`, {
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
        console.log("Dog name updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog name:", error);
      });
  };

  const updateCatHunger = () => {
    const data = {
      ...organicCat,
      hunger: 30,
    };

    fetch(`api/organicCats/${id}`, {
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
        console.log("Dog name updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog name:", error);
      });
  };

  const updateDogThirst = () => {
    const data = {
      ...organicDog,
      thirst: 30,
    };

    fetch(`api/organicDogs/${id}`, {
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
        console.log("Dog name updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog name:", error);
      });
  };

  const updateCatThirst = () => {
    const data = {
      ...organicCat,
      thirst: 30,
    };

    fetch(`api/organicCats/${id}`, {
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
        console.log("Dog name updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog name:", error);
      });
  };

  const updateDogMood = () => {
    const data = {
      ...organicDog,
      mood: 69,
      energy: 40,
    };

    fetch(`api/organicDogs/${id}`, {
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
        console.log("Dog name updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog name:", error);
      });
  };

  const updateCatMood = () => {
    const data = {
      ...organicCat,
      mood: 70,
      energy: 40,
    };

    fetch(`api/organicCats/${id}`, {
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
        console.log("Dog name updated successfully!");
      })
      .catch((error) => {
        console.error("Error updating dog name:", error);
      });
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
        <div>{JSON.stringify(organicCat)}</div>
        <div>{organicShelterName}</div>
        <button onClick={getDog}>See Dogs Name by ID</button>
        <button onClick={getCat}>See Cats Name by ID</button>
        <button onClick={getShelter}>See Shelter Name by ID</button>
        <button onClick={deleteDog}>Delete Dog</button>
        <button onClick={deleteCat}>Delete Cat</button>
        <button onClick={deleteShelter}>Delete Shelter</button>
        <button onClick={updateDogHunger}>Feed Dog</button>
        <button onClick={updateCatHunger}>Feed Cat</button>
        <button onClick={updateDogThirst}>Water Dog</button>
        <button onClick={updateCatThirst}>Water Cat</button>
        <button onClick={updateDogMood}>Walk Dog</button>
        <button onClick={updateCatMood}>Walk Cat</button>
      </div>
    </div>
  );
}
