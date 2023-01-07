import React from "react";
import { Route, Routes } from "react-router-dom";

function index() {
    return (
        <Routes>
            <Route path="emailsignup" element={<h1>이메일가입</h1>} />
        </Routes>
    );
}

export default index;