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
        <div id='list-of-dogs'>
          {allOrganicDogs["_embedded"]["organicDogList"].map((oneDog) => (
            <OrganicDog key={oneDog.petId} organicDog={oneDog} />
          ))}
        </div>
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

//CODE FOR LIST TO TEXTBOX (COPIED DIRECTLY):
// const makeDogEditable = (ID) => {
//         //setSelectedID(ID);
//         console.log(ID);
//         //console.log(selectedID);
//         const dogToEdit = document.getElementById(`dog-number-${ID}`)

//         const dogNameInfo = dogToEdit.querySelector('li');
//         const colonPosition = dogNameInfo.textContent.indexOf(':');
//         const labelText = dogNameInfo.textContent.substring(0, colonPosition).trim();
//         const originalValue = dogNameInfo.textContent.substring(colonPosition + 1).trim();

//         const inputElement = document.createElement('input');
//         inputElement.type = 'text';
//         inputElement.value = originalValue;

//         dogNameInfo.textContent = '';
//         dogNameInfo.appendChild(document.createTextNode(`${labelText}: `));
//         dogNameInfo.appendChild(inputElement);

//         const buttons = dogToEdit.querySelector('div');

//         const editButton = buttons.querySelector('a:first-child');
//         editButton.textContent = 'Save';
//         //editButton.onclick = () => promptSave();

//         const deleteButton = buttons.querySelector('a:nth-child(2)');
//         deleteButton.textContent = 'Cancel';
//         //deleteButton.onclick = () => makeActivityUneditable(ID);
//     }

//     const makeActivityUneditable = (ID) => {
//         console.log("Got to the function");

//         console.log(ID);
//         fetch(`api/activities/${ID}`, { method: "GET", cache: "default" })
//         .then((response) => response.json())
//         .then((currentActivity) => {
//             const activityData = {
//                 ...currentActivity,
//             };

//             const activityToMakeText = document.getElementById(`activity-number-${ID}`)

//             const categories = activityToMakeText.querySelectorAll('li');
//             categories.forEach(category => {
//                 const colonPosition = category.textContent.indexOf(':');
//                 const labelText = category.textContent.substring(0, colonPosition).trim();

//                 if (labelText === 'activityID') {
//                     return;
//                 }

//                 const inputElement = category.querySelector('input');
//                 const originalValue = activityData[labelText];
//                 category.removeChild(inputElement);
//                 category.textContent = `${labelText}: ${originalValue}`;
//             });
//         });


//         const buttons = document.getElementById(`activity-number-${ID}`).querySelector('div');

//         const saveButton = buttons.querySelector('a:first-child');
//         saveButton.textContent = 'Edit';
//         saveButton.onclick = () => makeActivityEditable(ID);

//         const cancelButton = buttons.querySelector('a:nth-child(2)');
//         cancelButton.textContent = 'Delete';
//         cancelButton.onclick = () => promptDelete(ID);
//     }
// //END OF CODE FOR LIST TO TEXTBOX


//         const buttons = document.getElementById(`activity-number-${ID}`).querySelector('div');

//         const saveButton = buttons.querySelector('a:first-child');
//         saveButton.textContent = 'Edit';
//         saveButton.onclick = () => makeActivityEditable(ID);

//         const cancelButton = buttons.querySelector('a:nth-child(2)');
//         cancelButton.textContent = 'Delete';
//         cancelButton.onclick = () => promptDelete(ID);
// //END OF CODE FOR LIST TO TEXTBOX
  
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
      <li key={organicShelter.shelterId}></li>
      <li>Organic Pets:{organicShelter.allPets}</li>
      <li>Shelter Name:{organicShelter.name}</li>
    </ul>
  );
}
