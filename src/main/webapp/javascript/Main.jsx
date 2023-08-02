import React from "react";
import { createRoot }  from 'react-dom/client';
import { BrowserRouter, Routes, Route, Outlet, Link } from "react-router-dom";
import '../css/style.css';

import Test from './Test';
import Display from "./Display";

function Layout() {
    return (
        <>
            <nav>
                <Link to="/">Main</Link>
                <Link to="/Test">Test</Link>
                <Link to="/Display">Display</Link>
                
            </nav>
            <Outlet />
        </>
    );
}

function Main(){
    return(
        <React.StrictMode>
            <BrowserRouter>
                <Routes>
                    <Route path="/app4?/src?/main?/resources?/static?/index.html?" element={<Layout />}>
                        <Route path="Test" element={<Test />} />
                        <Route path="Display" element={<Display />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </React.StrictMode>
    );
}

createRoot(document.getElementById('react-mountpoint')).render(<Main />);