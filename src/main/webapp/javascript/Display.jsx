import React, { useState, useEffect } from "react";

export default function Display() {
  let [organicShelters, setOrganicShelters] = useState([]);

  return (
    <div>
      <div id="cats">
        <OrganicCats />
      </div>
      <div id="dogs">
        <OrganicDogs />
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

function OrganicCats(){
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
    return (
      <button onClick={getCats}>Show All Cats</button>
    )
  }
}

function OrganicDogs(){
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
    return (
      <button onClick={getDogs}>Show All Dogs</button>
    )
  }
}

//CODE FOR LIST TO TEXTBOX (COPIED DIRECTLY):
const makeActivityEditable = (ID) => {
        setSelectedID(ID);
        console.log(ID);
        console.log(selectedID);
        const activityToEdit = document.getElementById(`activity-number-${ID}`)

        const activityInfo = activityToEdit.querySelectorAll('li');
        activityInfo.forEach(category => {
            const colonPosition = category.textContent.indexOf(':');
        
            const labelText = category.textContent.substring(0, colonPosition).trim();
            const originalValue = category.textContent.substring(colonPosition + 1).trim();
            if (labelText === "activityID") {
                return;
            }

            const inputElement = document.createElement('input');
            inputElement.type = 'text';
            inputElement.value = originalValue;

            category.textContent = '';
            category.appendChild(document.createTextNode(`${labelText}: `));
            category.appendChild(inputElement);
        });

        const buttons = activityToEdit.querySelector('div');

        const editButton = buttons.querySelector('a:first-child');
        editButton.textContent = 'Save';
        editButton.onclick = () => promptSave();

        const deleteButton = buttons.querySelector('a:nth-child(2)');
        deleteButton.textContent = 'Cancel';
        deleteButton.onclick = () => makeActivityUneditable(ID);
    }

    const makeActivityUneditable = (ID) => {
        console.log("Got to the function");

        console.log(ID);
        fetch(`api/activities/${ID}`, { method: "GET", cache: "default" })
        .then((response) => response.json())
        .then((currentActivity) => {
            const activityData = {
                ...currentActivity,
            };

            const activityToMakeText = document.getElementById(`activity-number-${ID}`)

            const categories = activityToMakeText.querySelectorAll('li');
            categories.forEach(category => {
                const colonPosition = category.textContent.indexOf(':');
                const labelText = category.textContent.substring(0, colonPosition).trim();

                if (labelText === 'activityID') {
                    return;
                }

                const inputElement = category.querySelector('input');
                const originalValue = activityData[labelText];
                category.removeChild(inputElement);
                category.textContent = `${labelText}: ${originalValue}`;
            });
        });


        const buttons = document.getElementById(`activity-number-${ID}`).querySelector('div');

        const saveButton = buttons.querySelector('a:first-child');
        saveButton.textContent = 'Edit';
        saveButton.onclick = () => makeActivityEditable(ID);

        const cancelButton = buttons.querySelector('a:nth-child(2)');
        cancelButton.textContent = 'Delete';
        cancelButton.onclick = () => promptDelete(ID);
    }
//END OF CODE FOR LIST TO TEXTBOX





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
        <a>Edit</a>
        <a>Delete</a>
      </div>
    </ul>
  );
}

//THIS IS JUST HERE FOR DAWSON'S REFERENCE, WILL BE DELETED:
/*
function displaySearchResult(activity) {
        const searchResults = document.getElementById('search-results');
        const activityData = document.createElement('ul');

        for (const key in activity) {
            const item = document.createElement('li');
            item.textContent = `${key}: ${activity[key]}`;
            activityData.appendChild(item);
        }

        const buttons = document.createElement('div');

        const editLink = document.createElement('a');
        editLink.onclick = () => makeActivityEditable(activity.activityID);
        editLink.textContent = 'Edit';
        buttons.appendChild(editLink);

        const deleteLink = document.createElement('a');
        deleteLink.onclick = () => promptDelete(activity.activityID);
        deleteLink.textContent = 'Delete';
        buttons.appendChild(deleteLink);
    
        buttons.id = `activity-number-${activity.activityID}-buttons`;
        activityData.appendChild(buttons);

        activityData.onmouseover = () => mouseOverSearchResult(activity.activityID);
        activityData.onmouseout = () => mouseLeavesSearchResult(activity.activityID);
        activityData.id = `activity-number-${activity.activityID}`;
        searchResults.appendChild(activityData);
    }
*/
//END OF REFERENCE

// function OrganicShelter({ organicShelter }) {
//   return (
//     <ul>
//       <li key={organicShelter.shelterId}></li>
//       <li>Organic Pets:{organicShelter.allPets}</li>
//     </ul>
//   );
// }
