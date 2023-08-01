import React, { useState, useEffect } from "react";

export default function Test() {

  const getDog = (ID) => {
    fetch(`api/organicDogs/{ID}`, { method: "GET", cache: "default" })
      .then((response) => response.json())
      .then((responseBody) => {
        console.log(responseBody);
        });
  };

  return (
    <div>
      <p>AHHHHHHHHHH</p>
    </div>
  );
}