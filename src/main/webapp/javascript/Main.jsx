import React from "react";
import { createRoot }  from 'react-dom/client';
import { BrowserRouter, Routes, Route, Outlet, Link } from "react-router-dom";
import '../css/style.css';

import HelloWorld from './Test';

function Layout() {
    return (
        <>
            <nav>
                <Link to="/">Main</Link>
                <Link to="/HelloWorld">Hello World</Link>
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
                        <Route path="HelloWorld" element={<HelloWorld />} />
                    </Route>
                </Routes>
            </BrowserRouter>
        </React.StrictMode>
    );
}

createRoot(document.getElementById('react-mountpoint')).render(<Main />);