import React, { useState, useEffect } from "react";

export default function Test() {

  const [newName, setNewName] = useState("");
  const [newAge, setNewAge] = useState();
  const [newHunger, setNewHunger] = useState();
  const [newThirst, setNewThirst] = useState();
  const [newEnergy, setNewEnergy] = useState();
  const [newMood, setNewMood] = useState();
  const [newCleanliness, setNewCleanliness] = useState();
  const [newHasBeenWalked, setNewHasBeenWalked] = useState();

  const getDog = () => {
    const ID = document.getElementById('text').value;
    console.log(ID);
    fetch(`api/organicDogs/${ID}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => {
        console.log(responseBody);
        });
  };



  const newDog = () => {

    const dogData = {
      name: "Spot",
      ageInDays: 69,
      Hunger: 20,
      thirst: 0,
      energy: 10,
      mood: 7,
      cageCleanliness: 100,
      hasBeenRecentlyWalked: false,
    };

    fetch('api/organicDogs', { method: "POST", headers: {
        "Content-Type": "application/json",
      }, body: JSON.stringify(dogData), })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Activity saved successfully!");
      })
      .catch((error) => {
        console.error("Error saving activity:", error);
      });
  };

  /*const newDog = () => {

    const dogData = {
      name: document.getElementById("name-input").value,
      ageInDays: document.getElementById("age-input").value,
      Hunger: document.getElementById("hunger-input").value,
      thirst: document.getElementById("thirst-input").value,
      energy: document.getElementById("energy-input").value,
      mood: document.getElementById("mood-input").value,
      cageCleanliness: document.getElementById("cage-input").value,
      hasBeenRecentlyWalked: document.getElementById("been-walked-input").value,
    };

    fetch('api/organicDogs', { method: "POST", headers: {
        "Content-Type": "application/json",
      }, body: JSON.stringify(dogData), })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        console.log("Activity saved successfully!");
      })
      .catch((error) => {
        console.error("Error saving activity:", error);
      });
  };*/

  const setValuesForUpdate = async (ID) => {
    const response = await fetch(`api/organicDogs/${ID}`, { method: "GET", cache: "default" });
    const responseBody = await response.json();
        return responseBody;
  }

  /*const setValuesForUpdate = async (ID) => {
    try {
      const response = await fetch(`api/organicDogs/${ID}`, {
        method: "GET",
        cache: "default",
      });
      const responseBody = await response.json();
      setNewName(responseBody.name);
      setNewHunger(responseBody.hunger);
      // ... set other state variables ...
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };*/

  const updateDog = (ID, data) => {
    console.log("7");

    fetch(`api/organicDogs/${ID}`, {
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
        console.log("Dog updated successfully!");
    })
    .catch((error) => {
        console.error("Error updating dog:", error);
    });

  }

  const changeDogState = async (fieldToEdit) => {
    const ID = document.getElementById('text').value;
    const data = await setValuesForUpdate(ID);
    
    console.log(fieldToEdit + " before was " + data[fieldToEdit]);
    data[fieldToEdit] = data[fieldToEdit] - 15;
    console.log(fieldToEdit + " now is " + data[fieldToEdit]);
    
    updateDog(ID, data);
  }

  const adoptDog = () => {
    const ID = document.getElementById('text').value;
    fetch(`api/organicDogs/${ID}`, {
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

  return (
    <div>
      <p>AHHHHHHHHHH</p>
      <button onClick={newDog}>New Dog</button>
      <input type="text" id='text'/>
      <button onClick={getDog}>Get Dog</button>
      <button onClick={() => changeDogState("hunger")}>Feed Dog</button>
      <button onClick={() => changeDogState("thirst")}>Water Dog</button>
      <button onClick={adoptDog}>Adopt Dog</button>
    </div>
  );
}