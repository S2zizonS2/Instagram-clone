import React from "react";
import BaseProps from "@/Type/BaseProps";
import ThemeContext from "@/store/ThemeContext";


function Content(props: BaseProps) {
    const { onChangeTheme } = React.useContext(ThemeContext);

    return (
        <div className={props?.className}>
            <input type="button" value="light" onClick={() => onChangeTheme("light")} />
            <input type="button" value="dark" onClick={() => onChangeTheme("dark")} />
        </div>
    );
}

export default Content;