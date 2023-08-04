import React, { useState, useEffect } from "react";

export default function Display() {
  const [selectedID, setSelectedID] = useState(0);

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

  function getOrganicCats() {
    fetch(`/api/organicCats`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicCats(responseBody));
    return () => {};
  }

  if (allOrganicCats && allOrganicCats._embedded) {
    return (
      <div>
        <button onClick={getOrganicCats}>Show All Cats</button>
        <ul>
          {allOrganicCats["_embedded"]["organicCatList"].map((oneCat) => (
            <OrganicCat key={oneCat.petId} organicCat={oneCat} />
          ))}
        </ul>
      </div>
    );
  } else {
    return <button onClick={getOrganicCats}>Show All Cats</button>;
  }
}

function OrganicDogs() {
  let [allOrganicDogs, setAllOrganicDogs] = useState([]);

  function getOrganicDogs() {
    fetch(`/api/organicDogs`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => setAllOrganicDogs(responseBody));
    return () => {};
  }

  if (allOrganicDogs && allOrganicDogs._embedded) {
    return (
      <div>
        <button onClick={getOrganicDogs}>Show All Dogs</button>
        <div id="list-of-dogs">
          {allOrganicDogs["_embedded"]["organicDogList"].map((oneDog) => (
            <OrganicDog key={oneDog.petId} organicDog={oneDog} />
          ))}
        </div>
      </div>
    );
  } else {
    return <button onClick={getOrganicDogs}>Show All Dogs</button>;
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
          {allOrganicShelters["_embedded"]["organicShelterList"].map(
            (oneShelter) => (
              <ListOrganicShelter
                key={oneShelter.shelterID}
                organicShelter={oneShelter}
              />
            )
          )}
        </ul>
        {console.log(JSON.stringify(allOrganicShelters))}
      </div>
    );
  } else {
    return <button onClick={getShelters}>Show All Shelters</button>;
  }
}

const makeDogEditable = (ID) => {
  //setSelectedID(ID);
  console.log(ID);
  //console.log(selectedID);
  const dogToEdit = document.getElementById(`dog-number-${ID}`);

  const dogNameInfo = dogToEdit.querySelector("li");
  const colonPosition = dogNameInfo.textContent.indexOf(":");
  const labelText = dogNameInfo.textContent.substring(0, colonPosition).trim();
  const originalValue = dogNameInfo.textContent
    .substring(colonPosition + 1)
    .trim();

  //         const inputElement = document.createElement('input');
  //         inputElement.type = 'text';
  //         inputElement.value = originalValue;
  inputElement.id = `pet-${ID}-name-textbox`;

  dogNameInfo.textContent = "";
  dogNameInfo.appendChild(document.createTextNode(`${labelText}: `));
  dogNameInfo.appendChild(inputElement);

  const buttons = dogToEdit.querySelector("div");

  //         const editButton = buttons.querySelector('a:first-child');
  //         editButton.textContent = 'Save';
  //         editButton.onclick = () => updateDogName(ID);

  //         const deleteButton = buttons.querySelector('a:nth-child(2)');
  //         deleteButton.textContent = 'Cancel';
  //         deleteButton.onclick = () => makeDogUneditable(ID);
};

const makeDogUneditable = (ID) => {
  console.log(ID);
  fetch(`api/organicDogs/${ID}`, { method: "GET", cache: "default" })
    .then((response) => response.json())
    .then((currentDog) => {
      console.log(ID);
      console.log(currentDog.name);

      const dogToMakeText = document.getElementById(`dog-number-${ID}`);

      const dogNameInfo = dogToMakeText.querySelector("li");

      const inputElement = dogNameInfo.querySelector("input");
      dogNameInfo.removeChild(inputElement);
      dogNameInfo.textContent = `Name: ${currentDog.name}`;
    });

  const buttons = document
    .getElementById(`dog-number-${ID}`)
    .querySelector("div");

  const saveButton = buttons.querySelector("a:first-child");
  saveButton.textContent = "Edit";
  saveButton.onclick = () => makeActivityEditable(ID);

  const cancelButton = buttons.querySelector("a:nth-child(2)");
  cancelButton.textContent = "Delete";
  cancelButton.onclick = () => adoptDog(ID);
};

const updateDogName = (ID) => {
  const newDogName = document.getElementById(`pet-${ID}-name-textbox`).value;
  const data = {
    name: newDogName,
  };

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
      console.log("Dog name updated successfully!");
    })
    .catch((error) => {
      console.error("Error updating dog name:", error);
    });

  makeDogUneditable(ID);
};

const adoptDog = (ID) => {
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

  const dog = document.getElementById(`dog-number-${ID}`);
  dog.parentNode.removeChild(dog);
};

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
    <ul id={`dog-number-${organicDog.petID}`}>
      <li>Name:{organicDog.name}</li>
      <li>Hunger:{organicDog.hunger}</li>
      <li>Thirst:{organicDog.thirst}</li>
      <li>mood:{organicDog.mood}</li>

      <div id={`dog-number-${organicDog.id}-buttons`}>
        <a onClick={() => makeDogEditable(organicDog.petID)}>Edit</a>
        <a>Delete</a>
      </div>
    </ul>
  );
}

function ListOrganicShelter({ organicShelter }) {
  return (
    <ul>
      <li key={organicShelter.shelterID}></li>
      <li>Shelter Name:{organicShelter.name}</li>
      <li>Organic Dogs:{organicShelter.getDog}</li>
      <li>Organic Cats:{organicShelter.getCat}</li>
    </ul>
  );
}
