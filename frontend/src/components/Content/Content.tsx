import React from "react";
import BaseProps from "@/Type/BaseProps";
import ThemeContext from "@/store/ThemeContext";
import { Route, Routes } from "react-router-dom";


function Content(props: BaseProps) {
    const { onChangeTheme } = React.useContext(ThemeContext);

    return (
        <main className={props?.className}>
            <input type="button" value="light" onClick={() => onChangeTheme("light")} />
            <input type="button" value="dark" onClick={() => onChangeTheme("dark")} />
            <Routes>
                {/* <Route path="wrap" element={<h1>wrap</h1>} /> */}
            </Routes>
        </main>
    );
}

export default Content;