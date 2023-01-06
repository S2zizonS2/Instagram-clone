import ThemeStyle from "./ThemeStyle";

interface ThemeContextProps {
    theme: string;
    themeStyle: ThemeStyle
    onChangeTheme: (theme: string) => void;
}

export default ThemeContextProps;