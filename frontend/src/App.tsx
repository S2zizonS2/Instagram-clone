import React from "react";

import GlobalStyle from "./GlobalStyle";
import { Route, Routes } from "react-router-dom";

import Accounts from "./pages/Accounts/index";
import Layout from "./components/Layout/Layout";

function App() {

    return (
        <React.Fragment>
            <GlobalStyle />
            <Routes>
                <Route path="/accounts/*" element={<Accounts />} />
                <Route path="*" element={<Layout />} />
            </Routes>
        </React.Fragment>
    );
}


export default App;