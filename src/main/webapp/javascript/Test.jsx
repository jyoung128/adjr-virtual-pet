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

  const setValuesForUpdate = (ID) => {
    console.log("3");
    fetch(`api/organicDogs/${ID}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => {
        console.log("3.25");
        setNewName(responseBody.name);
        setNewAge(responseBody.ageInDays);
        setNewHunger(responseBody.hunger);
        setNewThirst(responseBody.thirst);
        setNewEnergy(responseBody.energy);
        setNewMood(responseBody.mood);
        setNewCleanliness(responseBody.Cleanliness);
        setNewHasBeenWalked(responseBody.hasBeenRecentlyWalked);
        console.log("3.5: New name is " + newName);
        });
  }

  const updateDog = (ID) => {
    console.log("7");
    const data = {
      name: newName,
      ageInDays: newAge,
      hunger: newHunger,
      thirst: newThirst,
      energy: newEnergy,
      mood: newMood,
      cageCleanliness: newCleanliness,
      hasBeenRecentlyWalked: newHasBeenWalked,
    }

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

  const feedDog = () => {
    console.log("1");
    const ID = document.getElementById('text').value;
    console.log("2: " + ID);
    setValuesForUpdate(ID);
    console.log("4");
    console.log("5: hunger before was " + newHunger);
    setNewHunger(newHunger - 15);
    console.log("6: hunger now is " + newHunger);
    updateDog(ID);
    console.log("8");
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
      <button onClick={feedDog}>Feed Dog</button>
      <button onClick={adoptDog}>Adopt Dog</button>
    </div>
  );
}