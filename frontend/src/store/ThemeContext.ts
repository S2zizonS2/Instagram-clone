import ThemeContextProps from "@/Type/ThemeContextProps";
import React from "react";

const ThemeContext = React.createContext<ThemeContextProps>({
    theme: "light",
    themeStyle: {
        aside: {
            backgroundColor: "",
            color: ""
        }
    },
    onChangeTheme: (theme: string) => {},
});

export default ThemeContext;