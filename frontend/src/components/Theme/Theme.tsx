
import ThemeContext from "@/store/ThemeContext";
import ThemeStyle from "@/Type/ThemeStyle";
import React, { useCallback, useState } from "react";

const DarkTheme: ThemeStyle = {
    aside: {
        backgroundColor: "var(--black)",
        color: "var(--white)",
    }
};

const LightTheme: ThemeStyle = {
    aside: {
        backgroundColor: "var(--white)",
        color: "black",
    }
};



function Theme(props: { children: React.ReactNode; }) {
    const [theme, setTheme] = useState<string>("light");
    const [initThemeStyle, setInitTheme] =
        useState<ThemeStyle>(() => theme === "light" ? LightTheme : DarkTheme);

    const onChangeTheme = useCallback((theme: string) => {
        if (theme === "light") {
            setInitTheme(() => LightTheme);
        }
        else if (theme === "dark") {
            setInitTheme(() => DarkTheme);
        }
    }, []);

    return (
        <ThemeContext.Provider value={{
            theme: "light",
            themeStyle: initThemeStyle,
            onChangeTheme: onChangeTheme,
        }}>
            {props.children}
        </ThemeContext.Provider>
    );
}

export default Theme;