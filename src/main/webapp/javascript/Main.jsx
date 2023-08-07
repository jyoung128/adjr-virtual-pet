import React from "react";
import { createRoot } from "react-dom/client";
import { BrowserRouter, Routes, Route, Outlet, Link } from "react-router-dom";

import "../css/style.css";

import Test from "./Test";
import CreatePet from "./CreatePet";
import Adopt from "./Adopt";
import Display from "./Display";

function Layout() {
  return (
    <>
      <nav>
        <Link to="/">Add a Pet</Link>
        <Link to="/Display">My Pets</Link>
      </nav>
      <Outlet />
    </>
  );
}
function Main() {
  return (
    <React.StrictMode>
      <BrowserRouter>
        <Routes>
          <Route
            path="/app4?/src?/main?/resources?/static?/index.html?"
            element={<Layout />}
          >
            <Route index element={<CreatePet />} />
            <Route path="Display" element={<Display />} />
            <Route path="Test" element={<Test />} />
            <Route path="Adopt" element={<Adopt />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </React.StrictMode>
  );
}
createRoot(document.getElementById("react-mountpoint")).render(<Main />);
